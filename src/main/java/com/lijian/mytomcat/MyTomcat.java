package com.lijian.mytomcat;

import javax.xml.ws.handler.Handler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MyTomcat {

    private int port = 8080;
    private Map<String,String> urlServletMap =  new HashMap<>();

    public MyTomcat(int port) {
        this.port = port;
    }
    public void start(){
        initServletMapping();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("myTomcat is start...");
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();



//                System.out.println(  inputStream.read());
//                int length = inputStream.read();
//                byte [] content = new byte[length];
//                inputStream.read(content);
//                System.out.println(new String(content));

                    OutputStream outputStream = socket.getOutputStream();

                    MyRequest myRequest = new MyRequest(inputStream);
                    MyResponse myResponse = new MyResponse(outputStream);


                    dispatch(myRequest, myResponse);

                    socket.close();



            }
        } catch (IOException e){
                e.printStackTrace(); }
          catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace(); }

    }


    private void initServletMapping(){
        for (ServletMapping servletMapping : ServletMappingConfig.servletMappingList) {
            urlServletMap.put(servletMapping.getUrl(), servletMapping.getClazz());

        }
    }

    private void dispatch(MyRequest myRequest, MyResponse myResponse) throws IllegalAccessException, InstantiationException {

        String clazz = urlServletMap.get(myRequest.getUrl());

        Class<MyServlet> myServletClass = null;
        try {
            myServletClass = (Class<MyServlet>) Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        MyServlet myServlet = myServletClass.newInstance();
        myServlet.service(myRequest, myResponse);

        }

    public static void main(String[] args) {
        new MyTomcat(8080).start();
    }

}
