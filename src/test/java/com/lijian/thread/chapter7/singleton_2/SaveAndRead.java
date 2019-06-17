package com.lijian.thread.chapter7.singleton_2;

import java.io.*;

public class SaveAndRead {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyObject myObject =MyObject.getInstance4();

        FileOutputStream fileOutputStream = new FileOutputStream(new File("myObjectFile.txt"));

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(myObject);

        objectOutputStream.close();
        fileOutputStream.close();
        System.out.println(myObject.hashCode());

        FileInputStream fileInputStream = new FileInputStream(new File("myObjectFile.txt"));

        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        MyObject myObject1 = (MyObject) objectInputStream.readObject();

        objectInputStream.close();
        fileInputStream.close();
        System.out.println(myObject.hashCode());

    }
}
