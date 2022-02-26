package com.lijian.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FutureSelf implements Runnable {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    volatile boolean  flag = false;
    Object result;

    Callable callable;

    public FutureSelf(Callable callable) {
        this.callable = callable;
    }

    Object get() {
        try {

            lock.lock();
            System.out.println("get lock ");
            while (!flag) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return result;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" start sleep");
            Thread.sleep(5000);
            this.result = callable.call();
            this.flag = true;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
