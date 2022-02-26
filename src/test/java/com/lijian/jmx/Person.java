package com.lijian.jmx;

public class Person implements PersonMBean {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String sayHello(String hello) {
        System.out.println("GlobalCache.MAP.size:"+GlobalCache.MAP.size());
        GlobalCache.MAP.clear();
        System.out.println("GlobalCache.MAP.size:"+GlobalCache.MAP.size());
        System.out.println(hello);
        return this.name+":"+hello;
    }
}
