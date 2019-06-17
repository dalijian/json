package com.lijian.test;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {

    @Test
    public void simple(){

        int cpuNum = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(cpuNum);

        service.submit(()->{

        });


    }
}
