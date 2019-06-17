package com.lijian.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;

public class Mutex {
//    继承同步器模板
    private static  class Sync extends AbstractQueuedLongSynchronizer{
        protected boolean isHeldExclusively(){
            return  getState() ==1;
        }

        public boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int release){
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
                setState(0);
                return true;
            }
            Condition newCondition() {return new ConditionObject();
        }

    }
    private final Sync sync = new Sync();
    public void lock() {
        sync.acquire(1);

    }
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }
    public void unlock(){
        sync.release(1);

    }
    public Condition newCondition(){
        return  sync.newCondition();
    }
    public boolean isLocked(){
        return sync.isHeldExclusively();
    }
    public void lockInterruptibly() throws InterruptedException{
        sync.acquireInterruptibly(1);

    }

    public boolean tryLock(long timeout,TimeUnit unit) throws InterruptedException{
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }
}
