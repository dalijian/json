package com.lijian.string;

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
}