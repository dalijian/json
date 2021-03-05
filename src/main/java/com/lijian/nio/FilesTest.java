package com.lijian.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesTest {

    @Test
    public  void test() throws IOException {
        String folder = "D:\\temp";
        Path path = Paths.get(folder);
        // *.xml的正则表达式 *.[xX][mM][lL]   , 支持 过滤文件
        DirectoryStream<Path> dirStream = Files.newDirectoryStream(path, "*.[xX][mM][lL]");
        for (Path processPath : dirStream) {
            System.out.println(processPath.toFile().getName());
        }
        dirStream.close();
    }
}
