package com.lijian.concurrent.condition;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FunctionCondition implements Runnable {

    ReentrantLock lock =new ReentrantLock();

    List<Condition> waiter = new LinkedList<>();
    
    AtomicInteger counter = new AtomicInteger(0);
    @Override
    public void run() {
        while (true) {
            dowork();
        }

    }

    private void dowork() {
        try{
            lock.lock();
            if (counter.get() < 5) {
                counter.incrementAndGet();
                System.out.println(Thread.currentThread().getName()+"拿到 count");
            }else{
                Condition condition = lock.newCondition();
                waiter.add(condition);
                System.out.println(Thread.currentThread().getName()+",开始等待");
                condition.await();

                {
                    System.out.println(Thread.currentThread().getName()+",等待结束");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
