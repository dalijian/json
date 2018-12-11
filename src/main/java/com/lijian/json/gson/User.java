package com.lijian.json.gson;

import com.google.gson.annotations.Expose;

//简单说来就是需要导出的字段上加上@Expose 注解，
public class User {
    /**
     * @Expose //
     *     @Expose(deserialize = true,serialize = true) //序列化和反序列化都都生效，等价于上一条
     *     @Expose(deserialize = true,serialize = false) //反序列化时生效
     *     @Expose(deserialize = false,serialize = true) //序列化时生效
     *     @Expose(deserialize = false,serialize = false) // 和不写注解一样
     */


@Expose
    public String name;

    public int age;
@Expose
    public String email;

    public  User(){

    }
    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
