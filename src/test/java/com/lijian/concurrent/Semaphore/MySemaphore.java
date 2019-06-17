package com.lijian.concurrent.Semaphore;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MySemaphore {
    private AtomicInteger init;

    private ReentrantLock lock = new ReentrantLock(true);

    Condition condition1= lock.newCondition();
    Condition condition2 = lock.newCondition();

    private Queue queue;

    public MySemaphore(AtomicInteger init, Queue queue) {
         this.init = init;
        this.queue = queue;
    }

    public void acquire(){
        try {


            lock.lock();

            if (init.incrementAndGet() < 0) {
                queue.add(this);
                condition1.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void release(){
       if(init.decrementAndGet()<=0) {
           queue.poll();

        }
    }
}
