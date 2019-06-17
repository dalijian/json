package com.lijian.connectionPool;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class ConnectionDriver {
    public static Connection createconnection() {
      Connection connecton = (Connection) Proxy.newProxyInstance(Class.class.getClassLoader(),new Class[] {Connection.class},(Object proxy, Method method, Object[] args)->{
          System.out.println(method.getName());

            return  proxy;

        });
        return connecton;
    }
}
