package com.lijian.poi.excel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelTest {
    public static void main(String[] args) {
        ReadExcelUtils readExcelUtils = new ReadExcelUtils("C:/users/lijian/desktop/文体馆地址码.xlsx");

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
