package com.lijian.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class Run {
//    public static void main(String[] args) {
////        count 不共享 因为每个线程都创建了一个对象
//        Mythread thread1= new Mythread("a");
//        Mythread thread2= new Mythread("b");
//        Mythread thread3= new Mythread("c");
//        thread1.start();
//        thread2.start();
//        thread3.start();
//
//    }

    public static void main(String[] args) {


        Mythread2 mythread = new Mythread2();
        Thread a = new Thread(mythread, "a");
        Thread b = new Thread(mythread, "b");
        Thread c = new Thread(mythread, "c");
        Thread d = new Thread(mythread, "d");
        Thread e = new Thread(mythread, "e");
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();

    }
}
