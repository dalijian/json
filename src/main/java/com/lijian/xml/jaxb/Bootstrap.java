package com.lijian.xml.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class Bootstrap {
    /**
     * 文件映射路径
     */


    public static void main(String[] args) {


        init();

    }


    public static void init() {
        try {
            InputStream inputStream = Bootstrap.class.getResourceAsStream("empolyee.xml");
            JAXBContext context = JAXBContext.newInstance(XmlMapping.class);
            Unmarshaller createUnmarshaller = context.createUnmarshaller();
            Object unmarshal = createUnmarshaller.unmarshal(inputStream);
            XmlMapping em = (XmlMapping) unmarshal;
            List<Employee> list = em.getList();
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
