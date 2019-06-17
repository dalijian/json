package com.lijian.thread.chapter4.stack_1;

public class Run {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();

        P p = new P(myStack);
        C c = new C(myStack);

        ThreadP  threadP = new ThreadP(p);
        ThreadC threadC = new ThreadC(c);

        threadP.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadC.start();
    }
}
