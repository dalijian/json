package com.lijian.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//阻塞队列
public class BlockedQueue<T> {
    final Lock lock = new ReentrantLock();

    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    //入队
    void enq(T x) {
        lock.lock();
        try {
            while (队列已满) {
                //等待队列不满
                notFull.await();

            }
            //入队
            //入队后，通知可出队
            notEmpty.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //出队
    void deq() {
        lock.lock();
        try {
            while (队列已空) {
                //等待队列不空
                notEmpty.await();
            }
            //省略出队操作
            //出队后，通知可入队
            notFull.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
