package com.lijian.concurrent.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

public class DelayedQueueStudy {
    public static void main(String[] args) {


        DelayQueue delayQueue = new DelayQueue<DelayedTask>();
        DelayedTask delayedTask = new DelayedTask(5000, "abc");
        DelayedTask delayedTask1 = new DelayedTask(3000, "def");
        DelayedTask delayedTask2 = new DelayedTask(8000, "def");
        delayQueue.offer(delayedTask);
        delayQueue.offer(delayedTask1);
        delayQueue.offer(delayedTask2); // 并不是按照queue先入先出 , 而是 根據 任務的 delayTime  判斷   順序
        long startTime = System.currentTimeMillis();
        while (true) {
            try {
                Delayed delayedtask = delayQueue.take();
                System.out.println(delayedtask);
                long endTime = System.currentTimeMillis();
                System.out.println("用时总时间" + (endTime - startTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

