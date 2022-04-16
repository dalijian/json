package com.lijian.zip.stream;


import com.lijian.zip.stream.Employee;

import java.io.*;
import java.util.zip.*;

public class SaveEmployee {
    public static void main(String argv[]) throws
            Exception {
        // create some objects
        Employee sarah = new Employee("S. Jordan", 28,
                56000);
        Employee sam = new Employee("S. McDonald", 29,
                58000);
        // serialize the objects sarah and sam
        FileOutputStream fos = new FileOutputStream("serialize_bean");
        GZIPOutputStream gz = new GZIPOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(gz);
        oos.writeObject(sarah);
        oos.writeObject(sam);
        oos.flush();
        oos.close();
        fos.close();
    }
}