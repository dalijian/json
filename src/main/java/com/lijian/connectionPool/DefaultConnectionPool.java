package com.lijian.connectionPool;

import java.sql.Connection;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultConnectionPool<Job extends  Runnable> implements ThreadPool<Job> {
    private Queue<Job> jobList = new ArrayDeque<>();
    private List<Work> workList = Collections.synchronizedList(new LinkedList());
    private AtomicInteger threadNum = new AtomicInteger();

    public DefaultConnectionPool(int num) {
        for (int i = 0; i < num; i++) {
            Work work = new Work();
            workList.add(work);
            threadNum.getAndIncrement();
            Thread thread = new Thread(work, "work_thread_" + i);
            thread.start();
        }
    }

    @Override
    public void execute(Job job) {
        synchronized (jobList) {
            jobList.add(job);
        }
    }

    class Work implements Runnable{

        @Override
        public void run() {
            synchronized (jobList) {
                Job job = jobList.remove();
                Thread thread = new Thread(job);
                thread.setName("job_"+jobList.size());
                thread.start();
            }
        }
    }
}
