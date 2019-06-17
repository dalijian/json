package com.lijian.concurrent.connectionPool;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends  Runnable> implements ThreadPool<Job> {

        //线程池最大限制数
    private static final int MAX_WORER_NUMBERS = 10;
       //默认数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    private static final int MIN_WORKER_NUMBERS = 1;

    private final LinkedList<Job> jobs = new LinkedList<>();
      //工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());
      //工作者线程的数量
    private int workerNum = DEFAULT_WORKER_NUMBERS;

      //工作者线程的数量
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num) {

        if (num > MAX_WORER_NUMBERS) {
            workerNum = MAX_WORER_NUMBERS;
        } else if (num < MIN_WORKER_NUMBERS) {
            workerNum = MIN_WORKER_NUMBERS;
        } else{
            workerNum =num;
        }
        initializeWorkers(workerNum);
    }

    public void execute(Job job) {
        if (job != null){
//添加工作 然后进行通知
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }

    }


    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();

        }
    }

    public void addWorkers(int num) {
        synchronized (jobs) {
//            限制新增Worker数量不能超过最大值
            if (num + this.workerNum > MAX_WORER_NUMBERS) {
                num= MAX_WORER_NUMBERS-this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum+=num;

        }
    }

    public void removeWorker(int num) {
        synchronized (jobs) {
            if (num >= this.workerNum) {
                throw new IllegalArgumentException("beyond workNum");

            }
//            按照给定的数量停止Worker
            int count =0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;

        }
    }

    public int getJobSize(){
        return jobs.size();
    }

    private void initializeWorkers(int workerNum) {
        for (int i = 0; i < workerNum; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();

        }
    }


    class Worker implements  Runnable{
//是否工作
        public volatile  boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {

                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
//                            感知到外部WorkerThread 的中断操作，返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
//                    拿出一个Job
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception e) {
//                                忽略Job执行中的Exception
                    }
                }
            }

        }
        public void shutdown(){
            running = false;
        }
    }
}