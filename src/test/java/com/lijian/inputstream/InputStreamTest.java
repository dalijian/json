package com.lijian.inputstream;

import org.junit.Test;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;


public class InputStreamTest {



    @Test
    public void byteArrayInputStreamTest() throws IOException {


        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("hello world".getBytes());
        System.out.println("第一次");
        System.out.println(StreamUtils.copyToString(byteArrayInputStream, Charset.forName("utf-8")));
        System.out.println("第二次");
        System.out.println(StreamUtils.copyToString(byteArrayInputStream, Charset.forName("utf-8")));

    }


    //byteArrayInputStream 支持 mark,reset   可以 重复 读取
    @Test
    public void byteArrayInputStreamTestReset() throws IOException {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("hello world".getBytes());

        System.out.println("第一次");
        System.out.println(StreamUtils.copyToString(byteArrayInputStream, Charset.forName("utf-8")));
        System.out.println("第二次");
        byteArrayInputStream.reset();
        System.out.println(StreamUtils.copyToString(byteArrayInputStream, Charset.forName("utf-8")));

    }


    public static void main(String[] args) {
        DataInputStream input = null;
        try {
            //do something
            // Read all characters, until an EOFException is thrown.
            String file = "alice.txt";
            input = new DataInputStream(new FileInputStream(file));
            while(true) {
                char num;
                try {
                    num = input.readChar();
                    System.out.println("Reading from file: " + num);
                }
                catch (EOFException ex1) {
                    ex1.printStackTrace();
                    break; //EOF reached.
                }
                catch (IOException ex2) {
                    System.err.println("An IOException was caught: " + ex2.getMessage());
                    ex2.printStackTrace();
                }
            }
        }
        catch (IOException ex) {
            System.err.println("An IOException was caught: " + ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            try {
                // Close the input stream.
                input.close();
            }
            catch(IOException ex) {
                System.err.println("An IOException was caught: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
