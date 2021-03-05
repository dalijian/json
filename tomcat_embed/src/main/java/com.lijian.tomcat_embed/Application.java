package com.lijian.tomcat_embed;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class Application {
    public static void main(String[] args){

        Tomcat tomcat  = new Tomcat();
        HttpServlet servlet = new HttpServlet() {
            @Override
            public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//                super.service(req, res);
                res.getWriter().write("hello world");



            }
        };
        tomcat.setPort(10002);
        tomcat.addWebapp("/sample", "/Deploy/tomcat/webapps/sample");
//        Context context = tomcat.addContext("/sample", null);
//        Tomcat.addServlet(context, "/servlet", servlet);
//        context.addServletMappingDecoded("/servlet", "/servlet");
        try {
            tomcat.init();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
        tomcat.getServer();
    }
}