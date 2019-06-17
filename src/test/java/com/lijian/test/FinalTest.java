package com.lijian.test;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FinalTest {


    @Test
    public void finalTest1(){
        Point point =new Point(1,1);
        System.out.println(point);

    }


    @Test
    public void finalTest2(){
        Map<String,String>  modelMap = new HashMap<>();
        modelMap.put("1", "one");
  //不可变复制 安全复制
        Map<String, String> unmodifiableMap = Collections.unmodifiableMap(modelMap);

        unmodifiableMap.put("2", "two");
        System.out.println(unmodifiableMap);
    }


}
