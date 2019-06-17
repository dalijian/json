package com.lijian.concurrent.fork_join;

import org.apache.poi.ss.formula.functions.T;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApacheLogForkJoin {

    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\lijian\\IdeaProjects\\json\\src\\test\\resources\\city.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> result = reader.lines().collect(Collectors.toList());
        Map<String, Long> map = result.parallelStream().map(x -> x.split("")).flatMap(x -> Stream.of(x)).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        List<Map.Entry<String, Long>> list = new ArrayList<Map.Entry<String,Long>>(map.entrySet());
        Collections.sort(list, (Comparator<Map.Entry<String, Long>>) (o1, o2) -> (int) ( o2.getValue()-o1.getValue()));

        for (Map.Entry<String, Long> entry : list) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
//        System.out.println(map);

    }
}
