package com.lijian.thread.deathLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeathTryLock extends DeathLock {
    private String a ="A";
    private String b = "B";
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    public void getA(){
       if(lock1.tryLock()){
           try {
               System.out.println("thread " + Thread.currentThread().getName() + "get A");
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               if (lock2.tryLock()) {
                   try {
                       System.out.println("thread " + Thread.currentThread().getName() + "get B");
                   } finally {
                       lock2.unlock();
                   }
               }
           }finally {
               lock1.unlock();
           }
       }
   }
    public void getB(){
        if(lock2.tryLock()){
            try {
                System.out.println("thread " + Thread.currentThread().getName() + "get B");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (lock1.tryLock()) {
                    try {
                        System.out.println("thread " + Thread.currentThread().getName() + "get A");
                    } finally {
                        lock1.unlock();
                    }
                }
            }finally {
                lock2.unlock();
            }
        }
    }

    public static void main(String[] args) {

    }
}
