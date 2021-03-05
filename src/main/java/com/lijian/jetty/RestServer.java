/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package com.lijian.jetty;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.lijian.webservice.BookkeepingService;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.Slf4jRequestLog;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.server.handler.StatisticsHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 * Embedded server for the REST API that provides the control plane for Kafka Connect workers.
 */
public class RestServer {
    private static final Logger log = LoggerFactory.getLogger(RestServer.class);

    private static final long GRACEFUL_SHUTDOWN_TIMEOUT_MS = 60 * 1000;

    private static final ObjectMapper JSON_SERDE = new ObjectMapper();

    private Server jettyServer;

   static String hostname ="127.0.0.1";
   static Integer port = 8089;

    public static void main(String[] args) {
        RestServer server = new RestServer();
        server.start();
    }
    /**
     * Create a REST server for this herder using the specified configs.
     */
    public RestServer() {


        // To make the advertised port available immediately, we need to do some configuration here
        // 初始化 jetty server
        jettyServer = new Server();
        // 初始化 serverConnector
        ServerConnector connector = new ServerConnector(jettyServer);
        if (hostname != null && !hostname.isEmpty())
            connector.setHost(hostname);
        connector.setPort(port);
        jettyServer.setConnectors(new Connector[]{connector});
    }

    public void start() {
        log.info("Starting REST server");

        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(new JacksonJsonProvider());
        // 注册 server info restFul
        resourceConfig.register(BookkeepingService.class);
        // 注册 connector info restFul
//        resourceConfig.register(new ConnectorsResource(herder));
//        // 注册 connector plugins restFul
//        resourceConfig.register(new ConnectorPluginsResource(herder));
//        // 注册 异常 转换 接口
//        resourceConfig.register(ConnectExceptionMapper.class);



        // 注册 jersey servlet容器
        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        // 将 servlet 添加 到 servletHolder 中
        ServletHolder servletHolder = new ServletHolder(servletContainer);
        // 注册 servlet 上下文
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(servletHolder, "/*");
        //  设置 跨域 , 设置 跨域 方法

        // 设置 requestLogHandler 为 Slf4jRequestLog
        RequestLogHandler requestLogHandler = new RequestLogHandler();
        Slf4jRequestLog requestLog = new Slf4jRequestLog();
        requestLog.setLoggerName(RestServer.class.getCanonicalName());
        requestLog.setLogLatency(true);
        requestLogHandler.setRequestLog(requestLog);
        // 设置  handler 处理 集合
        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[]{new DefaultHandler(), requestLogHandler});

        /* Needed for graceful shutdown as per `setStopTimeout` documentation */
        StatisticsHandler statsHandler = new StatisticsHandler();
        statsHandler.setHandler(handlers);
        jettyServer.setHandler(statsHandler);
        jettyServer.setStopTimeout(GRACEFUL_SHUTDOWN_TIMEOUT_MS);
        jettyServer.setStopAtShutdown(true);

        try {
            jettyServer.start();
        } catch (Exception e) {
            e.printStackTrace();
//            throw new ConnectException("Unable to start REST server", e);
        }

        log.info("REST server listening at " + jettyServer.getURI());
    }

//    public void stop() {
//        log.info("Stopping REST server");
//
//        try {
//            jettyServer.stop();
//            jettyServer.join();
//        } catch (Exception e) {
//            throw new ConnectException("Unable to stop REST server", e);
//        } finally {
//            jettyServer.destroy();
//        }
//
//        log.info("REST server stopped");
//    }


    /**
     * @param url               HTTP connection will be established with this url.
     * @param method            HTTP method ("GET", "POST", "PUT", etc.)
     * @param requestBodyData   Object to serialize as JSON and send in the request body.
     * @param responseFormat    Expected format of the response to the HTTP request.
     * @param <T>               The type of the deserialized response to the HTTP request.
     * @return The deserialized response to the HTTP request, or null if no data is expected.
     */
    //重定向 处理
//    public static <T> HttpResponse<T> httpRequest(String url, String method, Object requestBodyData,
//                                                  TypeReference<T> responseFormat) {
//        HttpURLConnection connection = null;
//        try {
//            String serializedBody = requestBodyData == null ? null : JSON_SERDE.writeValueAsString(requestBodyData);
//            log.debug("Sending {} with input {} to {}", method, serializedBody, url);
//
//            connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.setRequestMethod(method);
//
//            connection.setRequestProperty("User-Agent", "kafka-connect");
//            connection.setRequestProperty("Accept", "application/json");
//
//            // connection.getResponseCode() implicitly calls getInputStream, so always set to true.
//            // On the other hand, leaving this out breaks nothing.
//            connection.setDoInput(true);
//
//            connection.setUseCaches(false);
//
//            if (requestBodyData != null) {
//                connection.setRequestProperty("Content-Type", "application/json");
//                connection.setDoOutput(true);
//
//                OutputStream os = connection.getOutputStream();
//                os.write(serializedBody.getBytes());
//                os.flush();
//                os.close();
//            }
//
//            int responseCode = connection.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
//                return new HttpResponse<>(responseCode, connection.getHeaderFields(), null);
//            } else if (responseCode >= 400) {
//                InputStream es = connection.getErrorStream();
//                ErrorMessage errorMessage = JSON_SERDE.readValue(es, ErrorMessage.class);
//                es.close();
//                throw new ConnectRestException(responseCode, errorMessage.errorCode(), errorMessage.message());
//                // 响应 成功 处理 响应结果
//            } else if (responseCode >= 200 && responseCode < 300) {
//                InputStream is = connection.getInputStream();
//                T result = JSON_SERDE.readValue(is, responseFormat);
//                is.close();
//                return new HttpResponse<>(responseCode, connection.getHeaderFields(), result);
//            } else {
//                throw new ConnectRestException(Response.Status.INTERNAL_SERVER_ERROR,
//                        Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
//                        "Unexpected status code when handling forwarded request: " + responseCode);
//            }
//        } catch (IOException e) {
//            log.error("IO error forwarding REST request: ", e);
//            throw new ConnectRestException(Response.Status.INTERNAL_SERVER_ERROR, "IO Error trying to forward REST request: " + e.getMessage(), e);
//        } finally {
//            if (connection != null)
//                connection.disconnect();
//        }
//    }

//    public static class HttpResponse<T> {
//        private int status;
//        private Map<String, List<String>> headers;
//        private T body;
//
//        public HttpResponse(int status, Map<String, List<String>> headers, T body) {
//            this.status = status;
//            this.headers = headers;
//            this.body = body;
//        }
//
//        public int status() {
//            return status;
//        }
//
//        public Map<String, List<String>> headers() {
//            return headers;
//        }
//
//        public T body() {
//            return body;
//        }
//    }

//    public static String urlJoin(String base, String path) {
//        if (base.endsWith("/") && path.startsWith("/"))
//            return base + path.substring(1);
//        else
//            return base + path;
//    }

}