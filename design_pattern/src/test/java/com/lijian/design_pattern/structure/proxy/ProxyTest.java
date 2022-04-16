package com.lijian.design_pattern.structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Driver;
import java.sql.SQLException;

public class ProxyTest {
    public static void main(String[] args) {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("morning")) {
                    System.out.println("Good morning, " + args[0]);
                }
                return null;
            }
        };
        Hello hello = (Hello) Proxy.newProxyInstance(
            Hello.class.getClassLoader(), // 传入ClassLoader
            new Class[] { Hello.class }, // 传入要实现的接口
            handler); // 传入处理调用方法的InvocationHandler
        hello.morning("Bob");
    }


//    public static void main(String[] args) {
//        InvocationHandler handler = new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println(method);
////                if (method.getName().equals("morning")) {
////                    System.out.println("Good morning, " + args[0]);
////                }
//                method.invoke(proxy, args);
//                return null;
//            }
//        };
//        Driver driver = (Driver) Proxy.newProxyInstance(
//                Driver.class.getClassLoader(), // 传入ClassLoader
//                new Class[] { Driver.class }, // 传入要实现的接口
//                handler); // 传入处理调用方法的InvocationHandler
//        try {
//            driver.acceptsURL("lijian");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}

