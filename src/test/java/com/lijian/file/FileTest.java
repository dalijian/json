package com.lijian.file;

import org.junit.Test;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileTest {

    @Test
    public void changeDirectory() {

        File file = new File("C:\\Users\\lijian\\Workspaces\\his_2021_11_08_slf4j\\his\\src");

        List<File> fileList = Stream.of(file.listFiles()).collect(Collectors.toList());
        for (File file1 : fileList) {
            if (file1.isDirectory()) {

            }
        }

    }


    /**
     * 修改文件中的某一部分的数据测试:将字定位置的字母改为大写
     *
     * @paramfName:要修改的文件名字
     * @paramstart:起始字节
     * @paramlen:要修改多少个字节
     * @return:是否修改成功
     * @throwsException:文件读写中可能出的错
     * @authorjavaFound
     */

    public static boolean changeFile(String fName, int start, int len) throws Exception {
//创建一个随机读写文件对象 
        java.io.RandomAccessFile raf = new java.io.RandomAccessFile(fName, "rw");
//        System.out.println(raf.readUTF());
        long totalLen = raf.length();
        System.out.println("文件总长字节是:" + totalLen);
//打开一个文件通道 
        java.nio.channels.FileChannel channel = raf.getChannel();
//映射文件中的某一部分数据以读写模式到内存中 
        java.nio.MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, start, len);
//示例修改字节 
        for (int i = 0; i < len; i++) {
            byte src = buffer.get(i);
            buffer.put(i, (byte) (src - 31));//修改Buffer中映射的字节的值
            System.out.println("被改为大写的原始字节是:" + src);
        }
        buffer.force();//强制输出,在buffer中的改动生效到文件
        buffer.clear();
        channel.close();
        raf.close();
        return true;
    }


    //测试主方法
    @Test
    public void changeFileTest() throws Exception {
        changeFile("BigFileRW.txt", 3, 1);
        System.out.println("changeOK...");
    }



    @Test
    public void testPath(){

        String linuxPath = "adadfhaldfh   afhas" +
                "" +
                "" +
                "" +
                "" +
                "aldshfa ,.as/f1e3o47y210473'';;oka\\p]akmmmmmmm/128037s";

        List<String> invalidCharacter = Stream.of(",", ".", "/", "\\", "·", "`", "!", "@", "#", "$", "%", "^", "&", "*", "[", "]", "{", "}", "|", "?",
                "，", "。", "？", "；", "：", "‘", "”", "‘", "“", "【", "】", "{", "}", "、", "|",
                "~", "！", "￥", "……", "&", "（", "）", "——", " ","'",";").collect(Collectors.toList());
        for (String str : invalidCharacter) {
            if (linuxPath.contains(str)) {
            linuxPath =    linuxPath.replace(str, "_");
            }
        }
        System.out.println(linuxPath);
    }

    @Test
    public void fileFormatTest(){
        fileFormat();
    }
    /**
     * 格式 化 文件 根据 ; 输出
     */
    public void fileFormat(){

        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\lijian\\Desktop\\java_class_path.txt");
            String  fileStr = StreamUtils.copyToString(fileInputStream, Charset.forName("utf-8"));
            String formatStr = Stream.of(fileStr.split(";")).collect(Collectors.joining("\r\n"));
            StreamUtils.copy(formatStr,Charset.forName("utf-8"),new FileOutputStream("C:\\Users\\lijian\\Desktop\\java_class_path_format.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFileLastModify(){
//        修改 子文件  父文件 的 lastModify(), 也会 发生 改变
        File file = new File("C:\\Users\\lijian\\Workspaces\\his_2021_11_08_slf4j\\his\\src");
        System.out.println(file.lastModified());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss:SSS").format(new Date(file.lastModified())));

    }
//1573038615765
//    1609729039105
//    1610617710976

    @Test
    public void testDate(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss:SSS").format(new Date(-28800000)));
    }

    public static void main(String[] args)  {
        for (int i = 0; i < 200; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                save2File(Thread.currentThread().getName(), "abc", "test_thread_name_with_synchronized");

            });
            thread.start();
        }
    }

    public static synchronized void save2File(String message,String fixmedins_code,String file_name) {
        try {
            File file = new File(  new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + System.getProperty("file.separator") +fixmedins_code+ System.getProperty("file.separator") + file_name + ".txt");
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("\r\n");
            fileWriter.write(message);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
