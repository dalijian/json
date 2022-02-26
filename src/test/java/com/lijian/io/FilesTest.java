package com.lijian.io;

import com.lijian.guava.CacheLoaderCreatetor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.UUID;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class FilesTest {

    public static Logger log = LoggerFactory.getLogger(FilesTest.class);

    @Test
    public void test() throws IOException {

        Files.lines(Paths.get("C:/users/lijian/desktop/java.log")).skip(10).limit(10).collect(Collectors.toList());
    }

    @Test
    public void testLines() throws IOException {

        Files.write(Paths.get("demo.txt"),
                IntStream.rangeClosed(1, 10).mapToObj(i -> UUID.randomUUID().toString()).collect(Collectors.toList())
                , UTF_8, CREATE, TRUNCATE_EXISTING);
    }

    @Test
    public void test2() {

        LongAdder longAdder = new LongAdder();
        IntStream.rangeClosed(1, 100000).forEach(i -> {
            try {
                Files.lines(Paths.get("demo.txt")).forEach(line -> longAdder.increment());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        log.info("total : {}", longAdder.longValue());
    }

    /**
     * 将 文件夹 下的文件  按行 写入 到 同一个 文件
     * @throws IOException
     */
    @Test
    public void writeToTxt() throws IOException {

        File folder = new File("C:\\Users\\lijian\\Documents\\postman\\plc-1301-ALL-2021-11-05");
        FileOutputStream fileOutputStream = new FileOutputStream("1301_all.txt");
        String str = null;
        FileWriter fw = null;
        BufferedWriter writer = null;
        fw = new FileWriter(new File("1301_all.txt"));
        writer = new BufferedWriter(fw);
        File[] files = folder.listFiles();
        for (int i = 0; i < files.length; i++) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(files[i])));
            while ((str = bufferedReader.readLine()) != null) {
                writer.write(str);
                writer.newLine();//换行
            }
        }
        writer.flush();
        writer.close();
        fw.close();

    }

}
