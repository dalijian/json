package com.lijian.thread.chapter4.stack_2_old;


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
