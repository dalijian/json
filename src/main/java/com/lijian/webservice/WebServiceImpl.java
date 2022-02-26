package com.lijian.webservice;

import javax.jws.WebService;
 
@WebService
public class WebServiceImpl implements WebServiceI{
 
    @Override
    public String sayHello(String name) {
        // TODO Auto-generated method stub
        return "sayHellp"+name;
    }
 
    @Override
    public String save(String name, String pwd) {
        // TODO Auto-generated method stub
        return "save Sucess";
    }
     
}