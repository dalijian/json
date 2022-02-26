package com.lijian.xml.jaxb;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employees")
public class Employees {
    private List<Employee> eList;

    @XmlElement(name = "employee")
    public List<Employee> getList() {
        return eList;
    }

    public void setList(List<Employee> eList) {
        this.eList = eList;
    }
}
