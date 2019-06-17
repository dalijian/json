package com.lijian.thread.chapter5.useConditionWaitNotifyOK;

public class ThreadA extends Thread {
    private MyService service;

    public ThreadA(MyService service) {
        super();
        this.service = service;
    }
    @Override
    public void run(){

        service.await();
    }
}
