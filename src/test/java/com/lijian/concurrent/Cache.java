package com.lijian.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    final Map<K,V> m = new HashMap<>();


    final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    final Lock r= readWriteLock.readLock();

    final Lock w = readWriteLock.writeLock();


    V get(K key) {
        V v =null;
        r.lock();
        try{
          v = m.get(key);
        }
        finally {
            r.unlock();
        }
        if (v != null) {
            return v;
        }
        w.lock();
        try{
            v = m.get(key);
            if (v == null) {
//                查询数据库
                m.put(key, v);

            }
        }finally {
            w.unlock();
        }
        return  v;
    }


    V put(K key, V v) {
        w.lock();
        try{
          return   m.put(key, v);
        }finally {
            w.unlock();
        }
    }
    }
