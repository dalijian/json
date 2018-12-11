package com.lijian.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

public class MyTimer {
    public static void main(String[] args)  {
//        创建 timer 实例
        Timer timer=new Timer();
//        创建 MyTimer实例
        MyTimerTask myTimerTask = new MyTimerTask("one");
        MyTimerTaskTwo myTimerTaskTwo = new MyTimerTaskTwo("two");

//通过timer 定时调用 mytimertask 的 run 方法

        Calendar calender = Calendar.getInstance();

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println(sf.format(calender.getTime()));
        try {


            timer.schedule(myTimerTask, 2000L, 1000L);

           timer.schedule(myTimerTaskTwo, 500L, 3000L);
        } catch (RuntimeException e) {
            e.printStackTrace();

        }


    }
}
