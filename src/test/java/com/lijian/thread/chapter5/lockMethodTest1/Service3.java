package com.lijian.thread.chapter5.lockMethodTest1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service3 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition newCondition = lock.newCondition();

    public void waitMethod(){
        try {
            System.out.println(lock.isHeldByCurrentThread());
            lock.lock();
            System.out.println(" 公平锁" + lock.isFair());
            System.out.println(lock.isHeldByCurrentThread());
            newCondition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void notifyMethod(){
        try{
            lock.lock();
            System.out.println("有" + lock.getWaitQueueLength(newCondition) + "个线程正在等待 newCondition");
            newCondition.signal();
        }finally {
            lock.unlock();
        }
    }


}
