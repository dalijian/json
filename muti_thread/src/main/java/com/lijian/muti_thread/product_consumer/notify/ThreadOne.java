package com.lijian.muti_thread.product_consumer.notify;

public class ThreadOne implements Runnable {

    Something something ;

    public ThreadOne(Something something) {
        this.something = something;
    }

    @Override
    public void run() {
        Something.method(1000000000L);

    }
}
