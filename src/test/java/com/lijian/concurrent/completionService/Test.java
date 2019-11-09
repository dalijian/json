package com.lijian.concurrent.completionService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Test {
    //当需要批量提交异步任务的时候建议你使用 CompletionService
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Executor executor = Executors.newCachedThreadPool();
        CompletionService<String> completionService = new ExecutorCompletionService(executor);
        List<Future> furtureList = new ArrayList<>();
        furtureList.add(completionService.submit(() -> {
            Thread.sleep(2000L);
            return "two";
        }));
        furtureList.add(completionService.submit(() -> {
            Thread.sleep(3000L);
            return "three";
        }));

        furtureList.add(completionService.submit(() -> {
            Thread.sleep(4000L);
            return "four";
        }));
//            拿到completionService 阻塞队列的头元素,没有则阻塞当前线程
        Future<String> result = completionService.take();
        if (result != null) {
            System.out.println(result.get());

        }
        for (int i = 0; i < 3; i++) {
//            取消任务
            furtureList.get(i).cancel(true);
        }


    }


    @org.junit.Test
    public void queryMutipleSource() {


        BlockingQueue<Integer> bq = new LinkedBlockingQueue<>();

        ExecutorService executor = Executors.newCachedThreadPool();


        Future<Integer> f1 = new FutureTask<Integer>(new Task());

        executor.execute(() -> {
            try {
                bq.put(f1.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        Future<Integer> f2 = new FutureTask<Integer>(new Task());

        executor.execute(() -> {
            try {
                bq.put(f2.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        Future<Integer> f3 = new FutureTask<Integer>(new Task());

        executor.execute(() -> {
            try {
                bq.put(f3.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });


    }

    private static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return null;
        }
    }
}
