package com.lijian.thread.chapter4.stack_2_new;


public class ThreadP  extends Thread {
    private P p;

    public ThreadP(P p) {
        super();
        this.p=p;

    }

    @Override
    public void run(){
        while (true) {
            p.pushService();
        }
    }
}
