package com.lijian.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReadFileTest {

    @Test
    public void test(){
        try {
            List<Map<String,Object>>result =   new ObjectMapper().readValue(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\src\\test\\resources\\test_data_list.json"),List.class);
            List<Map<String, Object>> filterList = result.stream().filter(x -> x.get("icpcode").toString().startsWith("361029104")).collect(Collectors.toList());
            System.out.println(filterList);
            new ObjectMapper().writeValue(new File("icpcode_361029104.json"), filterList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
