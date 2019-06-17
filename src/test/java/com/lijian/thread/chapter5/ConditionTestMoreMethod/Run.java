package com.lijian.thread.chapter5.ConditionTestMoreMethod;

public class Run {
    public static void main(String[] args) {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);

        a.setName("A");
        a.start();

        ThreadAA aa = new ThreadAA(service);
        aa.setName("AA");
        aa.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ThreadB b = new ThreadB(service);

        b.setName("B");
        b.start();

        ThreadBB bb = new ThreadBB(service);
        bb.setName("BB");
        bb.start();
    }
}
