package com.lijian.thread.chapter2.synBlockString2;


public class Run {
    public static void main(String[] args) {
        Service object = new Service();

        ThreadA athread = new ThreadA(object);
        athread.setName("A");
        ThreadB bthread = new ThreadB(object);
        bthread.setName("B");
        athread.start();
        bthread.start();
    }
}
