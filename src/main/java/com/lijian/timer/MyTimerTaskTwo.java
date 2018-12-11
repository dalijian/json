package com.lijian.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class MyTimerTaskTwo extends TimerTask {

    private String name;
    public MyTimerTaskTwo(String inputName){
        name= inputName;

    }
    @Override
    public void run() {

        Calendar calender = Calendar.getInstance();

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println(sf.format(calender.getTime()));
        System.out.println("name-->"+name);
        System.out.println("Thread.currentThread().getName()-->"+Thread.currentThread().getName());
        System.out.println(new Date(scheduledExecutionTime()));
        throw new RuntimeException();

//        cancel();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
