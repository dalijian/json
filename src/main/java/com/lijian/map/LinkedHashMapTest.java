package com.lijian.map;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

public class LinkedHashMapTest {

    // 当 设置 accessOrder 为 true  后 ， linkedhashmap 就是 一个  LRU cache
    @Test
    public void linkedHashTest(){


        LinkedHashMap<String, String> map = new LinkedHashMap(100,10, true);

        map.put("name", "lijian");
        map.put("age", "20");
        map.put("school", "five middle school");
        map.put("identity", "anhui");

        System.out.println(map);
        map.get("age");
        System.out.println(map);

    }
}
