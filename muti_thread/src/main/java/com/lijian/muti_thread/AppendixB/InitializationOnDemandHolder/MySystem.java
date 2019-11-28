package com.lijian.muti_thread.AppendixB.InitializationOnDemandHolder;

import java.util.Date;

// Initialization on Demand Holder  模式
public class MySystem {

//    使用Holder的类的初始化 来创建唯一的实例，并确保线程安全
//    因为在java规范中，类的初始化是线程安全的
    private static class Holder {
        public static MySystem instance = new MySystem();
    }
    private Date date = new Date();
    private MySystem() {
    }
    public Date getDate() {
        return date;
    }
    public static MySystem getInstance() {
        return Holder.instance;
    }
}
