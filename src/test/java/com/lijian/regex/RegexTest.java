package com.lijian.regex;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.stream.Collectors;

public class RegexTest {

    @Test
    public void groupTest() {
        Pattern pattern = Pattern.compile("^0*");
        Matcher matcher = pattern.matcher("00012345.1234235");

        Pattern pattern1 =Pattern.compile("/*?/.jpg");

        String str = "/picture/NMGHHHT_BGHDJD_20180705/001/7.jpg";
        Matcher matcher1 = pattern1.matcher(str);

        while (matcher.find()) {
            String groupStr = matcher.group();
            System.out.println(groupStr);
        }
//        List<String> strList = Stream.of(str.split("/")).collect(Collectors.toList());
//        System.out.println(strList.get(strList.size()-2)+"/"+strList.get(strList.size()-1));
    }


    public Map<String, String> regexParse(String str){
        Pattern pattern =Pattern.compile("(\\d+).*?([\\u4e00-\\u9fa5]+)");
//        String str = "110101 　　东城区";
        Matcher matcher = pattern.matcher(str);
        Map<String,String> map = new HashMap<>();
        while (matcher.find()) {
           int count = matcher.groupCount();
//            for (int i = 0; i < count; i++) {
//                System.out.println(matcher.group(i + 1));
//                Map<String,String> map = new HashMap<>();
//            }
        
            map.put(matcher.group(1), matcher.group(2));
        }
        return map;


    }

    @Test
    public void readLine() {
        ObjectMapper mapper = new ObjectMapper();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("cityCode.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<Map<String, String>> result = bufferedReader.lines().map(x -> (x).trim()).map(x -> regexParse(x)).collect(Collectors.toList());
        try {
            mapper.writeValue(new File("cityCode.json"), result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    //    解决特殊字符替换问题
    @Test
    public void quoteReplacementTest() {
        String regex = "\\$\\{.*?\\}";
        String replace = "${product}";
        String content = "product is ${productName}.";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        String replaceAll = m.replaceAll(Matcher.quoteReplacement(replace));

        System.out.println("content: " + content);
        System.out.println("replaceAll: " + replaceAll);
    }

    @Test
    public void replaceTest() {
        String regex = "can";
        String replace = "can not";
        String content = "I can because I think I can.";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        System.out.println("content: " + content);
        System.out.println("replaceFirst: " + m.replaceFirst(replace));
        System.out.println("replaceAll: " + m.replaceAll(replace));
    }

    @Test
    public  void appendTest() {
        String regex = "can";
        String replace = "can not";
        String content = "I can because I think I can.";
        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();

        System.out.println("content: " + content);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        while (m.find()) {
            m.appendReplacement(sb, replace);
        }
        System.out.println("appendReplacement: " + sb);
        m.appendTail(sb);
        System.out.println("appendTail: " + sb);
    }
}
