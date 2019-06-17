package com.lijian.thread.chapter4.p_c_allWait;



public class P {

    private String lock;

    public P(String lock) {
        super();
        this.lock =lock;

    }
    public void setValue(){
        synchronized (lock) {
            while (!ValueObject.value.equals("")) {
                System.out.println("生产者"+Thread.currentThread().getName()+"waiting ");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String value = System.currentTimeMillis()+"_"+System.nanoTime();
            ValueObject.value =value;
            lock.notify();
        }
    }

}
