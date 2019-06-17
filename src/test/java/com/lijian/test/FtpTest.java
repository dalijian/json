package com.lijian.test;

import org.apache.commons.net.ftp.*;
import org.junit.Test;

import java.io.*;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FtpTest {

    @Test
    public void testFtp1() {
        //创建客户端对象
        FTPClient ftp = new FTPClient();
        InputStream local = null;
        try {
            //连接ftp服务器
//            ftp.connect("106.12.219.222", 21);
//            ftp.connect("106.13.145.160", 21);
            ftp.connect("172.19.12.221", 21);
            //登录

//            ftp.login("lijian", "lijian520");  // 匿名 用户 不支持 上传
            ftp.login("ftptest", "Test123!@#");
            //设置上传路径

            String path = "/record/test";
            //检查上传路径是否存在 如果不存在返回false
            boolean flag = ftp.changeWorkingDirectory(path);
            if (!flag) {
                //创建上传的路径  该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
                ftp.makeDirectory(path);
            }
            //指定上传路径
            ftp.changeWorkingDirectory(path);
            //指定上传文件的类型  二进制文件
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //读取本地文件
//            File file = new File("C:\\Users\\lijian\\IdeaProjects\\json\\friend.json");
//            File file = new File("C:\\Users\\lijian\\IdeaProjects\\json\\simpleTable.docx");
            File file = new File("C:\\Users\\lijian\\IdeaProjects\\json\\project.zip");

            local = new FileInputStream(file);
            //第一个参数是文件名
            ftp.storeFile(file.getName(), local);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭文件流
                local.close();
                //退出
                ftp.logout();
                //断开连接
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


