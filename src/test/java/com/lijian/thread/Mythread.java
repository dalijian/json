package com.lijian.thread;

public class Mythread extends Thread {
    private int count =5;

    public Mythread(String name) {
        super();
        this.setName(name);
    }
    @Override
    public void run(){
        super.run();
        while (count > 0) {
            count --;
            System.out.println(" " + this.currentThread().getName() + " solve, count =" + count);
        }
    }


}
