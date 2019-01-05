package com.lijian.thread.daemonThread;

public class MyThread extends Thread {


    private int i=0;
    @Override
    public void run(){
        while (true){
            i++;
            System.out.println("i=" + (i));
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
