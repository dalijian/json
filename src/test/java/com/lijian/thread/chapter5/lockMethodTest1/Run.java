package com.lijian.thread.chapter5.lockMethodTest1;

import java.io.Serializable;

public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        service.serviceMethod1();
    }
}
