package com.lijian.concurrent.completablefuture;

import org.junit.Test;
import org.omg.CORBA.TIMEOUT;

import java.util.Comparator;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) {
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(()->{
            System.out.println("T1: 洗水壶");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("T1: 烧开水");
            sleep(15, TimeUnit.SECONDS);

        });

        CompletableFuture<String> f2= CompletableFuture.supplyAsync(()->{
            System.out.println("T2: 洗茶壶");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("T2: 洗茶杯");
            sleep(2, TimeUnit.SECONDS);

            System.out.println("T2: 拿茶叶");
            sleep(1, TimeUnit.SECONDS);

            return "龙井";

        });

        CompletableFuture<String> f3 = f1.thenCombine(f2,(__,tf)->{
            System.out.println("T1: 拿到茶叶：" + tf);
            System.out.println("T1: 泡茶");
            return "上茶"+tf;

        });
        System.out.println(f3.join());

       Runtime.getRuntime().availableProcessors();//当前cup核数
    }

   static void sleep(int t, TimeUnit unit) {
        try {
            unit.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//串行
@Test
    public void Serial(){
        CompletableFuture completableFuture1 = CompletableFuture.runAsync(()->{
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "   "+i);

            }
        },Executors.newFixedThreadPool(2,(Runnable r)->{
            Thread thread = new Thread(r);
            thread.setName("serialThread" + "_" + thread.getId());
            return thread;
        }));
    CompletableFuture completableFuture2 = completableFuture1.thenRun(()->{
        for (int i = 5; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() +" "+ i);
        }
    });
    try {
        Thread.sleep(2000L);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
        // 异步串行
@Test
    public void serialAsyn(){
    CompletableFuture<String> f0 = CompletableFuture.supplyAsync(() -> "hello world").thenApply(s -> s + " QQ").thenApply(String::toUpperCase);
//        异步转同步
    f0.join();
    System.out.println(f0.join());
}


    //And  汇聚
@Test
    public void convergeAnd(){

    CompletableFuture<String> f1 = CompletableFuture.supplyAsync(()->{
        int t = getRandom(5, 10);
        sleep(t, TimeUnit.SECONDS);
        return String.valueOf(t);
    });
    CompletableFuture<String> f2= CompletableFuture.supplyAsync(()->{
        int t = getRandom(5, 10);
        sleep(t, TimeUnit.SECONDS);
        return String.valueOf(t);
    });
    CompletableFuture<String> f3 = f1.thenCombine(f2, (s1, s2) -> {
       return s1+" "+s2;
    });
    System.out.println(f3.join());
}

@Test
    public void convergeOr(){

        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(()->{
            int t = getRandom(5, 10);
            System.out.println("f1 getRandom:" + t);
            sleep(t, TimeUnit.SECONDS);
            return String.valueOf(t);
        });
    CompletableFuture<String> f2= CompletableFuture.supplyAsync(()->{
            int t = getRandom(5, 10);
        System.out.println("f2 getRandom:" + t);
            sleep(t, TimeUnit.SECONDS);
            return String.valueOf(t);
        });
    CompletableFuture<String> f3 = f1.applyToEither(f2, s -> s);
    System.out.println(f3.join());

}

    private int getRandom(int i, int j) {
        Random random = new Random();
        return random.nextInt(j);
    }


    //处理异常
    @Test
    public void exception(){
        CompletableFuture<Integer> f0 = CompletableFuture
                .supplyAsync(() -> 7 / 0)
                .thenApply(r -> r * 10)
                .exceptionally(e -> 0);
        System.out.println(f0.join());
    }

    @Test
    public void handleTest(){
        CompletableFuture<Stream<String>> objectCompletableFuture = CompletableFuture.supplyAsync(() -> Stream.of("ljian".split("")).map(x->{
            System.out.println(1 / 0);
            return x;
        })
                .collect(Collectors.toList())).thenApply(x -> x.stream().map(y -> y.toUpperCase()))
                .handle((x, e) -> {
                    if (e instanceof RuntimeException) {
                        e.printStackTrace();
                    }
                    e.printStackTrace();
                    return x;
                });
        Stream<String> flag = objectCompletableFuture.join();
        System.out.println(flag.collect(Collectors.joining(
        )));

    }

    @Test
    public void asy(){

        CompletableFuture.runAsync(()-> {
            for (int i = 0; i < 1000; i++) {
                System.out.println(i);
            }
        });

        System.out.println("123456");
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
