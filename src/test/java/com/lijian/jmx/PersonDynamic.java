package com.lijian.jmx;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.ReflectionException;
import java.util.ArrayList;
import java.util.List;

public class PersonDynamic implements DynamicMBean {

    private Person person;

    // 描述属性信息
    private List<MBeanAttributeInfo> attributeInfos= new ArrayList<>();
   // 描述构造器信息
    private List<MBeanConstructorInfo> constructorInfos = new ArrayList<MBeanConstructorInfo>();
    // 描述方法信息
    private List<MBeanOperationInfo> operationInfos = new ArrayList<>();
    // 描述 通知信息
    private List<MBeanNotificationInfo> notificationInfos = new ArrayList<>();

    // 用于 管理 以上 描述信息
    private MBeanInfo mBeanInfo;

    public PersonDynamic(Person person) {
        this.person =person;
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws Exception {

        constructorInfos.add(new MBeanConstructorInfo("PersonDynamic(String,Integer)" + "构造器", this.person.getClass().getConstructors()[0]));

        attributeInfos.add(new MBeanAttributeInfo("name", "java.lang.String", "姓名", true, false, false));

        attributeInfos.add(new MBeanAttributeInfo("age", "int", "年龄", true, false, false));

        operationInfos.add(new MBeanOperationInfo("sayHello()方法",this.person.getClass().getMethod("sayHello", String.class)));

        // 创建一个 MbeanInfo 对象


        this.mBeanInfo = new MBeanInfo(this.getClass().getName(), "PersonDynamic",
                attributeInfos.toArray(new MBeanAttributeInfo[attributeInfos.size()]),
                constructorInfos.toArray(new MBeanConstructorInfo[constructorInfos.size()]),
                operationInfos.toArray(new MBeanOperationInfo[operationInfos.size()]),
                notificationInfos.toArray(new MBeanNotificationInfo[notificationInfos.size()]));

    }

    @Override
    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {

        if (attribute.equals("name")) {
            return this.person.getName();
        } else if (attribute.equals("age")) {
            return this.person.getAge();
        }
        return null;
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {

    }

    @Override
    public AttributeList getAttributes(String[] attributes) {

        if (attributes == null || attributes.length == 0) {

            return null;
        }
        try{
            AttributeList attributeList  = new AttributeList();
            for (String attrName : attributes) {
                Object object = this.getAttribute(attrName);
                Attribute attribute = new Attribute(attrName, object);

                attributeList.add(attribute);

            }
            return attributeList;
        } catch (AttributeNotFoundException e) {
            e.printStackTrace();
        } catch (ReflectionException e) {
            e.printStackTrace();
        } catch (MBeanException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        return null;
    }

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        if (actionName.equals("sayHello")) {
            return this.person.sayHello(params[0].toString());
        }
        return null;
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        return mBeanInfo;
    }
}
