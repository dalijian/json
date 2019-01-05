package com.lijian.thread.priority;

import java.util.Random;

public class MyThread1 extends Thread{

    @Override
    public void run(){
        long beginTime = System.currentTimeMillis();
        
        long addResult =0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 500000; j++) {
                Random random = new Random();
                random.nextInt();
                addResult =addResult+j;

            }
            
        }
        long endTime = System.currentTimeMillis();
        System.out.println("****** thread1 use time"+(endTime-beginTime));

    }

}
