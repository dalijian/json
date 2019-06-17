package com.lijian.concurrent.completionService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class WeatherTest {
    private static final String zhonghuawangnianliWeatherUrl = "http://wthrcdn.etouch.cn/weather_mini?city=青岛";
    private static final String jinshanWeatherUrl = "http://weather.123.duba.net/static/weather_info/101120201.html?callback=";
    private static final String xiaomiWeatherUrl = "http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId=101120201";


    public static String getWeather(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//    List<String> cookies=new ArrayList<>();
//        cookies.add("jeesite.session.id="+this.sessionValue);
        headers.add("Accept", "application/json");
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("User-Agent", " Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
//        headers.put(HttpHeaders.COOKIE,cookies);
        HttpEntity request = new HttpEntity(null, headers);
//        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        headers.setContentType(type);

        System.out.println(url);
//        String url = "http://localhost:8087";
        String msg = restTemplate.getForObject(url, String.class, String.class);
//        System.out.println(msg);
        return msg;
    }

//    public static String postWeather(String url){
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers=new HttpHeaders();
//        List<String> cookies=new ArrayList<>();
////        cookies.add("jeesite.session.id="+this.sessionValue);
//        headers.add("Accept","application/json");
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
////        headers.add("User-Agent", " Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
//        headers.put(HttpHeaders.COOKIE,cookies);
//        HttpEntity request=new HttpEntity(null,headers);
//
//        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
//        requestEntity.add("clientFlag", "clientFlag");
//        requestEntity.add("xml", "xml");
//        requestEntity.add("verifyData", "verifyData");
//
//        String s = restTemplate.postForObject("http://www.baidu.com/", requestEntity, String.class);
//        ResponseEntity<String> response=restTemplate.postForEntity("http://www.baidu.com/",requestEntity,String.class);
//        System.out.println(response.getBody());
////        System.out.println(s);
//    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Executor executor = Executors.newCachedThreadPool();
        CompletionService<String> completionService = new ExecutorCompletionService(executor);
        List<Future> furtureList = new ArrayList<>();
        furtureList.add(completionService.submit(() -> {
            return getWeather(zhonghuawangnianliWeatherUrl);
        }));
        furtureList.add(completionService.submit(() -> {
            return getWeather(jinshanWeatherUrl);
        }));

        furtureList.add(completionService.submit(() -> {
            return getWeather(xiaomiWeatherUrl);
        }));
        Future<String> result = completionService.take();
        if (result != null) {
            System.out.println(result.get());

        }
//            for (Future future : furtureList) {
//                System.out.println(future.get());
//            }
//        }
        for (int i = 0; i < 3; i++) {
            furtureList.get(i).cancel(true);
        }
    }

}


