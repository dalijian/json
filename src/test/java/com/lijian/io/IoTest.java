package com.lijian.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class IoTest {

@Test
public static void main(String[] args) throws IOException
{

    //BufferedReader是可以按行读取文件
    FileInputStream inputStream = new FileInputStream("d://download.txt");
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

    String str = null;
    while((str = bufferedReader.readLine()) != null)
    {
        System.out.println(str);
    }

    //close
    inputStream.close();
    bufferedReader.close();

}
}

