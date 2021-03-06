package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private  static final String TAG = "MainActivity";
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.text);

        Observable<Project> projectObservable = Observable
                .fromIterable(projects.addProject())
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Project>() {
                    @Override
                    public boolean test(@NonNull Project project) throws Exception {

                        return project.isComplete;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
        
        projectObservable.subscribe(new Observer<Project>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

                System.out.println("<< Subscribed ! >>");
                Log.d(TAG , "Subscribe called ");
                disposable.add(d);
            }

            @Override
            public void onNext(@NonNull Project project) {

                System.out.println("MainActivity : " + Thread.currentThread().getName());;
                System.out.println("MainActivity : " + project.projectName);;

                textView.setText("isComplete Project : " + project.projectName);
            }

            @Override
            public void onError(@NonNull Throwable e) {

                System.out.println("Error : " + e.getMessage());
            }

            @Override
            public void onComplete() {

                System.out.println("Project Completed ... ");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        disposable.clear();
    }
}