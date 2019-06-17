package com.lijian.howTomcatWork.chapter2;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ServletProcessor1 {
    public void process(Request request, Response response) {
        String uri = request.getUri();  //   uri 实例    /servlet/servletName
        String servletName = uri.substring(uri.lastIndexOf("/") + 1); //拿到 servletName
        URLClassLoader loader = null;
        URL[] urls = new URL[1];
        URLStreamHandler streamHandler =null;
        File classPath = new File(Constants.WEB_ROOT);

        /*接下去， process 方法加载 servlet。要完成这个，你需要创建一个类加载器并告诉这个类
        加载器要加载的类的位置。对于这个 servlet 容器，类加载器直接在 Constants 指向的目录里边
        查找。 WEB_ROOT 就是指向工作目录下面的 webroot 目录*/
        try {
            String repository = (new URL("file",null,classPath.getCanonicalPath()+File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Class myClass  =null;
        try {
//            myClass = loader.loadClass(servletName);
          myClass=  this.getClass().getClassLoader().loadClass("com.lijian.howTomcatWork.chapter2."+servletName);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Servlet servlet  = null;
        try {
            servlet = (Servlet)myClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            servlet.service((ServletRequest)request,(ServletResponse) response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
