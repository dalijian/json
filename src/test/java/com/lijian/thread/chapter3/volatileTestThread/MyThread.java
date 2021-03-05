package com.lijian.thread.chapter3.volatileTestThread;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    volatile public static AtomicInteger count = new AtomicInteger(0);
    private static void addCount(){
        for (int i = 0; i < 10; i++) {
            count.incrementAndGet();

        }
        System.out.println("count=" + count);

    }
    @Override
    public void run(){
        addCount();
    }
}
