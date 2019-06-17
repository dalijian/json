package com.lijian.thread.chapter2.synchronizedMethodLockObject;

public class ThreadB extends Thread {
    private MyObject object;

    public ThreadB(MyObject object) {

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
