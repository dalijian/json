package com.lijian.test;

public class Threadtwo implements Runnable {
    private int number =10;
    @Override
    public void run() {
        if (number > 0) {

            number--;

            System.out.println(number+Thread.currentThread().getName());
        }
    }
}
