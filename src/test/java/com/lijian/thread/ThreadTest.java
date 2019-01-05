package com.lijian.thread;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadTest {
    @Test
    public void test(){
        Runnable task = ()->{
            System.out.println("hello world");
        };
        Thread myThread = new Thread(task);
        myThread.start();
        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Future future = (Future) Executors.newFixedThreadPool(1).submit(task).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        设置守护线程
        Thread daemonThread = new Thread();
        daemonThread.setDaemon(true);
        daemonThread.start();
    }

}
