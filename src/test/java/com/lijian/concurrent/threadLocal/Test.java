package com.lijian.concurrent.threadLocal;

public class Test {

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    DateFormat sdf = new DateFormat();
                    System.out.println(sdf.parseDate("2016-10-19"));
                }
            });
            thread.start();
        }
    }
}