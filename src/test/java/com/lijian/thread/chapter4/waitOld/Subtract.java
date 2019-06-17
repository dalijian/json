package com.lijian.thread.chapter4.waitOld;



public class Subtract {
    private String lock;

    public Subtract(String lock) {
        super();
        this.lock=lock;

    }
    public void subtract(){
        synchronized (lock) {
           while (ValueObject.list.size() == 0) {
                System.out.println("wait begin ThreadName="+Thread.currentThread().getName());
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait end ThreadName="+Thread.currentThread().getName());
                ValueObject.list.remove(0);
                System.out.println("list size=" + ValueObject.list.size());
            }
        }
    }
}
