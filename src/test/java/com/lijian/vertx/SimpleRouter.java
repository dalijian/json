package com.lijian.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;


public class SimpleRouter extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        // 创建HttpServer
        HttpServer server = vertx.createHttpServer();

        // 创建路由对象 主路由
        Router mainRouter = Router.router(vertx);
        // 二级 路由
        Router restAPI = Router.router(vertx);
        mainRouter.mountSubRouter("/productsAPI", restAPI);

        // 监听/index地址
        restAPI.route("/index").handler(request -> {
            request.response().end("INDEX SUCCESS");
        });

        restAPI.route(HttpMethod.GET, "/method").handler(request -> {
            String param = request.request().getParam("param");
            System.out.println("接收到用户传递的参数为：" + param);
            request.response().end("method");
        });
        // 获取参数
        restAPI.route(HttpMethod.GET, "/method/:user/:pass").handler(request -> {
                    String user = request.request().getParam("user");
                    String pass = request.request().getParam("pass");

                    request.response()
                            .putHeader("Content-type", "text/html;charset=utf-8")
                            .end("接收到的用户名为：" + user + " 接收到的密码为：" + pass);
                });


        restAPI.route().handler(BodyHandler.create()); // 在路由的最前面，指定body的处理器


        restAPI.post("/post").handler(request -> {
            String res = request.getBodyAsString(); // 获取到body体的数据
            System.out.println(res);
            request.response().end("post");
        });


        // 把请求交给路由处理--------------------(1)
        server.requestHandler(mainRouter);
        server.listen(8889);


    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new SimpleRouter());
    }

}
