package com.lijian.algorithms.sort;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.FileSystem;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//读取 四个 log 文件，按照 日志 文件的 时间戳 顺序 写入 sortedLog.log 文件



public class MergeSortInPractise {

    BufferedReader bufferedReaderError;
    BufferedReader bufferedReaderInfo;
    BufferedReader bufferedReaderWarn;
    BufferedReader bufferedReaderDebug;
    BufferedWriter bufferedWriter;

    @Before
    public void test() throws IOException {

        File errorFile = new File("error.log");
        File infoFile = new File("info.log");
        File debugFile = new File("debug.log");
        File warnFile = new File("warn.log");

        Reader errorReader = new FileReader(errorFile);
        bufferedReaderError = new BufferedReader(errorReader);

        Reader infoReader = new FileReader(infoFile);
         bufferedReaderInfo = new BufferedReader(infoReader);


        Reader debugReader = new FileReader(debugFile);
         bufferedReaderDebug = new BufferedReader(debugReader);


        Reader warnReader = new FileReader(warnFile);
         bufferedReaderWarn = new BufferedReader(warnReader);


        File file = new File("sortedLog.log");

        FileWriter fileWriter = new FileWriter(file);
        bufferedWriter = new BufferedWriter(fileWriter);
    }

    @Test
    public void writeLog() throws IOException {

        insertLogCyc(bufferedReaderError.readLine(), bufferedReaderInfo.readLine(), bufferedReaderDebug.readLine(), bufferedReaderWarn.readLine());
    }

//    递归会等导致堆栈溢出，而循环不会
//就像我们缺点一所说的，栈会自动将不再使用 的语句所占用的空间释放，从而保证程序运行的正常，
//        但是在递归中，当前次递归的变量及结果依靠下次的递归返回值，
//        所以当前的 空间 便无法释放，栈内存的空间有限，
//        当栈内存的空间被 填满的时候，就会出现上图所示 程序 崩溃的 情况 。
    public  void insertLogRecursion(String errorLine,String infoLine,String debugLine,String warnLine) throws IOException {
        Log errorLog = new Log(errorLine, "error");
        Log infoLog = new Log(infoLine, "info");
        Log debugLog = new Log(debugLine,"debug");
        Log warnLog = new Log(warnLine, "warn");
        List<Log> list = Stream.of(errorLog, infoLog, debugLog, warnLog).sorted(Comparator.comparing(x -> x.getTimestamp())).collect(Collectors.toList());
        write(list.get(0).getContent());
        String type = list.get(0).getType();
        switch (type) {
            case "error":
            errorLine=    bufferedReaderError.readLine();
                break;
            case "info":
                infoLine=    bufferedReaderInfo.readLine();
                break;
            case "debug":
            debugLine=    bufferedReaderDebug.readLine();
                break;
            case "warn":
            warnLine=    bufferedReaderWarn.readLine();
                break;
        }
        if (errorLine == null || infoLine == null || debugLine == null || warnLine == null) {
            return;
        }
        insertLogRecursion(errorLine, infoLine, debugLine, warnLine);


    }

    public  void insertLogCyc(String errorLine,String infoLine,String debugLine,String warnLine) throws IOException {

        while (errorLine != null && infoLine != null && debugLine != null && warnLine != null) {
            Log errorLog = new Log(errorLine, "error");
            Log infoLog = new Log(infoLine, "info");
            Log debugLog = new Log(debugLine, "debug");
            Log warnLog = new Log(warnLine, "warn");
            List<Log> list = Stream.of(errorLog, infoLog, debugLog, warnLog).sorted(Comparator.comparing(x -> x.getTimestamp())).collect(Collectors.toList());
            write(list.get(0).getContent());
            String type = list.get(0).getType();
            switch (type) {
                case "error":
                    errorLine = bufferedReaderError.readLine();
                    break;
                case "info":
                    infoLine = bufferedReaderInfo.readLine();
                    break;
                case "debug":
                    debugLine = bufferedReaderDebug.readLine();
                    break;
                case "warn":
                    warnLine = bufferedReaderWarn.readLine();
                    break;
            }

        }

    }






    public void write(String str) throws IOException {


        bufferedWriter.write(str);
        bufferedWriter.write("\r\n");
        bufferedWriter.flush();
    }


    @Test
    public void writeTest(){

        try {
            write("lijain");
            write("\r\n");
            write("ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
class Log {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    String content;
    long timestamp;
    String type ;

    public Log(String content, String type) {
        this.content = content;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    Pattern pattern = Pattern.compile("(.*?)\\[");

    public Log(String errorLine) {
        this.content= errorLine;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        Matcher matcher = pattern.matcher(content);
        String groupOne =null;
        while (matcher.find()) {
            String groupStr = matcher.group();
            groupOne = matcher.group(1);

        }

        try {
            Date date =  this.simpleDateFormat.parse(groupOne);
            System.out.println(date);
            System.out.println(date.getTime());
            this.timestamp = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return timestamp == log.timestamp;
    }

    @Override
    public int hashCode() {

        return Objects.hash(timestamp);
    }
}
