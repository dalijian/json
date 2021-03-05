package com.lijian.webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
//JAX-RS 依赖于 MessageBodyReader 和 MessageBodyWriter 的实现来自动完成返回值到响应体的序列化以及请求体到实体参数的反序列化工作，


// 其中，XML 格式的请求／响应数据与 Java 对象的自动绑定依赖于 JAXB 的实现。
@Provider
@Produces("application/json")
@Consumes("application/json")
public class GsonProvider implements MessageBodyWriter<Object>,
        MessageBodyReader<Object> {
 
   private final Gson gson;
 
   public GsonProvider() { 
       gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(
               "yyyy-MM-dd").create(); 
   } 
 
   @Override
   public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations,
                             MediaType mediaType) {
       return true; 
   } 
 
   @Override
   public Object readFrom(Class<Object> type, Type genericType,
                          Annotation[] annotations, MediaType mediaType,
                          MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
           throws IOException, WebApplicationException {
       return gson.fromJson(new InputStreamReader(entityStream, "UTF-8"), type);
   } 
 
   @Override
   public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations,
                              MediaType mediaType) {
       return true; 
   } 
 
   @Override
   public long getSize(Object obj, Class<?> type, Type genericType,
                       Annotation[] annotations, MediaType mediaType) {
       return -1; 
   } 
 
   @Override
   public void writeTo(Object obj, Class<?> type, Type genericType,
                       Annotation[] annotations, MediaType mediaType,
                       MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
           throws IOException, WebApplicationException { 
       entityStream.write(gson.toJson(obj, type).getBytes("UTF-8")); 
   } 
 
}