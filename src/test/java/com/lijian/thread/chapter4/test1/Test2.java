package com.lijian.thread.chapter4.test1;

public class Test2 {
    public static void main(String[] args) {
        String lock = new String();
        System.out.println("syn 上面");
        synchronized (lock) {
            System.out.println("syn 第一行");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("wait 下面的代码");

        }
    }
}
