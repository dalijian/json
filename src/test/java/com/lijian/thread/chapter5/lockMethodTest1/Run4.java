package com.lijian.thread.chapter5.lockMethodTest1;

public class Run4 {
    public static void main(String[] args) throws InterruptedException {
        final Service2 service = new Service2();
        Runnable runnable = ()->{
            service.waitMethod();

        };

        Thread threadA = new Thread(runnable);
        threadA.start();
        threadA.join(5000);
        Thread.sleep(500);
        Thread threadB = new Thread(runnable);
        threadB.start();
        Thread.sleep(500);
        System.out.println(service.lock.hasQueuedThread(threadA));
        System.out.println(service.lock.hasQueuedThread(threadB));
        System.out.println(service.lock.hasQueuedThreads());
    }
}
