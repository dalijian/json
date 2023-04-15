package com.lijian.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Demo {
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        Document document = XmlUtils.getDocument(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\src\\test\\resources\\person_info.xml"));
    }
}
