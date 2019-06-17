package com.lijian.test;

import java.util.concurrent.atomic.AtomicInteger;

public class Threadone implements  Runnable {

    private AtomicInteger number =new AtomicInteger(10);
    @Override
    public  void run() {  //同步代码块 不可以使用 for while 循环 那么 将 等到 该线程执行完后 才 退出 ， 其他线程 无法 运行
        if(number.decrementAndGet()>0){
            System.out.println(number+Thread.currentThread().getName());
            Thread.currentThread().interrupt();
            if (Thread.interrupted()) {
                System.out.println(true);
            }
//            try {
//                Threa.sleep(1000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
