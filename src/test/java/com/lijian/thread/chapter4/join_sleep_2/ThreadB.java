package com.lijian.thread.chapter4.join_sleep_2;

public class ThreadB extends Thread {

    @Override
    public void run(){
        System.out.println("b run gegin timer=" + System.currentTimeMillis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("b run end timer=" + System.currentTimeMillis());

    }
    synchronized public void bService(){
        System.out.println("打印了bService timer=" + System.currentTimeMillis());

    }

}
