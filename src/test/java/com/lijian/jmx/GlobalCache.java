package com.lijian.jmx;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class GlobalCache {
   public static Map<String,String> MAP= new ImmutableMap.Builder<String,String>().put("name","lijian").build();
}
