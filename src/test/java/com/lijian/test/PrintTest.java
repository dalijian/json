package com.lijian.test;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrintTest {
    public static void main(String[] args) {
//        for (int i = 0; i < 200; i++) {
//            System.out.println((char) i);
//
//        }

//        System.out.println((byte)'\t');

        String filePath = System.getProperty("user.dir") + File.separator + "readme.md";
        try {
            InputStream inputStream = new FileInputStream(new File(filePath));
            int i;
            while ((i =inputStream.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test(){
        int intValue =Float.valueOf("123.000").intValue();
        float floatValue = Float.valueOf("123.000");
        if (floatValue - intValue == 0) {
            System.out.println("相同");
        }
        System.out.println(Float.valueOf("123.000").intValue());

    }

    @Test
    public void testPP(){
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("lijian", null);
        System.out.println(map.getOrDefault("lijian", ""));
        System.out.println(String.valueOf(map.getOrDefault("lijian","")));
    }


    @Test
    public void testPP2() throws IOException {
        List<Integer> list = Stream.of(1,2,4,5,6).collect(Collectors.toList());
        System.out.println(list.containsAll(Stream.of(3,4,5).collect(Collectors.toList())));
        System.out.println(new File("../../../text.txt").getCanonicalPath());
        System.out.println("title".toUpperCase(Locale.FRENCH));
    }
}
