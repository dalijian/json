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
        Future<String> result = completionService.take();
        if (result != null) {
            System.out.println(result.get());

        }
        for (int i = 0; i < 3; i++) {
            furtureList.get(i).cancel(true); }


    }
}
