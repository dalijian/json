package com.lijian.thread.chapter4.pipeInputOutput;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class ReadData {
    public void readMethod(PipedInputStream inputStream) {
        System.out.println("read:");
        byte[] byteArray = new byte[20];
        int readLength = 0;
        try {
            readLength = inputStream.read(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (readLength != -1) {
            String newData = new String(byteArray);
            System.out.println(newData);
            try {
                readLength = inputStream.read(byteArray);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println();
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
