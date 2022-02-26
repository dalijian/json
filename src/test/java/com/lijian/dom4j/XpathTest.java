package com.lijian.dom4j;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XpathTest {
    private static Document doc;
    private static XPath xpath;

    public static void main(String[] args) throws Exception {
        init();
        getRootEle();
        getChildEles();
        getPartEles();
        haveChildsEles();
        getLevelEles();
        getAttrEles();

        //打印根节点下的所有元素节点
        System.out.println(doc.getDocumentElement().getChildNodes().getLength());
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                System.out.print(nodeList.item(i).getNodeName() + " ");
            }
        }
    }

    // 初始化Document、XPath对象
    public static void init() throws Exception {
        // 创建Document对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(new FileInputStream(new File("C:\\Users\\lijian\\IdeaProjects\\json\\src\\test\\resources\\demo.xml")));

        // 创建XPath对象
        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
    }

    // 获取根元素
    // 表达式可以更换为/*,/rss
    public static void getRootEle() throws XPathExpressionException {
        Node node = (Node) xpath.evaluate("/rss", doc, XPathConstants.NODE);
        System.out.println(node.getNodeName() + "--------"
                + node.getNodeValue());
        System.out.println(node.getChildNodes());
    }

    // 获取子元素并打印
    public static void getChildEles() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("/rss/channel/*", doc,
                XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + " " + nodeList.item(i).getNodeValue());
        }
        System.out.println();
    }

    // 获取部分元素
    // 只获取元素名称为title的元素
    public static void getPartEles() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("//*[name() = 'title']",
                doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + "-->"
                    + nodeList.item(i).getTextContent());
        }
        System.out.println();
    }

    // 获取包含子节点的元素
    public static void haveChildsEles() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("//*[*]", doc,
                XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + " ");
        }
        System.out.println();
    }

    // 获取指定层级的元素
    public static void getLevelEles() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("/*/*/*/*", doc,
                XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + "-->"
                    + nodeList.item(i).getTextContent() + " ");
        }
        System.out.println("-----------------------------");
    }

    // 获取指定属性的元素
    // 获取所有大于指定价格的书箱
    public static void getAttrEles() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("//bookstore/book[price>35.00]/title", doc,
                XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + "-->"
                    + nodeList.item(i).getTextContent() + " ");
        }
        System.out.println();
    }


    @Test
    public void personInfoWithDomTest() throws XPathExpressionException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
            doc = db.parse(new FileInputStream(new File("C:\\Users\\lijian\\IdeaProjects\\json\\src\\test\\resources\\person_info.xml")));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        // 创建XPath对象
        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();

//        Node node = (Node) xpath.evaluate("/ClinicalDocument", doc, XPathConstants.NODE);

        NamespaceContext ctx = new NamespaceContext() {
            public String getNamespaceURI(String prefix) {
                return prefix.equals("xl:") ? "xl:" : null;
            }

            public Iterator getPrefixes(String val) {
                return null;
            }

            public String getPrefix(String uri) {
                return null;
            }
        };
        XPath xpath = XPathFactory.newInstance().newXPath();
        xpath.setNamespaceContext(ctx);

        Node node = (Node) xpath.evaluate("/ClinicalDocument/recordTarget/patientRole/id[@root='2.16.156.10011.1.2']", doc, XPathConstants.NODE);

        System.out.println(node);
        System.out.println(node.getAttributes());
        System.out.println(node.getBaseURI());
        System.out.println(node.getChildNodes());
        System.out.println("name space:" + node.getNamespaceURI());

        System.out.println(node.getNodeType());
        System.out.println("********************");
        Node node2 = (Node) xpath.evaluate("//component/structuredBody/component/section/code[@code='30954-2' and @displayName='STUDIES SUMMARY']", doc, XPathConstants.NODE);
        System.out.println(node2.getNodeName());
        System.out.println(node2.getAttributes());
        System.out.println(node2.getLocalName());

        System.out.println("******************");
        Node node3 = (Node) xpath.evaluate("//component/structuredBody", doc, XPathConstants.NODE);

        System.out.println(node3.getNodeName());

    }



}

