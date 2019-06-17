package com.lijian.thread.chapter2.StringAndSyn;

public class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        super();
        this.service=service;

    }
    @Override
    public void run(){
        service.print("AA");

    }
}
