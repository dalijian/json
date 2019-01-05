package com.lijian.xml.sal.test;

import java.io.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.lijian.xml.sal.MyHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * 1.得到SAX解析器的工厂实例
 * 2.从SAX工厂实例中获得SAX解析器
 * 3.把要解析的XML文档转化为输入流，以便DOM解析器解析它
 * 4.解析XML文档
 */
public class SAXParse {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 得到SAX解析工厂  
        SAXParserFactory factory = SAXParserFactory.newInstance();  
        // 创建解析器  
        SAXParser parser =null;
        try {
            parser = factory.newSAXParser();
            XMLReader xmlReader = parser.getXMLReader();

            InputSource input = new InputSource(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\src\\test\\resources\\world.xml"));

            xmlReader.setContentHandler(new MyHandler());
            xmlReader.parse(input);
        } catch (ParserConfigurationException | SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  

    }

}