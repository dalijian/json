package com.lijian.zip;

import org.junit.Test;
import org.springframework.util.StreamUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDemo {


    @Test
    public void test(){

        try {
            FileOutputStream dest = new
                    FileOutputStream("C:\\Users\\lijian\\Desktop\\checkout_number.zip");
            CheckedOutputStream checksum = new
                    CheckedOutputStream(dest, new Adler32());
            ZipOutputStream out = new
                    ZipOutputStream(new
                    BufferedOutputStream(checksum));
            File f = new File("C:\\Users\\lijian\\Desktop\\koa-demo-1");
            recursion(f,out);
            out.close();
            System.out.println("checksum: "+checksum.getChecksum().getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //  采用递归 压缩 文件夹
    public void recursion(File file,ZipOutputStream out) throws IOException {
        if (file.isFile()) {
            String fileName = file.getAbsolutePath();
          fileName =fileName.substring(fileName.indexOf("koa-demo-1"));
            ZipEntry entry = new ZipEntry(fileName);
            out.putNextEntry(entry);
            out.write(StreamUtils.copyToByteArray(new FileInputStream(file)));
            return ;
        }
        for (int i = 0; i < file.listFiles().length; i++) {
            recursion(file.listFiles()[i], out);
        }
    }
}
