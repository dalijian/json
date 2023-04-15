package com.lijian.webservice_demo;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class WebService {

    public static void main(String[] args) {


        JaxWsDynamicClientFactory dcflient=JaxWsDynamicClientFactory.newInstance();

        Client client=dcflient.createClient("http://10.0.0.103:12000/ws/user?wsdl");
        try{
            Object[] objects=client.invoke("getUserById","1");
            System.out.println("getUserById 调用结果："+objects[0].toString());

            Object[] objectall=client.invoke("getUsers");
            System.out.println("getUsers调用部分结果："+objectall[0].toString());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

