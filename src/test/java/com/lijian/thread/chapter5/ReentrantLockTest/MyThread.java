package com.lijian.thread.chapter5.ReentrantLockTest;

public class MyThread extends  Thread {
    private MyService service;

    public MyThread(MyService service) {
        super();
        this.service = service;

    }

    @Override
    public void run(){
        service.testMethod();
    }
}
