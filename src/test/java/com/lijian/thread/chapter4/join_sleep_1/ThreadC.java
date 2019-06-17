package com.lijian.thread.chapter4.join_sleep_1;

public class ThreadC extends Thread {
private  ThreadB threadB;

    public ThreadC(ThreadB threadB) {
        super();
        this.threadB = threadB;

    }
    @Override
    public void run(){
        threadB.bService();
    }

}
