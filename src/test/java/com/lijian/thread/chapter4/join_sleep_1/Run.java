package com.lijian.thread.chapter4.join_sleep_1;

public class Run {
    public static void main(String[] args) {


        ThreadB b = new ThreadB();
        ThreadA a = new ThreadA(b);
        a.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadC c = new ThreadC(b);
        c.start();
    }
}
