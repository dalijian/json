package com.lijian.thread.chapter2.synchronizedMethodLockObject;

import com.lijian.thread.chapter2.t1.HasSelfPrivateNum;

public class ThreadA extends Thread {
    private MyObject object;

    public ThreadA(MyObject object) {

        super();
        this.object=object;

    }
    @Override
    public void run(){
        super.run();
        try {
            object.methodA();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
