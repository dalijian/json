package com.lijian.thread.chapter4.waitOld;

public class ThreadSubject extends Thread  {
    private Subtract r;

    public ThreadSubject(Subtract r) {
        super();
        this.r=r;

    }
    @Override
    public void run(){
        r.subtract();
    }

}
