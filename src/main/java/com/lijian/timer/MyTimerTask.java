package com.lijian.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

    private String name;
    public MyTimerTask(String inputName){
        name= inputName;

    }
    @Override
    public void run() {
        Calendar calender = Calendar.getInstance();

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println(sf.format(calender.getTime()));
        System.out.println("name-->"+name);
        System.out.println("Thread.currentThread().getName()-->"+Thread.currentThread().getName());

//        cancel();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
