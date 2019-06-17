package com.lijian.thread.chapter2.synBlockString2;

public class ThreadB extends Thread {
    private Service object;

    public ThreadB(Service object) {

        super();
        this.object=object;

    }
    @Override
    public void run(){
        super.run();
        object.b();

    }
}
