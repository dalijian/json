package com.lijian.thread;

public class Mythread2 extends Thread {
    private int count =5;


    @Override
   synchronized public void run(){
        super.run();
            count --;
            System.out.println(" " + this.currentThread().getName() + " solve, count =" + count);

    }


}
