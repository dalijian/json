package com.lijian.concurrent.wait;

/**
 *  线程 A,B 有 一个 已经 结束了， 一个 处于 wait 状态 ， 所以 主线程 没 停止
 */
public class Resource {

    private Object objectSub = new Object();
    private Object objectAdd = new Object();

    private int su = 1;

    public synchronized void sub() {

            while (su >0) {
                System.out.println(su);
                su--;
//                while (su == 0) {
//                    try {
//                        wait();
//                        // 如何 通知 另一个 线程
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
            notifyAll();
        try {
            System.out.println(Thread.currentThread().getName()+" wait");
            wait();
            System.out.println("sub is end ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//            notifyAll();


//        while (su < 0) {
//            try {
//                objectSub.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            objectAdd.notifyAll();
//        }

    }

    public synchronized void add() {


            while (su < 10&&su>0) {
                System.out.println(su);
                su++;
//                while (su == 10) {
//                    try {
//                        wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
            notifyAll();
        try {
            System.out.println(Thread.currentThread().getName()+" wait");
            wait();
            System.out.println("add is end ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//            notifyAll();

//        while (su > 10) {
//            try {
//                objectAdd.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            objectSub.notifyAll();
//        }

    }
}
