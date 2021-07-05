package com.lijian.test;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrintTest {
    public static void main(String[] args) {
//        for (int i = 0; i < 200; i++) {
//            System.out.println((char) i);
//
//        }

//        System.out.println((byte)'\t');

        String filePath = System.getProperty("user.dir") + File.separator + "readme.md";
        try {
            InputStream inputStream = new FileInputStream(new File(filePath));
            int i;
            while ((i =inputStream.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test(){
        int intValue =Float.valueOf("123.000").intValue();
        float floatValue = Float.valueOf("123.000");
        if (floatValue - intValue == 0) {
            System.out.println("相同");
        }
        System.out.println(Float.valueOf("123.000").intValue());

    }

    @Test
    public void testPP(){
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("lijian", null);
        System.out.println(map.getOrDefault("lijian", ""));
        System.out.println(String.valueOf(map.getOrDefault("lijian","")));
    }


    @Test
    public void testPP2() throws IOException {
        List<Integer> list = Stream.of(1,2,4,5,6).collect(Collectors.toList());
        System.out.println(list.containsAll(Stream.of(3,4,5).collect(Collectors.toList())));
        System.out.println(new File("../../../text.txt").getCanonicalPath());
        System.out.println("title".toUpperCase(Locale.FRENCH));
    }
    @Test
    public void printTest(){
//        String lyrics = "Pull off my blindfold\\nYou just too cold\\nSold me truth\\nAnd baby I was...\\nSo sold\\nYou might as well cut me up\\nPhy-sic-cal-ly\\nYou know\\nYou got me on the edge of extremes\\nAnd I can't believe it\\nAnd don't know what to think\\nSummertime, my rhymes\\nWere so lovesick\\nNow they so sick, cause they ain't about love\\nHere's a pop lick－between us wasn't enough?\\nAnd I know...\\nI felt good times come\\nI thought they' d stay\\nThings undone\\nThey bceome waht may\\nAngels came\\nBut left today\\nAnd I let you slip away\\nListen now\\nBurning empty\\nStill this can't be\\nBelieve it or not－not\\nThat happy\\nHotel, motel\\nIt's hot in hell\\nFree from my cell\\nBut, now left with no home\\nWant u to know\\nI never would have oh\\nFigure out\\nThat\\nWay you play me now\\nIt could have been forever\\nNow it's bringing me down\\nThe high and the low\\nUp and down we go\\nPut myself too close\\nGot burnt like toast\\nFeels like I's m sinking\\nIn the Dead Sea\\nDon't really care\\nThe space inside\\nIs so empty\\nIt's like it's over\\nBefore it's begun\\nThis song is over\\nAnd so is our one.";

        String lyrics="Pull off my blindfold\nYou just too cold\nSold me truth\nAnd baby I was...\nSo sold\nYou might as well cut me up\nPhy-sic-cal-ly\nYou know\nYou got me on the edge of extremes\nAnd I can't believe it\nAnd don't know what to think\nSummertime, my rhymes\nWere so lovesick\nNow they so sick, cause they ain't about love\nHere's a pop lick－between us wasn't enough?\nAnd I know...\nI felt good times come\nI thought they' d stay\nThings undone\nThey bceome waht may\nAngels came\nBut left today\nAnd I let you slip away\nListen now\nBurning empty\nStill this can't be\nBelieve it or not－not\nThat happy\nHotel, motel\nIt's hot in hell\nFree from my cell\nBut, now left with no home\nWant u to know\nI never would have oh\nFigure out\nThat\nWay you play me now\nIt could have been forever\nNow it's bringing me down\nThe high and the low\nUp and down we go\nPut myself too close\nGot burnt like toast\nFeels like I's m sinking\nIn the Dead Sea\nDon't really care\nThe space inside\nIs so empty\nIt's like it's over\nBefore it's begun\nThis song is over\nAnd so is our one.";

        System.out.println(lyrics);
    }
}
