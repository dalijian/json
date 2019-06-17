package com.lijian.thread.chapter2.synchronizedMethodLockObject2;

public class MyObject {
  synchronized   public void methodA() throws InterruptedException {
        System.out.println("begin methodA threadName=" + Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("end");
    }

    synchronized   public void methodB() throws InterruptedException {
        System.out.println("begin methodB threadName=" + Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("end");
    }
}
