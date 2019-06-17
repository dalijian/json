package com.lijian.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Request {
    private InputStream inputStream;
    private String uri;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getUri() {
        return uri;
    }


    public void parse(){
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i=-1;
        }
        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);

        }
        System.out.println("******************");
        try {
            System.out.println(new String(buffer,"utf-8"));
            System.out.println("***************");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(request.toString());
        uri = parseUri(request.toString());
    }
    private String parseUri(String requestString) {
        int index1,index2;
        index1 = requestString.indexOf(" ");
        if (index1 != -1) {
            index2 = requestString.indexOf(" ", index1 + 1); //GET / HTTP/1.1
            return requestString.substring(index1 + 1, index2); // 拿到 url
        }
        return  null;

    }


}
