package com.lijian.thread.chapter2.t9;

import com.lijian.thread.chapter2.twoObjectTwoLock.HasSelfPrivateNum;
import com.lijian.thread.chapter2.twoObjectTwoLock.ThreadA;
import com.lijian.thread.chapter2.twoObjectTwoLock.ThreadB;

public class Run {
    public static void main(String[] args) {
       MyOneList list = new MyOneList();
        MyThread1 thread1 = new MyThread1(list);
        thread1.setName("A");
        thread1.start();
        MyThread2 thread2 = new MyThread2(list);
        thread2.setName("A");
        thread2.start();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("listSize =" + list.getSize());
    }
}
