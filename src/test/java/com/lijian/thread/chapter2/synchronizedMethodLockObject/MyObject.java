package com.lijian.thread.chapter2.synchronizedMethodLockObject;

public class MyObject {
    Object lock = new Object();
    Object lockB = new Object();
    public void methodA() throws InterruptedException {
        synchronized (MyObject.class) {
            System.out.println("begin methodA threadName=" + Thread.currentThread().getName());
            Thread.sleep(10000);
            System.out.println("end");
        }

    }

 public void methodB() throws InterruptedException {
      synchronized (MyObject.class) {
          System.out.println("begin methodB threadName=" + Thread.currentThread().getName());
          Thread.sleep(5000);
          System.out.println("end");
      }


    }
}
