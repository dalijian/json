package com.lijian.thread.chapter4.wait_notify_insert_test;

public class DBTools {


    volatile private boolean prevIsA = false;
    synchronized public void backupA(){
        while (prevIsA == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i <5 ; i++) {
            System.out.println("*****");

        }
        prevIsA = true;
        notifyAll();
    }
    synchronized public  void backupB(){
        while (prevIsA == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("&&&&&");

        }
        prevIsA = false;
        notifyAll();
    }
}
