package com.lijian.concurrent.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadPool {

    //利用阻塞队列实现生产者-消费者模式
    BlockingQueue<Runnable> workQueue;

    //保存内部工作线程
    List<WorkerThread> threads = new ArrayList<>();

    //构造方法

    public MyThreadPool(int poolSize,BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        for (int i = 0; i < poolSize; i++) {
            WorkerThread work = new WorkerThread();
            work.start();
            threads.add(work);

        }
    }

    //提交任务
    void execute(Runnable command) throws InterruptedException {
        workQueue.put(command);

    }
    class WorkerThread extends Thread{
        public void run(){
            while (true) {
                Runnable task = null;
                try {
                    task = workQueue.take();  // 拿到 头部  工作线程  queue  为空 则 等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                task.run();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        //c创建线程池
        MyThreadPool pool = new MyThreadPool(10, workQueue);
        pool.execute(()->{
            System.out.println("hello");


        });
    }



}
