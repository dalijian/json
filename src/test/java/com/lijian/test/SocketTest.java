package com.lijian.test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888, 1, InetAddress.getByName("127.0.0.1"));
        while (true) {
            Socket socket= serverSocket.accept();
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = inputStream.read(buffer);
            System.out.println(new String(buffer,0,length));
//            int length;
//            while ((length = inputStream.read(buffer)) != -1) {
//                result.write(buffer, 0, length);
//            }
//            System.out.println(result.toString("UTF-8"));


//            final int bufferSize = 1024;
//            final char[] buffer = new char[bufferSize];
//            final StringBuilder out = new StringBuilder();
//            Reader in = new InputStreamReader(inputStream, "UTF-8");
//            for (; ; ) {
//                int rsz = in.read(buffer, 0, buffer.length);
//                if (rsz < 0)
//                    break;
//                out.append(buffer, 0, rsz);
//            }
//            out.toString();
            PrintWriter printWriter = new PrintWriter(outputStream);
            outputStream.write("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n".getBytes());
            outputStream.write("hello world".getBytes());
//            printWriter.println("HTTP/1.1 200 Ok");
//            printWriter.println("Connection: keep-Alive");
//            printWriter.println("Content-Type: text/html");
//            printWriter.println();

//            printWriter.println("hello world" +
//                    "");
        }

    }
}
