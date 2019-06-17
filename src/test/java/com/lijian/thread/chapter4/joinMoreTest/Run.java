package com.lijian.thread.chapter4.joinMoreTest;

public class Run {
    public static void main(String[] args) {


        ThreadB b = new ThreadB();
        ThreadA a = new ThreadA(b);
        a.start();
        b.start();
        try {
            b.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" main end" + System.currentTimeMillis());


    }
}
