package com.lijian.concurrent.Semaphore;

import org.apache.commons.net.ftp.FTPClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 只是限流器 ，不能 控制 资源 数量
 * @param <T>
 * @param <R>
 */
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

    public static void main(String[] args) throws InterruptedException {
        ObjPool<FTPClient,FTPClient> ftpPool = new ObjPool<>(3,new FTPClient());
        // exec 回调 函数 才是 处理 具体 事务 的 地方 ，参数 t 就是 资源 对象
//       FTPClient ftpClient = ftpPool.exec(t-> {
//           //处理 具体 事务
//            System.out.println(Thread.currentThread().getName()+"拿到 ftpClient");
//            return t ;
//        });
//        Function<FTPClient,Void> function = s -> null;

        for (int i = 0; i < 15; i++) {
            Thread thread= new Thread(() -> {
                try {
                    ftpPool.exec(t-> {
                        //处理 具体 事务
                        System.out.println(Thread.currentThread().getName() + "拿到 ftpClient");
                        try {
                            TimeUnit.SECONDS.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return t;
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();

        }
    }



}


