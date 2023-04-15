//package com.lijian.webservice_demo;
//
//import java.net.URL;
//
//import javax.xml.namespace.QName;
//import javax.xml.ws.Service;
//
//import com.soft.platform.webservice.server.MyService;
//
//public class WsClient {
//
//	public static void main(String[] args) throws Exception {
//		URL url = new URL("http://192.168.0.101:8089/myservice?wsdl");
//		// 指定命名空间和服务名称
//		QName qName = new QName("http://com.soft.ws/my", "MyService");
//		Service service = Service.create(url, qName);
//		// 通过getPort方法返回指定接口
//		MyService myServer = service.getPort(new QName("http://com.soft.ws/my",
//				"LoginPort"), MyService.class);
//		// 调用方法 获取返回值
//		String result = myServer.authorization("admin", "123456");
//		System.out.println(result);
//	}
//
//}