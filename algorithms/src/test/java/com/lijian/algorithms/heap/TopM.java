package com.lijian.algorithms.heap;
//
//1. 动态 数据 处理 top K
//2. 维护 k 长度的 priorityQueue

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TopM {

    private int size;

    volatile PriorityQueue<String> priorityQueue;
    List<String> list;


    public TopM(int size) {

        this.size = size;

        priorityQueue = new PriorityQueue<>(size);
        list = new ArrayList<>();
        priorityQueue.offer("123");
    }

    public synchronized void add(String string) {

        String minQueue = priorityQueue.peek();
        if (minQueue == null) {
            priorityQueue.offer(string);
            this.notifyAll();
        } else if (string.compareTo(minQueue) > 0) {
            priorityQueue.remove(minQueue);
            priorityQueue.offer(string);
            this.notifyAll();
        }
        list.add(string);
    }

    public synchronized PriorityQueue getTop() {

        return priorityQueue;
    }

    public static void main(String[] args) {

        TopM topM = new TopM(5);

        new Thread(() -> {
            while (true) {
                topM.add(UUID.randomUUID().toString());
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {

            while (true) {

                System.out.println(topM.getMax());
            }
        }).start();

    }

    private synchronized String getMax() {
        while (priorityQueue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return priorityQueue.remove();

    }


}

