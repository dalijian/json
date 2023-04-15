package com.lijian.concurrent.lock;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

public class ObjectPool<T, R> {
    final List<T> pool;

    final Semaphore sem;

    public ObjectPool(int size, T t) {
        pool = new Vector<T>();
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        sem = new Semaphore(size);

    }

    R exec(Function<T, R> function) {
        T t = null;
        try {

            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t = pool.remove(0);
            return function.apply(t);
        } finally {
            pool.add(t);
            sem.release();
        }
    }

    public static void main(String[] args) {
        ObjectPool<Long, String> pool = new ObjectPool<Long, String>(10, 2L);
        pool.exec(t -> {
            System.out.println(t);
            return t.toString();
        });

    }


}
