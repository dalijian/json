package com.lijian.thread.chapter2.t3;

public class Test {
    public static void main(String[] args) {
        PublicVar publicVar = new PublicVar();
        ThreadA threadA = new ThreadA(publicVar);
        threadA.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publicVar.getValue();
    }
}
