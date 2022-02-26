package com.lijian.concurrent.lock;


public class BlockedQueueTest {
    public static void main(String[] args) {
        BoundedQueue<String> threadBlockedQueue = new BoundedQueue<String>(2);

        for (int i = 0; i < 200; i++) {
            Thread thread = new Thread(() -> {
                try {
                    threadBlockedQueue.add(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

    }
}
