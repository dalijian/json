package com.lijian.test;

import java.net.HttpURLConnection;

public class CloneTest {
    public static void main(String[] args) {
        String[] strArray = new String[]{"1", "2"};
        String[] strArrayClone = strArray.clone();
        System.out.println(strArray);
        System.out.println(strArrayClone);
        //clone  后地址 不同


    }
}
