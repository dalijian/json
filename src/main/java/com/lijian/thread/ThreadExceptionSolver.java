package com.lijian.thread;

import javax.management.RuntimeMBeanException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



//使用 callable 接口  方法支持 抛出异常
public class ThreadExceptionSolver {

    public static void main(String[] args)  {


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> result = executorService.submit(new SubThread());
        try {
         String str=   result.get();
            System.out.println(str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    public static class SubThread implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("手动抛异常");
            touchException();
            return "lijain";
        }

        public void touchException(){
            throw new RuntimeException();
        }
    }
}
