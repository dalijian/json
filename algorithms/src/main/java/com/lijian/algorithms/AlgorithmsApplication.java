package com.lijian.algorithms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AlgorithmsApplication {
    Logger logger = LoggerFactory.getLogger(AlgorithmsApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(AlgorithmsApplication.class, args);
    }


    @PostConstruct
    public void test(){
        for (int i = 0; i < 100000; i++) {
            logger.info("this is info log {}", i);
            logger.error("this is info log {}", i);
            logger.debug("this is info log {}", i);
            logger.warn("this is info log {}", i);
        }

    }
}
