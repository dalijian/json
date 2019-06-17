package com.lijian.thread.chapter4.firstNotify;

public class MyRun {
    private String lock = new String("");
    private Runnable runnableA = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("begin wait");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end wait");

            }
        }
    };

    private Runnable runnableB = new Runnable() {

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("begin notify");
                lock.notify();
                System.out.println("end notify");

            }
        }
    };

    public static void main(String[] args) {
        MyRun run = new MyRun();
        Thread a = new Thread(run.runnableA);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread b= new Thread(run.runnableB);
        b.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.start();
    }
}
