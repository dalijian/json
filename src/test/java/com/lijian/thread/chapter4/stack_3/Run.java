package com.lijian.thread.chapter4.stack_3;

public class Run {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();

        P p = new P(myStack);
        C c1 = new C(myStack);

        C c2 = new C(myStack);
        C c3 = new C(myStack);
        C c4 = new C(myStack);
        C c5 = new C(myStack);
        ThreadP threadP = new ThreadP(p);
        threadP.setName("product");
        ThreadC threadC1 = new ThreadC(c1);
        ThreadC threadC2 = new ThreadC(c2);

        ThreadC threadC3 = new ThreadC(c3);

        ThreadC threadC4 = new ThreadC(c4);

        ThreadC threadC5 = new ThreadC(c5);

        threadP.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadC1.start();
        threadC2.start();
        threadC3.start();
        threadC4.start();
        threadC5.start();
    }
}
