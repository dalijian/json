package com.lijian.concurrent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueueTest<T> {
    final ReentrantLock reentrantLock = new ReentrantLock();
    final Condition  empty = reentrantLock.newCondition();
    final Condition full = reentrantLock.newCondition();
    AtomicLong atomicLong = new AtomicLong(0);
    Queue<T> queue = new LinkedList();
    //入队
    void enq(T x) {
        reentrantLock.lock();
        try {
            while (atomicLong.get() > 10) {
                full.await();
            }
            atomicLong.incrementAndGet();
            queue.add(x);
            empty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    void sub(T x) {
        reentrantLock.lock();
        try {
            while (atomicLong.get() <= 0) {
                empty.await();
            }
            atomicLong.decrementAndGet();
            queue.poll();
            full.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        BlockQueueTest blockQueueTest = new BlockQueueTest();
        for (int i = 0; i < 12; i++) {
            blockQueueTest.enq(i);

        }
        System.out.println(blockQueueTest.queue.size());
    }
}
