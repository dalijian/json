package com.lijian.io;

import com.lijian.guava.CacheLoaderCreatetor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public void test2(){

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
}
