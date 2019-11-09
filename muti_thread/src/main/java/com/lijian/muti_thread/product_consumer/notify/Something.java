package com.lijian.muti_thread.product_consumer.notify;

public class Something  {

    public  static Object object = new Object();
    public static void method(long x)  {
        if (x != 0) {

            synchronized (object) {
                try {
                    object.wait(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("wait is notified");

        }
    }

    public  static  void notifyAll2(){
        synchronized (object) {
            object.notify();
        }


    }
}