package com.lijian.thread.chapter2.StringAndSyn;

public class Service {
    public static void print(String object) {
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
