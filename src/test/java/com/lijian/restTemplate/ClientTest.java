package com.lijian.restTemplate;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ClientTest {

    @Test
    public void restClient(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers=new HttpHeaders();
        List<String> cookies=new ArrayList<>();
//        cookies.add("jeesite.session.id="+this.sessionValue);
        headers.add("Accept","application/json");
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
//        headers.add("User-Agent", " Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        headers.put(HttpHeaders.COOKIE,cookies);
        HttpEntity request=new HttpEntity(null,headers);

        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("clientFlag", "clientFlag");
        requestEntity.add("xml", "xml");
        requestEntity.add("verifyData", "verifyData");

        String s = restTemplate.postForObject("http://www.baidu.com/", requestEntity, String.class);
        ResponseEntity<String> response=restTemplate.postForEntity("http://www.baidu.com/",requestEntity,String.class);
        System.out.println(response.getBody());
//        System.out.println(s);
    }
    @Test
    public void sendTest(){
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        headers.setContentType(type);
        System.out.println(type);
//        HttpEntity<String> requestEntity = new HttpEntity<String>(PostStrUtils.getPostStrFromMap(paramMap),  headers);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8087";
        String msg = restTemplate.getForObject(url,String.class, String.class);
        System.out.println(msg);
    }

}
