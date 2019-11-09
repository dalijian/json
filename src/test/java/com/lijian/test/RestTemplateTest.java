package com.lijian.test;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RestTemplateTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(1000, (Runnable runable) -> {
            Thread thread = new Thread(runable);
            thread.setName("thread_" + thread.getId());
            return thread;
        });
        for (int i = 0; i < 10; i++) {
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
        SocketAddress socketAddress = null;
        try {
            socketAddress = new InetSocketAddress(InetAddress.getByName("172.19.12.248"), 8888);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        Proxy proxy = new Proxy(Proxy.Type.HTTP, socketAddress);
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(20000);
        httpRequestFactory.setConnectTimeout(10000);
        httpRequestFactory.setProxy(proxy);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        String url = "http://www.baidu.com";
        String msg = restTemplate.getForObject(url, String.class, String.class);
        System.out.println(msg);

//        try {
//            System.out.println(Threa.currentThread().getName());
//            Threa.sleep(100000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
