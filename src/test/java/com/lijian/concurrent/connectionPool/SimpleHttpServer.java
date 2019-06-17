package com.lijian.concurrent.connectionPool;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//使用main线程不断地接受Socket的连接，将连接以及请求提交给线程池处理，这样使得Web服务器能够同时处理多个客户端请求
public class SimpleHttpServer {

    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<HttpRequestHandler>(1);

    static   String basePath ="C:\\Users\\lijian\\IdeaProjects\\json\\src\\test\\java\\com\\lijian\\concurrent\\connectionPool";

    static ServerSocket serverSocket;


    static int port =8080;

    public static void setPort(int port) {
        if (port > 0) {
            SimpleHttpServer.port=port;
        }
    }

    public static void setBasePath(String basePath) {
        if (basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()) {
            SimpleHttpServer.basePath = basePath;

        }
    }
    public static  void start() throws Exception{
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while ((socket = serverSocket.accept()) != null) {
            threadPool.execute(new HttpRequestHandler(socket) );


        }
        serverSocket.close();
    }

    static class HttpRequestHandler implements Runnable {
        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                if (filePath.endsWith("jpg") || filePath.endsWith("ico")) {
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0;
                    byte [] content = new byte[1024];
                    while ((in.read(content)) != -1) {
                        baos.write(content);
                    }
                    byte[] array = baos.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: molly");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length:" + array.length);
                    out.println(array);

//                    socket.getOutputStream().write(array, 0, array.length);

                } else {
//                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
//                    OutputStream outputStream= socket.getOutputStream();
//                    StringBuffer httpResponse = new StringBuffer();
                   out.println("HTTP/1.1 200 OK");
//                   out.println("Server: molly");
                    out.println("Content-Type:text/html");
                    out.println("hello world");

//                    outputStream.write(httpResponse.toString().getBytes());
//                    outputStream.close();
//                    while ((line = br.readLine()) != null) {
//                        out.println(line);
//
//                    }

                }
                out.flush();
            } catch (Exception e) {
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            } finally {
                close(br, in, reader, out, socket);
            }
        }
    }


        private static void close(Closeable ...closeables) {
            if (closeables != null){
                for (Closeable closeable : closeables) {
                    try {
                        closeable.close();
                    } catch (Exception e) {

                    }
                }
            }


    }

}
