package com.lijian.zip;


import java.io.*;
import java.util.zip.*;

public class Zip {

//    压缩的checkout_num 与 解压缩的 checkout_num 应该相同
    static final int BUFFER = 2048;

    public static void main(String argv[]) {
        try {
            BufferedInputStream origin = null;
            FileOutputStream dest = new
                    FileOutputStream("C:\\Users\\lijian\\Desktop\\checkout_number.zip");

            CheckedOutputStream checksum = new
                    CheckedOutputStream(dest, new Adler32());
            ZipOutputStream out = new
                    ZipOutputStream(new
                    BufferedOutputStream(checksum));
//out.setMethod(ZipOutputStream.DEFLATED);
            byte data[] = new byte[BUFFER];
// get a list of files from current directory
            File f = new File("C:\\Users\\lijian\\Desktop\\netty");

            File[] files = f.listFiles();
            for (int i = 0; i < files.length; i++) {
                System.out.println("Adding: " + files[i]);
                FileInputStream fi = new
                        FileInputStream(files[i]);
                origin = new
                        BufferedInputStream(fi, BUFFER);
                ZipEntry entry = new ZipEntry(files[i].getName());
                out.putNextEntry(entry);
                int count;
                while ((count = origin.read(data, 0,
                        BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
                origin.close();
            }
            out.close();
            System.out.println("checksum: "+checksum.getChecksum().getValue());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}