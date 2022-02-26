package com.lijian.xml.dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.VisitorSupport;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @author lijian
 */
public class XmlUtils {
    /**
     *  不去除 命名 空间, 采用 访问者 模式
     * @param inputStream
     * @return
     * @throws DocumentException
     */

   public static Document getDocument(InputStream inputStream) throws DocumentException {
        SAXReader reader = new SAXReader();
        reader.setEncoding("utf-8");
        Document document = reader.read(inputStream);

        document.accept(new VisitorSupport() {
            @Override
            public void visit(Attribute node) {
                System.out.println(node.getName());
                if (node == null) {
                    return ;
                }
                if (node.toString().contains("xmlns") || node.toString().contains("xsi:")) {
                    node.detach();
                }

            }
        });
        return document;
    }
}
