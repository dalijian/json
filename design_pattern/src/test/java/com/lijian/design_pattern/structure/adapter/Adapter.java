package com.lijian.design_pattern.structure.adapter;

// 装饰类, 直接关联被适配器类, 同时实现标准接口
public class Adapter implements Target {

    private Adaptee adaptee;

//    通过构造函数传入被适配类对象
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        System.out.println("我是适配器类, 我能适配任何两孔插座, 让它正常工作");
//        这里使用委托的方式完成特殊的功能
        this.adaptee.specificRequest();
    }
}
