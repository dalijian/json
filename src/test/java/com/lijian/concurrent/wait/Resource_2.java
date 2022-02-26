package com.lijian.concurrent.wait;

/**
 * 线程 A,B 有  交替执行  一个做加法，一个做减法
 */
public class Resource_2 {
    private int su = 1;

    public synchronized void sub() {

        while (su > 0) {
            System.out.println(su);
            su--;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        notifyAll();
//        System.out.println("sub "+su);
        try {
//            System.out.println(Thread.currentThread().getName()+" wait");
            wait();
//            System.out.println("sub is end ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void add() {

        while (su < 10 && su >= 0) {
            System.out.println(su);
            su++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
//        System.out.println("add "+su);
        try {
//            System.out.println(Thread.currentThread().getName()+" wait");
            wait();
//            System.out.println("add is end ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
