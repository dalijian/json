package com.lijian.thread.chapter3.t10;

public class Run {
    public static void main(String[] args) {
        PrintString printStringService = new PrintString();
        new Thread(printStringService).start();

        System.out.println("I will stop it stopThread = " + Thread.currentThread().getName());
        printStringService.setContinuePrint(false);

        Runnable task = () -> {
            System.out.println();
        };

    }
}
