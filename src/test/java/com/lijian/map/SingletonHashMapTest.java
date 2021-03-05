package com.lijian.map;

import org.junit.Test;

import java.util.Collections;
import java.util.Map;

public class SingletonHashMapTest {

    @Test
    public void singletonTest(){

        Map<String, String> map = Collections.singletonMap("name", "lijian");
//        map.put("age", String.valueOf(20));

        System.out.println(map);


    }
}
