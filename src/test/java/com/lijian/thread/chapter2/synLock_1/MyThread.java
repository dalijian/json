package com.lijian.thread.chapter2.synLock_1;

public class MyThread extends Thread {
    @Override
    public void run(){
        Service service = new Service();
        service.service1();

    }
}
