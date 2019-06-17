package com.lijian.thread.chapter4.join_sleep_2;

public class RunFirst {
    public static void main(String[] args) {
        ThreadB b = new ThreadB();
        ThreadA a  = new ThreadA(b);
        a.start();
        b.start();
        System.out.println("   main end =" + System.currentTimeMillis());
    }
}
