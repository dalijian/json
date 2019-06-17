package com.lijian.thread.chapter4.p_c_allWait;



public class C {
    private String lock;

    public C(String lock) {
        super();
        this.lock=lock;
    }

    public void getValue(){
        synchronized (lock) {
            while (ValueObject.value.equals("")) {
                System.out.println("消费者"+Thread.currentThread().getName());
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("消费者" + Thread.currentThread().getName() + "runnable");
            ValueObject.value = "";
            lock.notify();

        }
    }
}
