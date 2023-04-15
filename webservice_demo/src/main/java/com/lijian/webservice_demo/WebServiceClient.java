//package com.lijian.webservice_demo;
//
//
//import org.w3c.dom.Document;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.Unmarshaller;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.soap.MessageFactory;
//import javax.xml.soap.SOAPBody;
//import javax.xml.soap.SOAPConnection;
//import javax.xml.soap.SOAPConnectionFactory;
//import javax.xml.soap.SOAPConstants;
//import javax.xml.soap.SOAPEnvelope;
//import javax.xml.soap.SOAPException;
//import javax.xml.soap.SOAPMessage;
//import javax.xml.soap.SOAPPart;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLStreamHandler;
//import java.util.logging.Logger;
//
//public class WebServiceClient {
//
//    static Logger logger = Logger.getLogger("WebServiceClient");
//
//    public void testtt() {
//
//
//        SOAPConnection connection = null;
//
//        try {
//            SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
//            connection = sfc.createConnection();
//            SOAPMessage soapMessage = ObjectToSoapXml(object, nsMethod, nsName);
//
//            URL endpoint = new URL(new URL(url),
//                    "",
//                    new URLStreamHandler() {
//                        @Override
//                        protected URLConnection openConnection(URL url) throws IOException {
//                            URL target = new URL(url.toString());
//                            URLConnection connection = target.openConnection();
//                            // Connection settings
//                            connection.setConnectTimeout(120000); // 2 min
//                            connection.setReadTimeout(60000); // 1 min
//                            return (connection);
//                        }
//                    });
//
//            SOAPMessage response = connection.call(soapMessage, endpoint);
//
//        } catch (Exception e) {
//
//        }
//    }
//    private static<T> SOAPMessage ObjectToSoapXml(T object, String nsMethod, String nsName) {
//        SOAPMessage soapMessage = null;
//
//        try {
//            MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
//            soapMessage = messageFactory.createMessage();
//            SOAPPart soapPart = soapMessage.getSOAPPart();
//            // SOAP Envelope
//            SOAPEnvelope envelope = soapPart.getEnvelope();
//            envelope.setPrefix("SOAP-ENV");
//            envelope.addNamespaceDeclaration("ns1", nsMethod);
//
//            // SOAP Body
//            SOAPBody soapBody = envelope.getBody();
//            soapBody.setPrefix("SOAP-ENV");
//
//            soapBody.addDocument(jaxbObjectToXML(object, nsMethod, nsName));//将body中的类通过document的形式写入
//            soapMessage.saveChanges();
//        } catch (SOAPException e) {
//            e.printStackTrace();
//
//        }
//
//        return soapMessage;
//    }
//    private static<T> Document jaxbObjectToXML(T emp, String nsMethod, String nsName) {
//        try {
//            JAXBContext context = JAXBContext.newInstance(emp.getClass());
//            // Create the Document
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            org.w3c.dom.Document document = db.newDocument();
//
//            // Marshal the Object to a Document
//            Marshaller marshaller = context.createMarshaller();
//            marshaller.marshal(emp, document);
//            if(null != document) {
//                document.renameNode(document.getFirstChild(), nsMethod, nsName);
//            }
//
//            return document;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static<T> T parseSoapMessage(SOAPMessage reqMsg, T object, String name) {
//        try {
//            reqMsg = removeUTFBOM(reqMsg);
//            SOAPBody soapBody = reqMsg.getSOAPBody();
//            Document document = soapBody.extractContentAsDocument();//获取返回信息中的消息体
//            document.renameNode(document.getFirstChild(), null, name);//根节点去掉前缀
//            JAXBContext jc = JAXBContext.newInstance(object.getClass());
//            Unmarshaller unmarshaller = jc.createUnmarshaller();
//            object = (T)unmarshaller.unmarshal(document);
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//
//        return object;
//    }
//    private static SOAPMessage removeUTFBOM(SOAPMessage soapMessage) {
//        ByteArrayOutputStream baos = null;
//        try
//        {
//            baos = new ByteArrayOutputStream();
//            soapMessage.writeTo(baos);
//            String soapString = baos.toString();
//            if (baos.toString().startsWith("\uFEFF")) {
//                soapString = soapString.substring(1);
//                InputStream is = new ByteArrayInputStream(soapString.getBytes());
//                soapMessage = MessageFactory.newInstance().createMessage(null, is);
//            }
//
//        } catch (SOAPException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return soapMessage;
//    }
//}
