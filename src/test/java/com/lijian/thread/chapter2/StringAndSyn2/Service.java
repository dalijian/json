package com.lijian.thread.chapter2.StringAndSyn2;

public class Service {
    public    void print(Object object) {
        synchronized (object) {


            while (true) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
