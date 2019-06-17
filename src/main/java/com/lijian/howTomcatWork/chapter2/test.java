package com.lijian.howTomcatWork.chapter2;

public class test {
    public static void main(String[] args) {
        try {
            Class.forName("com.lijian.howTomcatWork.chapter2.Constants");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
