package com.lijian.thread.chapter5.ReedWriteLockBegin2;

public class ThreadB extends Thread {

    private Service service;

    public ThreadB(Service service) {
        super();
        this.service=service;
    }
    @Override
    public void run(){
        service.read();
    }

}
