package com.lijian.log;

public class LogTest {
    public static void main(String[] args) {
        FileLog fileLog = new FileLog();
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fileLog.log(String.valueOf(System.currentTimeMillis()));
        }
    }
}
