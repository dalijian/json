package com.lijian.thread.chapter4.pipeInputOutput;

import java.io.IOException;
import java.io.PipedOutputStream;

public class WriteData {

    public void writeMethod(PipedOutputStream outputStream) {
        System.out.println("write:");
        for (int i = 0; i < 300; i++) {
            String outData = "" + (i + 1);
            try {
                outputStream.write(outData.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println();
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
