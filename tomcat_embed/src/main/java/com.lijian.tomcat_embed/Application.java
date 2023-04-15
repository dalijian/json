package com.lijian.tomcat_embed;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.IOException;

public class Application {
    public static void main(String[] args){

        Tomcat tomcat  = new Tomcat();
        HttpServlet servlet = new HttpServlet() {
            @Override
            public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//                super.service(req, res);
                res.getWriter().write("hello world");



            }
        };
        tomcat.setPort(10002);
        tomcat.addWebapp("/sample", "/Deploy/tomcat/webapps/sample");
//        Context context = tomcat.addContext("/sample", null);
//        Tomcat.addServlet(context, "/servlet", servlet);
//        context.addServletMappingDecoded("/servlet", "/servlet");
        try {
            tomcat.init();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
        tomcat.getServer();
    }

    /**
     * 调用文件上传交易
     */
//    @Test
//    public void test2() {
//        File file = new File("testUpload.txt");
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpPost httppost = new HttpPost(URL);
//        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(10000).build();
//        httppost.setConfig(requestConfig);
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.setCharset();
//        builder.addTextBody("jsonStr", upInput);
//        builder.addBinaryBody("file", file, ContentType.DEFAULT_BINARY, "testUpload.txt");
//        HttpEntity entity = builder.build();
//        httppost.setEntity(entity);
//        CloseableHttpResponse response = null;
//        try {
//            response = httpclient.execute(httppost);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode != HttpStatus.SC_OK) {
//                httppost.abort();
//                throw new RuntimeException("HttpClient,error status code :" + statusCode);
//            }
//            HttpEntity responseEntity = response.getEntity();
//            String result;
//            if (responseEntity != null) {
//                //返回字符串
//                result = EntityUtils.toString(responseEntity, "UTF-8");
//                System.out.println(result);
//            }
//            EntityUtils.consume(entity);
//        } catch (ClientProtocolException e) {
//            throw new RuntimeException("提交给服务器的请求，不符合HTTP协议", e);
//        } catch (IOException e) {
//            throw new RuntimeException("向服务器承保接口发起http请求,执行post请求异常", e);
//        } finally {
//            if (response != null) {
//                try {
//                    response.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (httpclient != null) {
//                try {
//                    httpclient.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}