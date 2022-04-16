package com.lijian.concurrent.condition;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    ReentrantLock lock = new ReentrantLock();
    Condition conditionAdd = lock.newCondition();
    Condition conditionSub = lock.newCondition();

    AtomicInteger atomicInteger = new AtomicInteger(0);

    void addT() {
        lock.lock();
        try {
            while (atomicInteger.get() > 10) {
                try {

                    conditionAdd.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(atomicInteger.incrementAndGet());
            conditionSub.signalAll();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }

    void subT() {
        lock.lock();
        try {
            while (atomicInteger.get() < 0) {
                try {
                    conditionSub.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(atomicInteger.decrementAndGet());
            conditionAdd.signalAll();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionTest conditionTest = new ConditionTest();
        Thread thread1 = new Thread(() -> {
            while (true) conditionTest.addT();
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                conditionTest.subT();
            }
        });

        thread1.start();
        thread2.start();
    }
}
