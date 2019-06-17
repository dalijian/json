package com.lijian.thread.chapter3.waitHasParamMethod;

public class MyRunnable {

    static private Object lock = new Object();
    static private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("wait begin time="+System.currentTimeMillis());
                try {
                    lock.wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait end time ="+System.currentTimeMillis());

            }
            }
        };

    public static void main(String[] args) {
        Thread t = new Thread(runnable);
        t.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(runnable2);
        t2.start();
    }

    static private Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("wait begin time="+System.currentTimeMillis());

                    lock.notify();

                System.out.println("wait end time ="+System.currentTimeMillis());

            }
        }
    };



}
