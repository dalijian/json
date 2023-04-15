package com.lijian.sql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.PreparedStatement;

public class SqlProxy {


    public SqlProxy() {

    }


    public Object createProxy() {

        DynamicProxyHandler handler = new DynamicProxyHandler();
        return Proxy.newProxyInstance(PreparedStatement.class.getClassLoader(), // 传入ClassLoader,// 传入要实现的接口
                new Class[]{PreparedStatement.class}, handler);
    }

    public static class DynamicProxyHandler implements InvocationHandler {


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Exception {

            System.out.println("代理启动");

            // 执行 proxiedObject 对象 method 方法 参数 args
            Object result = method.invoke(proxy, args);


            return result;


        }
    }
}
