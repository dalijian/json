package com.lijian.concurrent.demo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockedQueue<T> {
    final Lock lock = new ReentrantLock();

    final Condition notFull = lock.newCondition();


    final Condition notEmpty = lock.newCondition();

    Queue queue = new LinkedList();

    public BlockedQueue(Queue queue) {
        this.queue = queue;
    }

    void enq(T x) {
        lock.lock();


        try {
            while (queue.size() == 10) {
                notFull.await();
            }

//        入队

            notEmpty.signalAll();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    void deq() {
        lock.lock();
        try {
            while (queue.size() == 0) {
                notEmpty.await();
            }
//            出队
            notFull.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
