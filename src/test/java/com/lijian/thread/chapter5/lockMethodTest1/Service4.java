package com.lijian.thread.chapter5.lockMethodTest1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service4 {
    public ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void waitMethod(){
        try{
            lock.lock();
            System.out.println("lock begin" + Thread.currentThread().getName());
            for (int i = 0; i < Integer.MAX_VALUE / 10; i++) {
                String newString = new String();
                Math.random();
            }
            System.out.println("lock end" + Thread.currentThread().getName());

        }finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
    
}
