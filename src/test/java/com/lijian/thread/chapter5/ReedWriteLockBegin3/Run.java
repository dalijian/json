package com.lijian.thread.chapter5.ReedWriteLockBegin3;

public class Run {
    public static void main(String[] args) {


        Service service = new Service();

        ThreadA a = new ThreadA(service);
        a.setName("A");
        ThreadB b = new ThreadB(service);
        b.setName("B");
        a.start();
        b.start();
    }
}
