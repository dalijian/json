//package com.lijian.webservice;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.xml.bind.JAXBElement;
//
//@Path("/hello") //本类的Result路径为/rest/hello/*（结合web.xml),如果直接在rest下面，可以用@path("/")
//public class MyService0 {
//    private final String ROOT_NODE = "root";
//
//     //-----------例子1：返回text ----------
//    @GET //这是Restful中的GET方法
//    @Path("/text") //路径为/rest/hello/text
//    @Produces(MediaType.TEXT_PLAIN) //response的ContentType为text/plain
//    public String getHelloWorld() { 因为输出是text/plain，所以返回一个String，经过测试，即使有toString()，也不能是其他类型
//        return "Hello, my frist RESTFul Test";
//    }
//
//    //-----------例子2：返回Json，JAXBElement<String>格式 ----------
//    @GET
//    @Path("/json")
//    @Produces(MediaType.APPLICATION_JSON)
//    public JAXBElement<String> getHelloWorldJSON() {
//        JAXBElement<String> result = new JAXBElement<String>(
//                new QName("",ROOT_NODE), String.class, "Hello,JSR!");
//        return result;
//    }
//
//    //-----------例子3：URL带参数，返回Json，直接对象格式 ----------
//    @GET
//    @Path("/json/user/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public User getMe(@PathParam("id") String id) {
//        User user = new User();
//        user.setId(id);
//        user.setName(id + "-Test");
//        user.setEmail(id + "@hello");
//        return user;
//    }
//
//    //-----------例子4：URL带参数，返回XML格式 ----------
//    @GET
//    @Path("/xml/user/{id}")
//    @Produces(MediaType.APPLICATION_XML)
//    public User getUserInXML(@PathParam("id") String id) {
//        User user = new User();
//        user.setId(id);
//        user.setName(id + "-TestXML");
//        user.setEmail(id + "@XML");
//        return toReturn;
//    }
//}
//————————————————
//版权声明：本文为CSDN博主「恺风」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//原文链接：https://blog.csdn.net/flowingflying/article/details/52212389