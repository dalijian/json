package com.lijian.concurrent.faturetask;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FatureTaskTest {

    @Test
    public void fatureTask() {

//        提交 callable
        FutureTask futureTask = new FutureTask(() -> {
            return "当前线程名:" + Thread.currentThread().getName();
        });
        futureTask.run();
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Result r = new Result();

//        使用 参数 r 作为主子线程之间的通信
        Future<Result> future = executorService.submit(new Task(r), r);

        try {
            Result f = future.get();
            System.out.println(f.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    class Task implements Runnable {

        Result r;

        Task(Result r) {
            this.r = r;
        }

        @Override
        public void run() {

            Object a = r.getName();

        }
    }
}

class Result {

    public Object getName() {
        return "lijian";
    }
}
