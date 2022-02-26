package com.lijian.concurrent.wait;

public class CountTest {

    public static void main(String[] args) {
        Count c=new Count();
        Add add=new Add(c);
        Sub sub=new Sub(c);
        Thread t1=new Thread(add);

        Thread t4=new Thread(sub);
        t1.start();

        t4.start();

    }

}