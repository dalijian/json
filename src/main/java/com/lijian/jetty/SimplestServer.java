package com.lijian.jetty;

import org.eclipse.jetty.server.Server;

/**
 * 最简单的 Jetty 服务器
 */
public class SimplestServer
{
    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8080);
        server.start();
        server.dumpStdErr();
        server.join();
    }
}