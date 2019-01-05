package com.lijian.xml.sal;

import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {


    /** 
     * 开始前缀 URI 名称空间范围映射。 
     * 此事件的信息对于常规的命名空间处理并非必需： 
     * 当 http://xml.org/sax/features/namespaces 功能为 true（默认）时， 
     * SAX XML 读取器将自动替换元素和属性名称的前缀。 
     * 参数意义如下： 
     *    prefix ：前缀 
     *    uri ：命名空间 
     */  
    @Override
    public void startPrefixMapping(String prefix, String uri)
            throws SAXException {
        // TODO Auto-generated method stub
         System.out.println("(startPrefixMapping)start prefix_mapping : xmlns:"+prefix+" = "  
                    +"\""+uri+"\"");  
    }
    
    /** 
     * 结束前缀 URI 范围的映射。 
     * @param prefix  前缀
     */ 
    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        // TODO Auto-generated method stub
        System.out.println("(endPrefixMapping)end prefix_mapping : "+prefix); 
    }
    
    /**
     * 文档结束
     */
    @Override
    public void endDocument() throws SAXException {
        // TODO Auto-generated method stub
        System.out.println("(endDocument)doument is ended"); 
    }
    
    /** 
     * 接收文档的结尾的通知。 
     * 参数意义如下： 
     *    uri ：元素的命名空间 
     *    localName ：元素的本地名称（不带前缀） 
     *    qName ：元素的限定名（带前缀） 
     */ 
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // TODO Auto-generated method stub
        System.out.println("(endElement)end element : "+qName+"("+uri+")");  
    }
    
    /** 
     * 接收元素内容中可忽略的空白的通知。 
     * 参数意义如下： 
     *     ch : 来自 XML 文档的字符 
     *     start : 数组中的开始位置 
     *     length : 从数组中读取的字符的个数 
     */
    @Override
    public void ignorableWhitespace(char[] ch, int start, int length)
            throws SAXException {
        // TODO Auto-generated method stub
        StringBuffer buffer = new StringBuffer();  
        for(int i = start ; i < start+length ; i++){  
            switch(ch[i]){  
                case '\\':buffer.append("\\\\");break;  
                case '\r':buffer.append("\\r");break;  
                case '\n':buffer.append("\\n");break;  
                case '\t':buffer.append("\\t");break;  
                case '\"':buffer.append("\\\"");break;  
                default : buffer.append(ch[i]);   
            }  
        }  
        System.out.println("(ignorableWhitespace)ignorable whitespace("+length+"): "+buffer.toString());  
    }
    
    /** 
     * 接收用来查找 SAX 文档事件起源的对象。 
     * 参数意义如下： 
     *     locator : 可以返回任何 SAX 文档事件位置的对象 
     */  
    @Override
    public void setDocumentLocator(Locator locator) {
        // TODO Auto-generated method stub
        System.out.println("(setDocumentLocator)set document_locator : (lineNumber = "+locator.getLineNumber()  
                +",columnNumber = "+locator.getColumnNumber()  
                +",systemId = "+locator.getSystemId()  
                +",publicId = "+locator.getPublicId()+")");  
    }
    
    /**
     * 接收文档的开始的通知。 
     */ 
    @Override
    public void startDocument() throws SAXException {
        // TODO Auto-generated method stub
        System.out.println("(startDocument)document is startting"); 
    }
    
    /**
     * 接收元素开始的通知。 
     * 参数意义如下： 
     *    uri ：元素的命名空间 
     *    localName ：元素的本地名称（不带前缀） 
     *    qName ：元素的限定名（带前缀） 
     *    atts ：元素的属性集合
     *
     *
     */  
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub
         System.out.println("(startElement)start element : "+qName+"("+uri+")");
        if (qName.equals("user")) {
            for (int i = 0; i < attributes.getLength(); i++) {
                System.out.println(attributes.getQName(i)+"-->"+ attributes.getValue(i));

            }


        }
    }
    
    /** 
     * 接收注释声明事件的通知。 
     * 参数意义如下： 
     *     name - 注释名称。 
     *     publicId - 注释的公共标识符，如果未提供，则为 null。 
     *     systemId - 注释的系统标识符，如果未提供，则为 null。 
     */ 
    @Override
    public void notationDecl(String name, String publicId, String systemId)
            throws SAXException {
        // TODO Auto-generated method stub
        System.out.println("(notationDecl)notation declare : (name = "+name  
                +",systemId = "+publicId  
                +",publicId = "+systemId+")");  
    }
    
    /** 
     * 允许应用程序解析外部实体。 
     * 解析器将在打开任何外部实体（顶级文档实体除外）前调用此方法 
     * 参数意义如下： 
     *     publicId ： 被引用的外部实体的公共标识符，如果未提供，则为 null。 
     *     systemId ： 被引用的外部实体的系统标识符。 
     * 返回： 
     *     一个描述新输入源的 InputSource 对象，或者返回 null， 
     *     以请求解析器打开到系统标识符的常规 URI 连接。 
     */  
    @Override
    public InputSource resolveEntity(String publicId, String systemId)
            throws IOException, SAXException {
        // TODO Auto-generated method stub
        return super.resolveEntity(publicId, systemId);
    }
    
    /** 
     * 接收跳过的实体的通知。 
     * 参数意义如下：  
     * name : 所跳过的实体的名称。如果它是参数实体，则名称将以 '%' 开头， 
     *            如果它是外部 DTD 子集，则将是字符串 "[dtd]" 
     */  
    @Override
    public void skippedEntity(String name) throws SAXException {
        // TODO Auto-generated method stub
        System.out.println("(skippedEntity)the name of the skipped entity : "+name); 
    }
    
    /** 
     * 接收未解析的实体声明事件的通知。 
     * 参数意义如下： 
     *     name - 未解析的实体的名称。 
     *     publicId - 实体的公共标识符，如果未提供，则为 null。 
     *     systemId - 实体的系统标识符。 
     *     notationName - 相关注释的名称。 
     */ 
    @Override
    public void unparsedEntityDecl(String name, String publicId,
            String systemId, String notationName) throws SAXException {
        // TODO Auto-generated method stub
          System.out.println("(unparsedEntityDecl)unparsed entity declare : (name = "+name  
                    +",systemId = "+publicId  
                    +",publicId = "+systemId  
                    +",notationName = "+notationName+")");  
    }
    
    /** 
     * 接收处理指令的通知。 
     * 参数意义如下： 
     *     target : 处理指令目标 
     *     data : 处理指令数据，如果未提供，则为 null。 
     */  
    @Override
    public void processingInstruction(String target, String data)
            throws SAXException {
        // TODO Auto-generated method stub
         System.out.println("(processingInstruction)process instruction : (target = \""  
                    +target+"\",data = \""+data+"\")");
    }
    
    /** 
     * 接收字符数据的通知。    拿到 nodevalue
     * 在DOM中 ch[begin:end] 相当于Text节点的节点值（nodeValue） 
     */
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        // TODO Auto-generated method stub
          StringBuffer buffer = new StringBuffer();  
            for(int i = start ; i < start+length ; i++){  
                switch(ch[i]){  
                    case '\\':buffer.append("\\\\");break;  
                    case '\r':buffer.append("\\r");break;  
                    case '\n':buffer.append("\\n");break;  
                    case '\t':buffer.append("\\t");break;  
                    case '\"':buffer.append("\\\"");break;  
                    default : buffer.append(ch[i]);   
                }  
            }  
            System.out.println("(characters)characters("+length+"): "+buffer.toString()); 
    }
    /**
     * 错误异常处理 可恢复
     */
    @Override
    public void error(SAXParseException e) throws SAXException {
        // TODO Auto-generated method stub
         System.err.println("(error)Error ("+e.getLineNumber()+","  
                    +e.getColumnNumber()+") : "+e.getMessage());
    }
    
    /**
     * 致命性错误处理 不可恢复
     */
    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        // TODO Auto-generated method stub
         System.err.println("(fatalError)FatalError ("+e.getLineNumber()+","  
                    +e.getColumnNumber()+") : "+e.getMessage());
    }
    
    /**
     * 警告处理
     */
    @Override
    public void warning(SAXParseException e) throws SAXException {
        // TODO Auto-generated method stub
         System.err.println("(warning)("+e.getLineNumber()+","  
                    +e.getColumnNumber()+") : "+e.getMessage());
    }
}