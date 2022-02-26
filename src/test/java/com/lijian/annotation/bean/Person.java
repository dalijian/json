package com.lijian.annotation.bean;

import com.lijian.annotation.NotSelfBlank;

public class Person implements Animal {

    @NotSelfBlank(message = "不能为空")
     String name;


    @NotSelfBlank(message = "score 不能为空")
     float score;

    @NotSelfBlank(message = "score 不能为空")
     int age;
    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person() {

    }

    public Person(String name, float score, int age) {

        this.name = name;
        this.score = score;
        this.age = age;
    }
}