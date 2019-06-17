package com.lijian.thread.chapter5.lockMethodTest1;

public class Run3 {


    public static void main(String[] args) {

        final Service3  service3 = new Service3();
        Runnable runnable = () -> {
            service3.waitMethod();
        };
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);

        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service3.notifyMethod();
    }

}
