package com.lijian.thread.chapter4.pipeReaderWriter;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedReader;

public class ReadData {
    public void readMethod(PipedReader  inputStream) throws IOException {
        System.out.println("read:");
        char [] byteArray = new char[20];
        int  readLength = 0;

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
