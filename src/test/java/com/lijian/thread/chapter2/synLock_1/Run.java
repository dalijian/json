package com.lijian.thread.chapter2.synLock_1;

public class Run {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}