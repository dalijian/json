package com.lijian.thread.chapter4.stack_2_new;


public class ThreadC extends Thread {
    private C c;

    public ThreadC(C c) {
        super();
        this.c=c;

    }

    @Override
    public void run(){
        while (true) {
            c.popService();
        }
    }
}
