package com.lijian.concurrent.worker_comsumer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Logger2 {

    final LogMsg poisonPill = new LogMsg(LEVEL.ERROR, "");

//    任务队列

    final BlockingQueue<LogMsg> bq = new LinkedBlockingQueue<>();


    ExecutorService es = Executors.newFixedThreadPool(1);

    void start() throws IOException {
        File file = File.createTempFile("foo", ".log");

        final FileWriter writer = new FileWriter(file);

        this.es.execute(()->{
            try {
                while (true) {
                    LogMsg log = bq.poll(5, TimeUnit.SECONDS);

                    if (
                            poisonPill.equals(log)
                    ) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void  stop(){
        bq.add(poisonPill);
        es.shutdown();
    }
}
