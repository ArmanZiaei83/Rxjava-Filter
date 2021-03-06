package com.example.rxjava;

public class Project {

    String projectName;
    String priority;
    String deadline;
    boolean isComplete;

    public Project(String projectName, String priority, String deadline, boolean isComplete) {
        this.projectName = projectName;
        this.priority = priority;
        this.deadline = deadline;
        this.isComplete = isComplete;
    }
}
