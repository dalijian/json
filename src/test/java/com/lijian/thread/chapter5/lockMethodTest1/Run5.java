package com.lijian.thread.chapter5.lockMethodTest1;

public class Run5 {
    public static void main(String[] args) throws InterruptedException {
        final Service4 service5 = new Service4();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service5.waitMethod();
            }
        };

        Thread threadA = new Thread(runnable);
        threadA.setName("A");
        threadA.start();
        Thread.sleep(500);
        Thread threadB = new Thread(runnable);
        threadB.setName("B");
        threadB.start();
        threadB.interrupt();
        System.out.println("main end ");
    }
}
