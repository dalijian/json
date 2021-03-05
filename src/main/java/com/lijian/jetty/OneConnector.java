package com.lijian.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

/**
 * 只有一个 Connector 的 Jetty server
 */
public class OneConnector
{
    public static void main( String[] args ) throws Exception
    {
        // Server
        Server server = new Server();

        // HTTP connector
        ServerConnector http = new ServerConnector(server);
        http.setHost("localhost");
        http.setPort(8080);
        http.setIdleTimeout(30000);

        // 设置 connector
        server.addConnector(http);

        // 设置 handler
        server.setHandler(new HelloHandler());

        // 启动 Server
        server.start();
        server.join();
    }
}