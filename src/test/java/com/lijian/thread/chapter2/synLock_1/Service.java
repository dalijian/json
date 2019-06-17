package com.lijian.thread.chapter2.synLock_1;

public class Service {
    synchronized public void service1() {
        System.out.println("sevicel");
        service2();
    }
    synchronized public void service2(){
        System.out.println("sevice2");
        service3();
    }
    synchronized public void service3(){
        System.out.println("sevice3");

    }
}
