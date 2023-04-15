//package com.lijian.jackson;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.junit.Test;
//
//import java.awt.print.Book;
//
//public class XmlTest {
//
//    @Test
//    public void testGenXml() {
//        XmlMapper xmlMapper = new XmlMapper();
//
//        Book book = new Book("Think in Java", 100);
//        try {
//            String xmlStr = xmlMapper.writeValueAsString(book);
//            System.out.println(xmlStr);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//}
