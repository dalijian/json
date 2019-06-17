package com.lijian.thread.chapter4.p_c_allWait;

public class ThreadC extends Thread {
    private C c;

    public ThreadC(C c) {
        super();
        this.c=c;

    }

    @Override
    public void run(){
        while (true) {
            c.getValue();
        }
    }
}
