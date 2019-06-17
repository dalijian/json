package com.lijian.howTomcatWork.chapter2;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

public class Response implements ServletResponse {

    private static final int BUFFER_SIZE= 1024;
    Request request;
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

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
    public void sendStaticResource() {
        byte [] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;

        File file = new File(Constants.WEB_ROOT, request.getUri());
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
    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override // PrintWriter 类的构造方法的第二个参数是一个布尔值表明是否允许
    //自动刷新。传递 true 作为第二个参数将会使任何 println 方法的调用都会刷新输出(output)
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(outputStream,true);
    }

    @Override
    public void setCharacterEncoding(String charset) {

    }

    @Override
    public void setContentLength(int len) {

    }

    @Override
    public void setContentLengthLong(long len) {

    }

    @Override
    public void setContentType(String type) {

    }

    @Override
    public void setBufferSize(int size) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale loc) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
