package com.lijian.xml.jaxb;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;


@XmlRootElement(name = "employees")
public class XmlMapping {
    private List<Employee> eList;

    @XmlElement(name = "employee")
    public List<Employee> getList() {
        return eList;
    }

    public void setList(List<Employee> eList) {
        this.eList = eList;
    }
}