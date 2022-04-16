package com.lijian.nio;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PathDemo {

    @Test
    public void test() throws IOException {
        Path file = Paths.get("/alice.txt");
        System.out.println(file.toAbsolutePath());

        Path path = FileSystems.getDefault().getPath("C:/users/lijian/desktop", "appTool-2019-06-26.log");
        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
    }

    @Test
    public void divide() {
        System.out.println(90 / 100);
    }


    @Test
    public void loopMap() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 10000000; i++) {

            map.put(String.valueOf(i), String.valueOf(i));
        }

        System.out.println(map.size());
    }
}
