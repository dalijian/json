package com.lijian.thread.chapter3.atomicIntergerNoSage;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class MyService {
    public static AtomicLong  airef = new AtomicLong();
   synchronized public void addNum(){
        System.out.println(Thread.currentThread().getName()+"加了100之后的值是："+airef.addAndGet(100));
        airef.addAndGet(1);

    }
}
