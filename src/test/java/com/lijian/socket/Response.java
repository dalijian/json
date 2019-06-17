package com.lijian.socket;

import java.io.*;

public class Response {
    private static final int BUFFER_SIZE = 1024;
    Request request;                            //封装 request
    OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void setStaticResource() {
        byte [] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;

        File file = new File(HttpServer.WEB_ROOT, request.getUri());
        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            int ch = 0;
            try {
                ch = fis.read(bytes, 0, BUFFER_SIZE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (ch != -1) {
                try {
                    outputStream.write(bytes, 0, ch);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        else {
            String errorMessage = "HTTP/1.1 404 FILE NOT FOUND\r\n" +
                    "Content-Type:text/html\r\n" +
                    "Content-Length:23\r\n" +
                    "\r\n" +
                    "<h1>File not found </h1>";
            try {
                outputStream.write(errorMessage.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
    }
}
