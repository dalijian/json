package com.lijian.test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
    private static SimpleDateFormat simpleOrigin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Test
    public void test() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        Date  date = calendar.getTime();
        System.out.println(simpleOrigin.format(date));

    }
    private Date date = new Date();

    public CalendarTest() {
        this.date = getCalendarDate();
    }
    private Date getCalendarDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        Date  date = calendar.getTime();
        return date;
    }

    @Override
    public String toString() {
        return "CalendarTest{" +
                "date=" + date +
                '}';
    }

    public static void main(String[] args) {
        CalendarTest calendarTest = new CalendarTest();
        System.out.println(calendarTest);
    }
}
