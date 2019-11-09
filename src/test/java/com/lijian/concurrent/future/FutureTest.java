package com.lijian.concurrent.future;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.*;

public class FutureTest {

    private final ConcurrentMap<Object,Future<String>> taskCache = new ConcurrentHashMap<>();

    private String executionTask(final String taskName) {
        while (true) {
            Future<String> future = taskCache.get(taskName);
            if (future == null) {
                Callable<String> task = () -> taskName;
                FutureTask<String> futureTask = new FutureTask<>(task);
                future = taskCache.putIfAbsent(taskName, futureTask);
                if (future == null) {
                    future=futureTask;
                    futureTask.run();
                }
            }
            try{
                return future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Callable<String> callableA = ()-> {


            InputStream inputStream = new FileInputStream("E:/maven-repository.zip");
//            byte[] content = new byte[inputStream.available()];
//            inputStream.read(content);
            return String.valueOf(inputStream.available());
        };
        Callable<String> callableB = ()-> {


            InputStream inputStream = new FileInputStream("E:/《Apache Kafka源码剖析》@www.java1234.com.pdf");
//            Thread.sleep(5000);

            return String.valueOf(inputStream.available());
        };

        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<String> resultA = pool.submit(callableA);
        Future<String> resultB = pool.submit(callableB);
        System.out.println("begin");
        try {
            System.out.println("amount=" + resultA.get() +","+ resultB.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("over");

    }
    //华罗庚-泡茶
    @Test
    public void tea(){
        FutureTask<String> ft2 = new FutureTask<>(new T2Task());

        FutureTask<String> ft1 = new FutureTask<>(new T1Task(ft2));

        Thread T1 = new Thread(ft1);

        T1.start();

        Thread T2 = new Thread(ft2);

        T2.start();

        try {
            System.out.println(ft1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
class  T1Task implements Callable<String>{
    FutureTask<String> ft2 ;

    public T1Task(FutureTask<String> ft2) {
        this.ft2 = ft2;
    }

    @Override
    public String call() throws Exception {
        System.out.println("T1: 洗水壶");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T1: 烧开水");
        TimeUnit.SECONDS.sleep(15);

        String tf=ft2.get();
        System.out.println("T1: 拿茶叶：" + tf);
        System.out.println("T1:泡茶");
        return "上茶:"+tf;

    }
}
class T2Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("T2: 洗茶壶");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T2: 洗茶杯");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T2: 拿茶叶");
        TimeUnit.SECONDS.sleep(1);
        return "龙井";
    }
}