package com.lijian.webservice_demo;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;

public class CxfClient {
    public static void main(String[] args) throws Exception {
        //采用动态工厂方式 不需要指定服务接口
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf
                .createClient("http://10.0.0.103:12000/ws/user?wsdl");
        QName qName = new QName("http://10.0.0.103:12001/webservice", "getUsers");
//        Object[] result = client.invoke(qName);
        Object[] result =   client.invoke("getUsers");
        System.out.println(result[0]);
    }

}
