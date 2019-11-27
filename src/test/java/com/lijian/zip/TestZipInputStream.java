package com.lijian.zip;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class TestZipInputStream
{
    public static void main ( String [ ] args ) throws ZipException,
            IOException
    {
        // get a zip file instance
        File file = new File ( "C:/users/lijian/desktop/NMGHHHT_BGHDJD_20180705.zip" ) ;
        System.out.println(System.getProperty("user.dir"));
        if (file.exists()) {
            System.out.println("exists");

        }
        // get a ZipFile instance
//        ZipFile zipFile = new ZipFile ( file ) ;

        // create a ZipInputStream instance
        ZipInputStream zis = new ZipInputStream ( new FileInputStream(file) ) ;

        int size =zis.available();
        System.out.println(size);
        // create a ZipEntry instance , lay the every file from
        // decompress file temporarily
        ZipEntry entry = null ;
        ZipEntry result = zis.getNextEntry();
        // a circle to get every file
        while ( ( entry = zis.getNextEntry ( ) ) != null )
        {
            System.out.println ( "decompress file :"
                    + entry.getName ( ) ) ;

            // define the path to set the file
            File outFile = new File ( "C:\\Users\\lijian\\Desktop\\zip\\"
                    + entry.getName ( ) ) ;


            System.out.println(outFile.getAbsolutePath());
//            FileUtils.openOutputStream(outFile);
            if (!outFile.exists()) {
                outFile.getParentFile().mkdirs();
            }
            if (outFile.isDirectory()) {
                continue;
            }
            if (entry.isDirectory()) {
                continue;
            }

            String pathName = entry.getName().substring(0,entry.getName().lastIndexOf("/"));
            String pictureName = entry.getName().substring(entry.getName().lastIndexOf("/") + 1);
            String path = ("picture" + File.separator+pathName);

            System.out.println("entry'name->" + entry.getName());
            System.out.println("outFile'name->" + outFile.getName());
            upLoadFileFolder("172.19.12.221",21,"ftptest","Test123!@#",zis,path,pictureName);

//            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
//            int n;
//            byte[] bytes = new byte[1024];
//            while ((n = zis.read(bytes)) != -1) {
//                fileOutputStream.write(bytes, 0, n);
//            }
//            //关闭流
//            fileOutputStream.close();
            zis.closeEntry();





            // if the file's parent directory wasn't exits ,than
            // create the directory

//            if ( ! outFile.getParentFile ( ).exists ( ) )
//            {
//                outFile.getParentFile ( ).mkdir ( ) ;
//            }

            // if the file not exits ,than create the file


//            if ( ! outFile.exists ( ) )
//            {
//                outFile.createNewFile ( ) ;
//            }

            // create an input stream




//            BufferedInputStream bis = new BufferedInputStream (
//                    zipFile.getInputStream ( entry ) ) ;




            // create an output stream





//            BufferedOutputStream bos = new BufferedOutputStream (
//                    new FileOutputStream( outFile ) ) ;
//            byte [ ] b = new byte [ 1000 ] ;
//            while ( true )
//            {
//                int len = bis.read ( b ) ;
//                if ( len == - 1 )
//                    break ;
//                bos.write ( b , 0 , len ) ;
//            }
//            // close stream
//            bis.close ( ) ;
//            bos.close ( ) ;

    }

    }
    public static String changePathDivider(String path) {

        String filePath =null;

        String os = System.getProperty("os.name");
        String fileSeparator=null;
        if (os.toLowerCase().startsWith("win")) {
            fileSeparator = "\\\\";
        } else {
            fileSeparator = "/";
        }
        if (path.contains("\\")) {
            filePath =(path).replaceAll("\\\\",fileSeparator);
        }else if (filePath.contains("\\\\")) {
            filePath =(path).replaceAll("\\\\\\\\", fileSeparator);
        }
        else if (filePath.contains("/")) {
            filePath =(path).replaceAll("/", fileSeparator);
        }
        return filePath;
    }

    public static boolean upLoadFileFolder(String host, int port, String username, String password, InputStream inputStream, String path, String fileName) throws IOException {
        boolean result = false;
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(host, port);
            ftpClient.login(username, password);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            // make directory
            if (path != null && !"".equals(path.trim())) {
                String[] pathes = path.split("/");
                for (String onepath : pathes) {
                    if (onepath == null || "".equals(onepath.trim())) {
                        continue;
                    }
                    onepath = new String(onepath.getBytes("GBK"), "iso-8859-1");
                    if (!ftpClient.changeWorkingDirectory(onepath)) {
                        ftpClient.makeDirectory(onepath);
                        boolean flag = ftpClient.changeWorkingDirectory(onepath);
                        if (!flag) {
                            throw new RuntimeException("上传失败");
                        }
                    }
                }
            }
            result = ftpClient.storeFile(new String(fileName.getBytes("GBK"), "iso-8859-1"), inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ftpClient.logout();
        }
        return result;
    }
    /**
     * 提供给用户使用的解压工具
     * @param srcPath
     * @param outPath
     * @throws IOException
     */
    public static void decompressionFile(String srcPath, String outPath) throws IOException {
        //简单判断解压路径是否合法
        if (!new File(srcPath).isDirectory()) {
            //判断输出路径是否合法
            if (new File(outPath).isDirectory()) {
                if (!outPath.endsWith(File.separator)) {
                    outPath += File.separator;
                }
                //zip读取压缩文件
                FileInputStream fileInputStream = new FileInputStream(srcPath);
                ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
                //解压文件
                decompressionFile(outPath, zipInputStream);
                //关闭流
                zipInputStream.close();
                fileInputStream.close();
            } else {
                throw new RuntimeException("输出路径不合法!");
            }
        } else {
            throw new RuntimeException("需要解压的文件不合法!");
        }
    }

    /**
     * ZipInputStream是逐个目录进行读取，所以只需要循环
     * @param outPath
     * @param inputStream
     * @throws IOException
     */
    private static void decompressionFile(String outPath, ZipInputStream inputStream) throws IOException {
        //读取一个目录
        ZipEntry nextEntry = inputStream.getNextEntry();
        //不为空进入循环
        while (nextEntry != null) {
            String name = nextEntry.getName();
            File file = new File(outPath+name);
            //如果是目录，创建目录
            if (name.endsWith("/")) {
                file.mkdir();
            } else {
                //文件则写入具体的路径中
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                int n;
                byte[] bytes = new byte[1024];
                while ((n = inputStream.read(bytes)) != -1) {
                    bufferedOutputStream.write(bytes, 0, n);
                }
                //关闭流
                bufferedOutputStream.close();
                fileOutputStream.close();
            }
            //关闭当前布姆
            inputStream.closeEntry();
            //读取下一个目录，作为循环条件
            nextEntry = inputStream.getNextEntry();
        }
    }


    @Test
    public void zip() throws IOException {

            decompressionFile("C:/users/lijian/desktop/NMGHHHT_BGHDJD_20180705.zip", "C:/users/lijian/desktop/lijian/");

    }

}