package com.lijian.muti_thread.guarded_suspension.a3_book;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.function.Predicate;

public class RequestQueue {
    private final Queue<Request> queue = new LinkedList<Request>();
    public synchronized Request getRequest()  {
        while (queue.peek() == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.remove();
    }
    public synchronized void putRequest(Request request) {
        queue.offer(request);
        notifyAll();
    }

    public static void main(String[] args) {
        GuardedObject<Queue<Request>> object = new GuardedObject<>();

        object.get(x -> x.peek() != null);
    }
}

//多条件if 等待
class GuardedObject<T> {
    T obj;
    final Lock lock = new ReentrantLock();

    final Condition done = lock.newCondition();

    final int timeout = 1;

    final static Map<Object, GuardedObject> gos = new ConcurrentHashMap<>();

    static <K> GuardedObject create(K key) {
        GuardedObject go = new GuardedObject();
        gos.put(key, go);

        return go;
    }

    //    触发事件
    static <K, T> void fireEvent(K key, T obj) {
        GuardedObject go = gos.remove(key);
        if (go != null) {
            go.onChanged(obj);

        }
    }

    //可以使 参数 以 函数的形式 传进来
    T get(Predicate<T> predicate) {
        lock.lock();
        try {
//MeSA 管程
            while (!predicate.test(obj)) {
                done.await(timeout, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            lock.unlock();
        }
        return obj;
    }

    //事件通知方法
    void onChanged(T obj) {
        lock.lock();
        try {
            this.obj = obj;
            done.signalAll();

        } finally {
            lock.unlock();
        }
    }


}


