package com.lijian.DatePlus;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DatePlusTest {

/*
     当前日期加上天数后的日期
     @param num 为增加的天数
       @return*/

    public static String plusDay2(int num) {
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currdate = format.format(d);
        System.out.println("现在的日期是：" + currdate);

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        d = ca.getTime();
        String enddate = format.format(d);
        System.out.println("增加天数以后的日期：" + enddate);
        return enddate;

    }
    @Test
    public void test(){
        List<String> list= new ArrayList<>();

        for (int i = -0; i > -7; i--) {
            list.add(plusDay2(i));
        }
        System.out.println(list.size());
        System.out.println(list);
    }
}