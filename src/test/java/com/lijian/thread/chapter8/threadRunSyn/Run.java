package com.lijian.thread.chapter8.threadRunSyn;

public class Run {
    public static void main(String[] args) {


        Object lock = new Object();
        Mythread a = new Mythread(lock, "A", 1);
        Mythread b = new Mythread(lock, "B", 2);
        Mythread c = new Mythread(lock, "C", 0);
a.start();
b.start();
c.start();

    }
}
