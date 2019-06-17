package com.lijian.thread.chapter4.pipeReaderWriter;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PipedWriter;

public class WriteData {

    public void writeMethod(PipedWriter outputStream) {
        System.out.println("write:");
        for (int i = 0; i < 300; i++) {
            String outData = "" + (i + 1);
            try {
                outputStream.write(outData);
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
