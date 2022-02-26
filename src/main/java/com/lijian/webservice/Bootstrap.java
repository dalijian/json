package com.lijian.webservice;

import javax.xml.ws.Endpoint;

public class Bootstrap {
    public static void main(String[] args) {
        String address = "http://127.0.0.1:12345/webService_02/Webservice";
        Endpoint.publish(address, new WebServiceImpl());
        System.out.println("webService发布成功");
    }
}
