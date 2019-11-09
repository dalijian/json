package com.lijian.io;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IoTest {


    public static void main(String[] args) throws IOException {

        //BufferedReader是可以按行读取文件
        FileInputStream inputStream = new FileInputStream("d://download.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }

        //close
        inputStream.close();
        bufferedReader.close();

    }

    @Test
    public void readLine() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("cityCode.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<List<String>> result = bufferedReader.lines().map(x -> (x).trim()).map(x -> x.replaceAll("[\\s]*", "&")).map(x -> Stream.of(x.split("&")).collect(Collectors.toList())).collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    public void regexBlank() {

        String str = "110101 　　东城区";

        List<String> list = Stream.of(str.split("\\x20")).collect(Collectors.toList());

        System.out.println(list);

    }
}

