package com.lijian.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class OneServletContext
{
    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setResourceBase(System.getProperty("java.io.tmpdir"));
        server.setHandler(context);

        // 添加 dump servlet
//        context.addServlet(DumpServlet.class, "/dump/*");
        // 添加 default servlet
        context.addServlet(DefaultServlet.class, "/");

        server.start();
        server.join();
    }
}