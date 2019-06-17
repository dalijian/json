package com.lijian.thread.chapter4.stack_2_new;

public class Run {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        P p1 = new P(myStack);
        P p2 = new P(myStack);
        P p3 = new P(myStack);
        P p4 = new P(myStack);
        P p5 = new P(myStack);
        ThreadP threadP1 = new ThreadP(p1);
        ThreadP threadP2= new ThreadP(p2);
        ThreadP threadP3 = new ThreadP(p3);
        ThreadP threadP4 = new ThreadP(p4);
        ThreadP threadP5 = new ThreadP(p5);
        threadP1.setName("product1");
        threadP1.start();
        threadP2.start();

        threadP3.start();
        threadP4.start();
        threadP5.start();

        C c1 = new C(myStack);

        C c2 = new C(myStack);
        C c3 = new C(myStack);
        C c4 = new C(myStack);
        C c5 = new C(myStack);


        ThreadC threadC1 = new ThreadC(c1);
        ThreadC threadC2 = new ThreadC(c2);

        ThreadC threadC3 = new ThreadC(c3);

        ThreadC threadC4 = new ThreadC(c4);

        ThreadC threadC5 = new ThreadC(c5);


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
