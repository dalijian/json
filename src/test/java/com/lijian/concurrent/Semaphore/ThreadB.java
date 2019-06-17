package com.lijian.concurrent.Semaphore;

public class ThreadB extends Thread {
    private Service service;

    public ThreadB(Service service) {
        this.service = service;
    }

    @Override
    public synchronized void run() {

        service.testMethod();
    }
}
