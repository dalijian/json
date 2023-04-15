package com.lijian.sql;

import org.junit.Test;

import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Stream;

public class SqlTest {


    public static void main(String[] args) {
        new SqlTest().sqlProxy();

    }

    private void sqlProxy() {
        String mysqlUrl = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "645143";
        try {
            Connection connection = DriverManager.getConnection(mysqlUrl, username, password);
            boolean flag = connection.isValid(1);
            System.out.println(flag);
            PreparedStatement ps = connection.prepareStatement("select * from person where name=?");
            ps.setString(1, "lijian");
            ps.executeQuery();
            SqlProxy.DynamicProxyHandler handler = new SqlProxy.DynamicProxyHandler();
            ps = (PreparedStatement) Proxy.newProxyInstance(this.getClass().getClassLoader(), // 传入ClassLoader,// 传入要实现的接口
                    new Class[]{PreparedStatement.class}, handler);
            ResultSet rs = ps.executeQuery();
            List<Map<String, Object>> result = convertList(rs);
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, Object>> convertList(ResultSet rs) throws SQLException {
        List<Map<String, Object>> list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取列的数量
        while (rs.next()) {
            Map<String, Object> rowData = new LinkedHashMap<String, Object>();//声明Map
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i).toLowerCase(), rs.getObject(i));//获取键名及值
            }
            list.add(rowData);
        }
        return list;
    }

    @Test
    public void testByte() {
        byte[] bytes = {39, 108, 105, 106, 105, 97, 110, 39};
        System.out.println(new String(bytes));
    }

    @Test
    public void runnableProxyTest() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(System.getProperty("user.dir"));

            }
        };

        Runnable runnable1 = (Runnable) Proxy.newProxyInstance(runnable.getClass().getClassLoader(), runnable.getClass().getInterfaces(), (proxy, method, args) -> {
//            System.out.println(Thread.currentThread().getName());

            return method.invoke(proxy, args);
        });
        runnable1.run();
    }

    @Test
    public void charSequenceTest(){
        CharSequence charSequence = new StringBuffer("q2e3");
        CharSequence runnable1 = (CharSequence) Proxy.newProxyInstance(charSequence.getClass().getClassLoader(), new Class[]{CharSequence.class}, (proxy, method, args) -> {
            System.out.println(Thread.currentThread().getName());

            return method.invoke(proxy, args);
        });
        runnable1.length();


    }
}
