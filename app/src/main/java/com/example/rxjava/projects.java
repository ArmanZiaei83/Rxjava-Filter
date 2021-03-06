package com.example.rxjava;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;

public class projects {

    public static List<Project> addProject (){

        List<Project> list = new ArrayList<>();

        list.add(new Project("Alibaba" , "High" , "20 days" , true));
        list.add(new Project("ATF" , "normal" , "10 days" , true));
        list.add(new Project("RoomDB Project" , "High" , "15 days" , false));

        return list;
    }
}
