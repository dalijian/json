package com.lijian.thread.chapter2.synchronizedMethodLockObject2;


public class Run {
    public static void main(String[] args) {
        MyObject object = new MyObject();

        ThreadA athread = new ThreadA(object);
athread.setName("A");

        ThreadB bthread = new ThreadB(object);
        bthread.setName("B");
        athread.start();
        bthread.start();
    }
}
