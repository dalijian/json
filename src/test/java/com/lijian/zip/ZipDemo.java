package com.lijian.zip;

import org.junit.Test;
import org.springframework.util.StreamUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.lijian.zip.Zip.BUFFER;

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
            File f = new File("C:\\Users\\lijian\\Desktop\\迅雷下载");
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
          fileName =fileName.substring(fileName.indexOf("迅雷下载"));
            ZipEntry entry = new ZipEntry(fileName);
            out.putNextEntry(entry);
//            使用 文件 输出流 FileInputStream  + while  批量 写入 byte[]  可以 不消耗 内存   实现 压缩
            BufferedInputStream origin = new
                    BufferedInputStream(new FileInputStream(file), BUFFER);
            int count;
            byte data[] = new byte[BUFFER];
            while ((count = origin.read(data, 0,
                    BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            origin.close();

//            StreamUtils.copyToByteArray(new FileInputStream(file))   会 把 file  字节 一次 全拿出来 , 而不是 循环写入   大文件 会导致 OOM
//            out.write(StreamUtils.copyToByteArray(new FileInputStream(file)));
            return ;
        }
        for (int i = 0; i < file.listFiles().length; i++) {
            recursion(file.listFiles()[i], out);
        }
    }


    @Test
    public void testArrayEmpty(){

        List<File> list = new ArrayList<>();
        list.add(null);
        list.add(null);
        System.out.println(list.size());

    }



    @Test
    public void stringToLowerCase(){


        System.out.println("李健23LL".toLowerCase());

    }
}
