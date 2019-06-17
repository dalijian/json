package com.lijian.serializable;

import java.beans.Transient;
import java.io.*;

public class User  implements Serializable {
    public  static String address = "china";
    private String username;
    private transient int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
@Transient
    public int getAge() {
        return age;
    }
@Transient
    public void setAge(int age) {
        this.age = age;
    }

    public User(String username, int age) {

        this.username = username;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
    //序列化 与反序列化 使用 ObjectOutputStream，ObjectInputStream
    public static void main(String[] args) throws IOException {
        User user = new User("lijian", 22);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("/user.txt"));

        outputStream.writeObject(user);

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("/user.txt"));
        int size = inputStream.available();
        byte[] content = new byte[size];

        try {
            User user1 = (User) ( inputStream).readObject();
            System.out.println(user1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
