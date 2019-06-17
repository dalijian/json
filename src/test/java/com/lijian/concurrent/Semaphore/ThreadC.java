package com.lijian.concurrent.Semaphore;

public class ThreadC extends Thread {
    private Service service;

    public ThreadC(Service service) {
        this.service = service;
    }

    @Override
    public synchronized void run() {

        service.testMethod();
    }
}
