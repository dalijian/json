package com.lijian.algorithms.practise;

import org.junit.Test;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/***
 * 桶排序
 * 1. 先 生产 随机数 文件
 * 2. 扫描 文件 确定 最大值，最小值
 * 3. 根据 最大值，最小值 分组 分别（使用 快排） 写入 不同的 文件
 * 4. 将不同 有序文件 写入 同一 文件 中
 */
public class BucketSort {



    @Test
    public void RandomFile() throws IOException {

        FileOutputStream outputStream = new FileOutputStream("random.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        for (int i = 0; i < 1000*1000*100; i++) {
            bufferedOutputStream.write(String.valueOf((new  Random().nextInt(1000000000))).getBytes());
            bufferedOutputStream.write(",".getBytes());
        }
        bufferedOutputStream.close();
    }
@Test
    public void getMinAndMax() throws IOException {

    BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File("random.txt")));

        int max = 0; int min=10;

        // max =999999999 , min= 0

        byte[] charBuffer = new byte[208000];
        int result =-1;
        while ( (result =inputStream.read(charBuffer))!=-1){

            String maxStr = Stream.of(new String(charBuffer).split(",")).max(Comparator.comparing(x -> x)).get();
            if (max < Integer.valueOf(maxStr)) {
                max = Integer.valueOf(maxStr);
            }
            String minStr = Stream.of(new String(charBuffer).split(",")).min(Comparator.comparing(x -> x)).get();
            if (StringUtils.isEmpty(minStr)) {
                continue;
            }
            if (min > Integer.valueOf(minStr)) {
                min = Integer.valueOf(minStr);
            }
            }

        System.out.println("min:" + min);
        System.out.println("max:" + max);

    }

    @Test
    public void  bucketTest() throws IOException {
       int   max =999999999 ;

        FileOutputStream outputStream1 = new FileOutputStream("random_1_100000000.txt");
        BufferedOutputStream bufferedOutputStream1 = new BufferedOutputStream(outputStream1);

        FileOutputStream outputStream2 = new FileOutputStream("random_1100000000_200000000.txt");
        BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(outputStream2);


        FileOutputStream outputStream3 = new FileOutputStream("random_200000000_300000000.txt");
        BufferedOutputStream bufferedOutputStream3 = new BufferedOutputStream(outputStream3);


        FileOutputStream outputStream4 = new FileOutputStream("random_300000000_400000000.txt");
        BufferedOutputStream bufferedOutputStream4 = new BufferedOutputStream(outputStream4);


        FileOutputStream outputStream5= new FileOutputStream("random_400000000_500000000.txt");
        BufferedOutputStream bufferedOutputStream5 = new BufferedOutputStream(outputStream5);


        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File("random.txt")));

        byte[] buffer = new byte[20480];
        int result =-1;
        while ( (result =inputStream.read(buffer))!=-1){
            Stream.of(new String(buffer).split(",")).forEach(s->{
                if (!StringUtils.isEmpty(s)) {
                    int m = Integer.valueOf(s);

                    if (m < 100000000) {
                        try {
                            bufferedOutputStream1.write(s.getBytes());
                            bufferedOutputStream1.write(",".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if (m < 200000000) {
                        try {
                            bufferedOutputStream2.write(s.getBytes());
                            bufferedOutputStream2.write(",".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if (m < 300000000) {
                        try {
                            bufferedOutputStream3.write(s.getBytes());
                            bufferedOutputStream3.write(",".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if (m < 400000000) {
                        try {
                            bufferedOutputStream4.write(s.getBytes());
                            bufferedOutputStream4.write(",".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if (m < 500000000) {
                        try {
                            bufferedOutputStream5.write(s.getBytes());
                            bufferedOutputStream5.write(",".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        bufferedOutputStream1.close();
        bufferedOutputStream2.close();
        bufferedOutputStream3.close();
        bufferedOutputStream4.close();
        bufferedOutputStream5.close();

    }
}
