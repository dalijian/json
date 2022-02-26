package com.lijian.concurrent.threadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
*日期格式化
**/
public class DateFormat {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    public Date parseDate(String str ){
        if(str == null)
            return null;
        try {
            return SDF.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}




