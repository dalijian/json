package com.lijian.thread.chapter3.t9;

public class Run {
    public static void main(String[] args) {
        PrintString printStringService = new PrintString();
        printStringService.printStringMethod();
        System.out.println("I will stop it stopTHread=" + Thread.currentThread().getName());
        printStringService.setContinuePrint(false);

    }
}
