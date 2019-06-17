package com.lijian.thread.chapter4.join_sleep_2;

public class Run {
    public static void main(String[] args) {


        ThreadB b = new ThreadB();
        ThreadA a = new ThreadA(b);
        a.start();
        b.start();
        try {
            b.join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
