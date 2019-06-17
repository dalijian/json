package com.lijian.concurrent.StampedLock;

import java.util.concurrent.locks.StampedLock;

public class Demo {


    final StampedLock s1 = new StampedLock();

    public  void read() {

        long stamp = s1.tryOptimisticRead();

        //写入方法局部变量
        //校验 stamp
        if (!s1.validate(stamp)) {
            //升级 为悲观读锁
            stamp = s1.readLock();
            try{
                //读入方法局部变量

            }finally {
                //释放悲观读锁
                s1.unlockRead(stamp);
            }
        }
        // 使用方法局部变量执行业务操作


    }


    public void write(){


        long stamp = s1.writeLock();

        try{
            //写入共享变量

        }finally {
            s1.unlockWrite(stamp);
        }
    }
}
