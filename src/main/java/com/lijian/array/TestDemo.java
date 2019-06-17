package com.lijian.array;

import org.junit.jupiter.api.Test;

public class TestDemo {
    public static void main(String[] args) {
        person person1 =new person("lijain");
        person person2 = new person("GG");
        person2 =person1;
        person1.setName("li");
        System.out.println(person2);
    }
}
class person{
    private String name;

    public person() {
    }

    public person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "person{" +
                "name='" + name + '\'' +
                '}';
    }
}