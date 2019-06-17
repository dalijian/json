package com.lijian.thread.chapter2.setNewPropertiesLockOne;

public class Run {
    public static  void main(String [] args){
        Service service = new Service();
        Userinfo userinfo = new Userinfo();
        ThreadA a = new ThreadA(service, userinfo);
        a.setName("a");
        a.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadB b = new ThreadB(service, userinfo);
        b.setName("b");
        b.start();

    }
}
