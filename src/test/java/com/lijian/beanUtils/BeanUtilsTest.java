package com.lijian.beanUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BeanUtilsTest {
    @Test
    public void test3() throws Exception
    {
        //1.生成对象
        Map<String,Object> map = new HashMap<String,Object>();

        //2.给一些参数
        map.put("id", 2);
        map.put("name", "EZ");
        map.put("age", 22);
        map.put("classID", 3);
        map.put("birthday", new Date());

        //需求：把map的属性值拷贝到S中
        Student s = new Student();
        BeanUtils.copyProperties(s, map);

        System.out.println(s);
    }
}
