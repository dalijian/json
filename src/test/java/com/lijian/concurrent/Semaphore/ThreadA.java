package com.lijian.concurrent.Semaphore;

public class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public synchronized void run() {

        service.testMethod();
    }
}
