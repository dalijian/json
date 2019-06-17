package com.lijian.thread.chapter3.atomicIntergerNoSage;

import com.lijian.thread.chapter2.twoObjectTwoLock.HasSelfPrivateNum;

public class MyThread extends Thread {
    private MyService numRef;

    public MyThread(MyService numRef) {

        super();
        this.numRef=numRef;

    }
    @Override
    public void run(){
        super.run();
        numRef.addNum();

    }
}
