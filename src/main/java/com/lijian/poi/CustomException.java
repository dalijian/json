package com.lijian.poi;

public class CustomException extends Exception {
    String err;
    String sysErr;
    public CustomException(String s) {
        super(s);
    }

    public CustomException(String s, String message) {
        s=err;
        sysErr=message;
    }
}
