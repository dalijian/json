package com.lijian.thread.chapter4.joinMoreTest;

public class ThreadB extends Thread {

    @Override
   synchronized public void run(){
        System.out.println("begin B Threadname =" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" end B ThreadName=" + Thread.currentThread().getName() + " " + System.currentTimeMillis());


    }


}
