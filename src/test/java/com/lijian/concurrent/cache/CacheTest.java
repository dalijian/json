package com.lijian.concurrent.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheTest<K, V> {

    final Map<K, V> m = new HashMap<>();
    final ReadWriteLock rwl = new ReentrantReadWriteLock();

    final Lock r = rwl.readLock();
    final Lock w = rwl.writeLock();

    V get(K key) {
        r.lock();
        try {
            V v = m.get(key);
            if (v != null) {
                return v;
            }
      //查询数据库
      return null;
        } finally {
            r.unlock();
        }



    }

    V put(K key, V v) {
        w.lock();
        try {
            v = m.get(key);
            if (v == null) {
                //查询数据库

                m.put(key, v);
            }


        } finally {
            w.unlock();
        }
        return v;
    }
}
