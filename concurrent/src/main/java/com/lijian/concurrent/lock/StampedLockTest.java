package com.lijian.concurrent.lock;

import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
    final StampedLock stampedLock = new StampedLock();
    long stamp = stampedLock.tryOptimisticRead();

    //读入方法局部变量

    //校验 stamp
    if(!stampedLock.validate(stamp))

    {
        //升级为悲观读锁
        stamp = stampedLock.readLock();

        try {
            //读入方法局部变量

        } finally {
            //释放悲观读锁
            stampedLock.unlockRead(stamp);

        }
    }
    //使用方法局部变量执行业务操作

}
