package com.lijian.concurrent.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {
   static ThreadLocal<Object> threadLocal = ThreadLocal.withInitial(()->{
        System.out.println("get 为空 时, 调用此方法");
        return null;
    });


   static ExecutorService service = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            service.submit(()->{
                Object result = threadLocal.get();

                System.out.println(result);
                if (result == null) {
                    threadLocal.set(Thread.currentThread().getName());
                }
                System.out.println("*****************");
                System.out.println(threadLocal.get());
            });
        }
    }
}
