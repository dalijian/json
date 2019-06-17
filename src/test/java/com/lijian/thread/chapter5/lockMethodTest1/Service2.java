package com.lijian.thread.chapter5.lockMethodTest1;

import java.util.concurrent.locks.ReentrantLock;

public class Service2 {
    public ReentrantLock lock = new ReentrantLock();
    public void serviceMethod1(){
        try {
            lock.lock();
            System.out.println("ThreadName=" + Thread.currentThread().getName() + "进入方法");
            Thread.sleep(Integer.MAX_VALUE);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void waitMethod(){
        try {
            lock.lock();

            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
