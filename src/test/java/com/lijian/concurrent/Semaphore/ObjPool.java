package com.lijian.concurrent.Semaphore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

public class ObjPool<T,R> {
    final List<T> pool;
    final Semaphore sem;

    public ObjPool(int size,T t) {
        pool = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < size; i++) {
            pool.add(t);

        }
        sem = new Semaphore(size);
    }

    R exec(Function<T, R> function) throws InterruptedException {
        T t = null;
        sem.acquire();
        try{
            t = pool.remove(0);
            return function.apply(t);
        }finally {
            pool.add(t);
            sem.release();
        }
    }



}


