package com.lijian.poi.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelTest {
    public static void main(String[] args) throws FileNotFoundException {


        ReadExcelUtils readExcelUtils = new ReadExcelUtils(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\100thousand_xls.xls"),".xls");

        try {
            LinkedHashMap<Integer,LinkedHashMap<String,Object>> resultMap = readExcelUtils.readExcelContent();
            List<LinkedHashMap<String, Object>> resultList = resultMap.values().stream().collect(Collectors.toList());
            System.out.println(resultList);
//            System.out.println(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
