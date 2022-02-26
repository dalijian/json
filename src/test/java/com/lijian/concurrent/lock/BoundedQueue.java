package com.lijian.concurrent.lock;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  阻塞队列
 *  存放的 不是 线程 是 要 存储 的 对象
 * @param <T>
 */
public class BoundedQueue<T>  {
    private T[] items;

    private int addIndex,removeIndex,count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size) {

        items = (T[]) new Object[size];

    }

    public void add(T t) throws InterruptedException{

        lock.lock();
        try{
            while (count == items.length) {
                System.out.println(" add  wait " + t +" current_count "+count);
                notFull.await();
            }
            items[addIndex] =t;
            if (++addIndex == items.length) {
                addIndex=0;
            }
            ++count;
            notEmpty.signalAll();
            System.out.println(" add success" + t +" current_count "+count);
        }
        finally {
            lock.unlock();
        }
    }
    public T remove() throws InterruptedException{

        lock.lock();
        try{
            while (count == 0) {
                notEmpty.await();

            }
            T x = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex=0;
            }
            --count;
            notFull.signalAll();

            System.out.println(" remove  " + x);
            return  x;
        }finally {
            lock.unlock();
        }
    }
}
