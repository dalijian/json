package com.lijian.muti_thread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class MutiThreadApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                MutiThreadApplication.class, args);
    }

}
