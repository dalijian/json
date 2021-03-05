package com.lijian.thread.deathLock;


public class DeathLock {
    private String a ="A";
    private String b = "B";

    public void getA(){
       synchronized (a){
           System.out.println("thread " + Thread.currentThread().getName() + "get A");
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           synchronized (b) {
               System.out.println("thread " + Thread.currentThread().getName() + "get B");
           }
       }
   }
    public void getB(){
        synchronized (b){
            System.out.println("thread " + Thread.currentThread().getName() + "get B");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (a) {
                System.out.println("thread " + Thread.currentThread().getName() + "get A");
            }
        }
    }

    public static void main(String[] args) {

    }
}
