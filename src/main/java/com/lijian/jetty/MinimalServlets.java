package com.lijian.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class MinimalServlets
{
    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8080);

        // ServletHandler 是创建 context handler 的简单方法，
        // 由 Servlet 实力支持。
        // 这个 handler 对象需要被注册给 Server 对象。
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        // 传递的 Servlet 类允许 jetty 实例化并且挂载到给定的上下文路径上。// 重要:
        // 这是一个原始的 Servlet, 不是一个通过 web.xml 或者 @WebServlet注解或者其他类似方式配置的servlet。
        handler.addServletWithMapping(HelloServlet.class, "/*");

        // 启动服务
        server.start();

        // 使用 server.join() 加入当前线程// 在 http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join() 查看更多。
        server.join();
    }

    @SuppressWarnings("serial")
    public static class HelloServlet extends HttpServlet
    {
        @Override
        protected void doGet( HttpServletRequest request,
                              HttpServletResponse response ) throws ServletException,
                                                            IOException
        {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Hello from HelloServlet</h1>");
        }
    }
}