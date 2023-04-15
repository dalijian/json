package com.lijian.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockOne {
    private final Lock rt1 = new ReentrantLock();
    int value;

    public void addOne() {
        rt1.lock();
        try {
            value += 1;
            //业务代码
        } finally {
            rt1.unlock();
        }
    }
}
