package com.lijian.thread.chapter5.ConditionTestMoreMethod;

public class ThreadA extends Thread{
    private MyService service;

    public ThreadA(MyService service) {
        super();
        this.service =service;

    }
    @Override
    public void run(){
        service.methodA();
    }
}
