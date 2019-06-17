package com.lijian.thread.chapter2.setNewStringTwoLock;



public class Run2 {
    public static void main(String[] args) {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.setName("A");



        ThreadB b = new ThreadB(service);
        b.setName("B");
        a.start();
        b.start();
    }
}
