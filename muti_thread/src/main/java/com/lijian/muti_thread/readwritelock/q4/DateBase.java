package com.lijian.muti_thread.readwritelock.q4;

import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DateBase<K,V> {
    private final Map<K,V> map = new HashMap<K,V>();
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    // 全部清除
    public synchronized void clear() {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        try {

            writeLock.lock();
            verySlowly();
            map.clear();
        }finally {
            writeLock.unlock();
        }
    }

    // 给key分配value
    public synchronized void assign(K key, V value) {

        verySlowly();
        map.put(key, value);
    }

    // 获取给key分配的值
    public synchronized V retrieve(K key) {
        slowly();
        return map.get(key);
    }

    // 模拟耗时的操作
    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
    }

    // 模拟非常耗时的操作
    private void verySlowly() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }
}
