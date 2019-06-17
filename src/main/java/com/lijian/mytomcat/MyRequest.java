package com.lijian.mytomcat;

import java.io.IOException;
import java.io.InputStream;

public class MyRequest {

    private String url;
    private String method;

////    GET https://t-i.gridsumdissector.com/v/?gscmd=impress&gid=gad_167_dadey16n&mid=[TENCENTSOID]&iesid=__IESID__&txp=__TXP__&ts=__TS__ HTTP/1.1
//    Host: t-i.gridsumdissector.com
//    Connection: keep-alive
//    Accept: image/webp,image/*,*/*;q=0.8
//    User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7
//    Referer: https://www.qq.com/?fromdefault
//    Accept-Encoding: gzip, deflate, sdch
//    Accept-Language: zh-CN,zh;q=0.8
//    Cookie: GRIDSUMID=39fbfafecffa437e8dd3d9b43f3fcf71
    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];

        int length =0;
        if ((length = inputStream.read(httpRequestBytes)) > 0) {
            httpRequest = new String(httpRequestBytes, 0, length);

            String httpHead = httpRequest.split("\n")[0];
            url = httpHead.split("\\s")[1];
            method = httpHead.split("\\s")[0];
            System.out.println(this);

        }
        }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "MyRequest{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
