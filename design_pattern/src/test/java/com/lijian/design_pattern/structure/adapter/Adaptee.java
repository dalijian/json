package com.lijian.design_pattern.structure.adapter;

//已经存在的, 并且具有特殊功能的类, 但是不符合我们既有的标准的接口的类
public class Adaptee {
    public void specificRequest() {
        System.out.println("被适配类, 我是两孔插座, 具有特殊的功能");
    }
}
