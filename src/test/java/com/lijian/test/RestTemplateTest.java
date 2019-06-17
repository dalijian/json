package com.lijian.test;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RestTemplateTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(1000, (Runnable runable) -> {
            Thread thread = new Thread(runable);
            thread.setName("thread-" + thread.getId());
            return thread;
        });
        for (int i = 0; i < 10000; i++) {



            threadPool.execute(new Task());

        }
        threadPool.shutdown();

    }
}
class Task extends Thread {

    @Override
    public void run() {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        headers.setContentType(type);
        System.out.println(type);
//        HttpEntity<String> requestEntity = new HttpEntity<String>(PostStrUtils.getPostStrFromMap(paramMap),  headers);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8087";
        String msg = restTemplate.getForObject(url,String.class, String.class);
        System.out.println(msg);

//        try {
//            System.out.println(Threa.currentThread().getName());
//            Threa.sleep(100000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
