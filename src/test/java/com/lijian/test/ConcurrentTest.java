package com.lijian.test;

import com.lijian.json.gson.User;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<User> completionService = new ExecutorCompletionService<>(executorService);
        Future result =null ;
        List<Integer> list=Arrays.asList(1, 2, 3, 4, 5);
        for (final Integer i: list)
        {
           completionService.submit(() -> new User("lijian", i, ""));

        }
        for (Integer i: list) {
            Future<User> f = completionService.take();
          User  user=   f.get();
            System.out.println(user);
        }

        System.out.println("lijian");
    }
    static class nest implements Callable{

        @Override
        public Object call() throws Exception {
            Thread.sleep(400000);

            return null;
        }
    }
}
