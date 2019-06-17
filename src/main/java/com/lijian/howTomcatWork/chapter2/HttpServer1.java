package com.lijian.howTomcatWork.chapter2;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer1 {
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer1 server = new HttpServer1();
        server.await();

    }

    private void await() {
        ServerSocket serverSocket = null;

        int port =8080;

        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);

        }

        while (!shutdown) {

            Socket socket =null;
            InputStream inputStream =null;
            OutputStream outputStream = null;

            try {
                socket =serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Request request = new Request(inputStream);
            request.parse();
            Response response = new Response(outputStream);

            response.setRequest(request);
//            response.sendStaticResource();
            if (request.getUri().startsWith("/servlet/")) {
                ServletProcessor1 processor = new ServletProcessor1();
                processor.process(request, response);
            }
            else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            shutdown = request.getUri().equals(SHUTDOWN_COMMAND);

        }

    }
}
