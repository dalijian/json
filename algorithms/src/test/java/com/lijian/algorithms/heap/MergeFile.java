package com.lijian.algorithms.heap;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.UUID;

public class MergeFile {


    private static char[] chars = new char[6];

    private static int upInt = 100000;
    private static int downInt=upInt/10;
    // 生成 100 个 有序的   文件
    @Test
    public void createFile() throws IOException {
        for (int i = 0; i < 100; i++) {
            List<Integer> list = new ArrayList<>();

            while (list.size() < 50000) {
//                String uuid = UUID.randomUUID().toString();
                int randomInt = new Random().nextInt(upInt);
                if (randomInt < downInt) {
                    randomInt = upInt - randomInt;
                }
                list.add(randomInt);
            }
            String fileName = "litterFile/file_" + i + ".log";
            File file = new File(fileName);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            list.stream().sorted().forEach(x -> {
                try {
                    bufferedOutputStream.write(String.valueOf(x).getBytes());
                    bufferedOutputStream.write(",".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bufferedOutputStream.close();
        }
    }

    //1. 将 100 个 有序文件 按照 顺序  写成 一个 大文件
    @Test
    public void sortLitterFile() throws IOException {

        File mergeFile = new File("mergeFile.log");
        if (!mergeFile.exists()) {
            try {
                mergeFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(mergeFile));
        PriorityQueue<StrToFIle> queue = new PriorityQueue<>();
        List<FileReader> fileReaders = new ArrayList<>();
        // 相当于 初始化 queue
        for (int i = 0; i < 100; i++) {
            String fileName = "litterFile/file_" + i + ".log";
            FileReader fileReader = new FileReader(fileName);

            int result = fileReader.read(chars);
            if (result != -1) {
                StrToFIle strToFIle = new StrToFIle(fileReader, new String(chars));
                queue.offer(strToFIle);
            }
            fileReaders.add(fileReader);
        }
        recursion(fileReaders, bufferedOutputStream, queue);
        bufferedOutputStream.close();
    }

    private void recursion(List<FileReader> fileReaderList, BufferedOutputStream bufferedOutputStream, PriorityQueue<StrToFIle> queue) throws IOException {
        int count = 0;
        int result = 0;
        while (fileReaderList.size() != 0) {
            StrToFIle file = queue.remove();
            bufferedOutputStream.write(file.getValue().getBytes());
            count++;
            FileReader fileReader2 = file.getFileReader();
            //  读完 后 位置 发生 变化 ， 不需要 手动 skip
//            fileReader2.skip(5);
            result = fileReader2.read(chars);
            if (result != -1) {
                StrToFIle strToFIle = new StrToFIle(fileReader2, new String(chars));
                queue.offer(strToFIle);
            }
            if (result == -1) {
                System.out.println("fileReader is read over ,fileReader'name:" + fileReader2.toString());
                fileReaderList.remove(fileReader2);
            }
        }
        System.out.println("count:" + count);
    }

    class StrToFIle implements Comparable<StrToFIle> {
        public StrToFIle(FileReader fileName, String value) {
            this.fileReader = fileName;
            this.value = value;
        }

        FileReader fileReader;
        String value;

        public FileReader getFileReader() {
            return fileReader;
        }

        public void setFileReader(FileReader fileReader) {
            this.fileReader = fileReader;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public int compareTo(StrToFIle o) {
            return - o.getValue().compareTo(this.getValue());
        }
    }

//    @Test
//    public void sortL(){
//        System.out.println("14131a21-e154-4058-b69f-dec03ae94d3c,".length());
//    }


}
