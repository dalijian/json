package com.lijian.concurrent.worker_comsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Monitor {

    BlockingQueue<Task> blockingQueue = new LinkedBlockingQueue<>(2000);

    void start(){

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.execute(()->{
                try {
                    while (true) {
                        List<Task> ts = pollTasks();

                        execTasks(ts);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        }
    }
    List<Task> pollTasks(){
        List<Task> ts = new LinkedList<>();
        try {



            Task t = blockingQueue.take();

            while (t != null) {
                ts.add(t);
                t = blockingQueue.poll();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ts;
    }

    void execTasks(List<Task> ts) {

    }
}
