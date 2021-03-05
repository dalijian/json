package com.lijian.concurrent.guarded_suspension;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

public class GuardedObjectcopy<T> {

    T obj;
    final Lock  lock = new ReentrantLock();
    final Condition done = lock.newCondition();
    final int timeout =1;

    T get(Predicate<T> predicate) {
        lock.lock();

        try{
            while (!predicate.test(obj)) {
                done.await(timeout, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
        return obj;
    }


    void onChanged(T obj) {
        lock.lock();
        try{
            this.obj=obj;
            done.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
