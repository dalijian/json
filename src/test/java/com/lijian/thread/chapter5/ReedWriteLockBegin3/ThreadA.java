package com.lijian.thread.chapter5.ReedWriteLockBegin3;

public class ThreadA extends Thread {

    private Service service;

    public ThreadA(Service service) {
        super();
        this.service=service;
    }
    @Override
    public void run(){
        service.read();
    }

}
