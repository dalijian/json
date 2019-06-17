package com.lijian.thread.chapter2.syn_out_asyn;


public class Run {
    public static void main(String[] args) {
        MyList object = new MyList();

        ThreadA athread = new ThreadA(object);
        athread.setName("A");
        ThreadB bthread = new ThreadB(object);
        bthread.setName("B");
        athread.start();
        bthread.start();
    }
}
