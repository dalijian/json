package com.lijian.thread.chapter2.synBlockString2;

public class ThreadA extends Thread {
    private Service object;

    public ThreadA(Service object) {

        super();
        this.object=object;

    }
    @Override
    public void run(){
        super.run();
        object.a();

    }
}
