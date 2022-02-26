package com.lijian.string;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

public class TestJVM {
    public static void main(String[] args) {
        create();
    }
     
    public static void create() {
        String m = "ER-";
        for (int i = 0; i < 2; i++) {
            String format = String.format("%05d", i);   
            System.out.println(m+format);
        }
    }

    @Test
    public void compareToTest(){
        int flag = "2020-09-09".compareTo("2020-09-09");
        System.out.println(flag);
        int flag2 = "2020-09-09".compareTo("2020-09-08");
        System.out.println(flag2);
        int flag3 = "2020-09-07".compareTo("2020-09-08");
        System.out.println(flag3);
        Calendar.getInstance();
    }
}