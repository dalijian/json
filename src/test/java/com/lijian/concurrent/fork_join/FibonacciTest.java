package com.lijian.concurrent.fork_join;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciTest {


    public static void main(String[] args){
        //创建分治任务线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        //创建分治任务
        Fibonacci fibonacci = new Fibonacci(40);
        //调用invoke()启动分治任务
        Integer result = forkJoinPool.invoke(fibonacci);
        System.out.println(result);
    }

    // 有返回值
    static class Fibonacci extends RecursiveTask<Integer> {
        final int n;
        Fibonacci(int n){
            this.n=n;
        }
        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;

            }
            Fibonacci fibonacci = new Fibonacci(n - 1);
//            异步执行   子任务
            fibonacci.fork();
            Fibonacci fibonacci2 = new Fibonacci(n - 2);
//            join 阻塞当前线程等待子任务执行结束
            return fibonacci2.compute() + fibonacci.join();

        }
    }

}
