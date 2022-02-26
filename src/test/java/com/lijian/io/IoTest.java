package com.lijian.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IoTest {


    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
//       保存 连接 异常   java.sql.SQLException: The Network Adapter could not establish the connection
        List<String> executeFailStrList = new ArrayList<>();
        //BufferedReader是可以按行读取文件
        FileInputStream inputStream = new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\SJPT_CFLS.sql");
//                FileInputStream inputStream = new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\AI.sql");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String str = null;
        Integer i = 0;

        Connection connection = getConnection();

        while ((str = bufferedReader.readLine()) != null) {
            if (org.junit.platform.commons.util.StringUtils.isBlank(str)) {
                continue;
            }
            if (str.endsWith(";")) {
                str = str.substring(0, str.length() - 1);
            }
            System.out.println(str);
            insert(str, connection, executeFailStrList);
            System.out.print(".");
            i++;
        }
        System.out.println("count:" + i);
        //close
        inputStream.close();
        bufferedReader.close();
        new ObjectMapper().writeValue(new File("executeFailStrList.json"), executeFailStrList);
    }

    static String driverName = "oracle.jdbc.driver.OracleDriver";
    static String url = "jdbc:oracle:thin:@39.98.127.199:1521:helowin";
    static String username = "LIJIAN";
    static String password = "lijian";

    static Connection getConnection() throws SQLException {
        Connection connect = null;

        try {
            // 1、加载驱动
            Class.forName(driverName);

            // 2、获取connection
            connect = DriverManager.getConnection(url, username, password);
            return connect;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }

    static void insert(String sql, Connection connect, List<String> executeFailStrList) throws SQLException {

        PreparedStatement result = null;
        try {
            // 1、加载驱动
            Class.forName(driverName);

            // 2、获取connection
            connect = DriverManager.getConnection(url, username, password);
            result = connect.prepareStatement(sql);
            result.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            executeFailStrList.add(sql);
        }
    }

    @Test
    public void readLine() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("cityCode.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<List<String>> result = bufferedReader.lines().map(x -> (x).trim()).map(x -> x.replaceAll("[\\s]*", "&")).map(x -> Stream.of(x.split("&")).collect(Collectors.toList())).collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    public void regexBlank() {

        String str = "110101 　　东城区";

        List<String> list = Stream.of(str.split("\\x20")).collect(Collectors.toList());

        System.out.println(list);

    }

    @Test
    public void IOTEST_03(){
        String fileName = "D:\\java-EE\\apache-tomcat-6.0.53_2\\logs\\his_emp.log";
        try {
            new FileOutputStream(fileName, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

