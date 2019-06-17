package com.lijian.thread.chapter2.setNewStringTwoLock;



public class Run {
    public static void main(String[] args) {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
    }
}
