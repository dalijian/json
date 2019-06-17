package com.lijian.thread.chapter4.test1;

public class Test {
//    public static void main(String[] args) {
//        String newString = new String("");
//        try {
//            newString.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }


    public static void main(String[] args) {
        Object lock = new Object();

        MyThread1 t1 = new MyThread1(lock);
        t1.start();
//        try {
//            Threa.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        MyThread2 t2 = new MyThread2(lock);
        t2.start();

    }
}

