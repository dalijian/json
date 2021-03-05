//package com.lijian.jetty;
//
//import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
//import org.eclipse.jetty.server.Connector;
//import org.eclipse.jetty.server.Handler;
//import org.eclipse.jetty.server.NCSARequestLog;
//import org.eclipse.jetty.server.RequestLog;
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.server.ServerConnector;
//import org.eclipse.jetty.server.Slf4jRequestLog;
//import org.eclipse.jetty.server.handler.DefaultHandler;
//import org.eclipse.jetty.server.handler.HandlerCollection;
//import org.eclipse.jetty.server.handler.RequestLogHandler;
//import org.eclipse.jetty.server.handler.StatisticsHandler;
//import org.eclipse.jetty.servlet.ServletContextHandler;
//import org.eclipse.jetty.servlet.ServletHolder;
//import com.sun.jersey.spi.container.servlet.ServletContainer;
//import org.glassfish.jersey.server.ResourceConfig;
//
//public class Main {
//
//
//    public static void main(String[] args) throws Exception {
//        Server server=new Server(8088);
//
//        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
//
//        ResourceConfig resourceConfig = new ResourceConfig();
//        // 加载 指定 配置
//        resourceConfig.register(BookkeepingService.class);
//        ServletContainer servletContainer = new ServletContainer(resourceConfig);
//        handler.addServlet(new ServletHolder(servletContainer), "/*");
//        handler.setContextPath("/");
//        server.setHandler(handler);
//        server.start();
//        System.out.println("start...in 8080");
//
//
////        扫描 路径
//        //        Server server=new Server(8080);
////        ServletHolder servlet = new ServletHolder(ServletContainer.class);
////        servlet.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
////        servlet.setInitParameter("com.sun.jersey.config.property.packages", "com.lijian.jetty");
////        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
////        handler.setContextPath("/");
////        handler.addServlet(servlet, "/*");
////        server.setHandler(handler);
////        server.start();
////        System.out.println("start...in 8080");
//    }
//
//}
