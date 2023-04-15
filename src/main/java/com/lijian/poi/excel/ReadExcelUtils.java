package com.lijian.poi.excel;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReadExcelUtils {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Workbook wb;
    private Sheet sheet;
    private Row row;

    public ReadExcelUtils(InputStream inputStream,String ext) {
        if(inputStream==null){
            return;
        }
        try {
            if(".xls".equals(ext)){
                wb = new HSSFWorkbook(inputStream);
            }else if(".xlsx".equals(ext)){
                wb = new XSSFWorkbook(inputStream);
            }else{
                wb=null;
            }
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
    }

    public ReadExcelUtils(String filepath) {
        if(filepath==null){
            return;
        }
        String ext = filepath.substring(filepath.lastIndexOf("."));
        try {
            InputStream is = new FileInputStream(filepath);
            if(".xls".equals(ext)){
                wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(ext)){
                wb = new XSSFWorkbook(is);
            }else{
                wb=null;
            }
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
    }


    /**
     * 读取Excel表格表头的内容
     * @return String 表头内容的数组
     */
    public String[] readExcelTitle() throws Exception{
        if(wb==null){
            throw new Exception("Workbook对象为空！");
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();

        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            // title[i] = getStringCellValue(row.getCell((short) i));
            title[i] = row.getCell(i).getStringCellValue();
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     * @return Map 包含单元格数据内容的Map对象
     * map 格式 <行号，<列号，value>>
     */
    public LinkedHashMap<Integer,LinkedHashMap<String,Object>> readExcelContent() throws Exception{
        if(wb==null){
            throw new Exception("Workbook对象为空！");
        }
//        Map<Integer, Map<Integer,Object>> content = new HashMap<Integer, Map<Integer,Object>>();
//LinkedHashMap<Integer,LinkedHashMap<Integer,Object>> content = new LinkedHashMap<>();
        LinkedHashMap<Integer,LinkedHashMap<String,Object>> content = new LinkedHashMap<>();

        sheet = wb.getSheetAt(4);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(1);
        int colNum = row.getPhysicalNumberOfCells();

        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            int j = 1;
//            Map<Integer,Object> cellValue = new HashMap<Integer, Object>();
//            LinkedHashMap<Integer,Object> cellValue = new LinkedHashMap<>();
            LinkedHashMap<String,Object>cellValue = new LinkedHashMap<>();
            while (j < colNum) {
                Object obj = getCellFormatValue(row.getCell(j));
                cellValue.put(sheet.getRow(1).getCell(j).getStringCellValue(), obj);
                j++;
            }
            content.put(i, cellValue);
        }
        return content;
    }

    /**
     * 根据Cell类型设置数据
     * @param cell
     * @return Object
     */
    private Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {

            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                case NUMERIC:// 如果当前Cell的Type为NUMERIC  Numeric cell type (whole numbers, fractional numbers, dates)
                    cellvalue=cell.getNumericCellValue();
                    break;
//                case Cell.CELL_TYPE_FORMULA: {
                case FORMULA:{  // 公式  Formula cell type
                    // 判断当前的cell是否为Date
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式
                        // data格式是带时分秒的：2013-7-10 0:00:00
                        // cellvalue = cell.getDateCellValue().toLocaleString();


                        // data格式是不带带时分秒的：2013-7-10
                        Date date = cell.getDateCellValue();
                        cellvalue = date;
                    } else {
                        // 如果是纯数字
                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
//                case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
                case STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:// 默认的Cell值
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }




    public static void write(InputStream inputStream) throws IOException, ClassNotFoundException {
        // 初始一个workbook
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        // 创建一个sheet
        XSSFSheet sheet = workbook.getSheetAt(0);

        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);

// 下拉框可选的数据，作为约束
        XSSFDataValidationConstraint dvConstraint =
                (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(new String[]{"a", "b", "c"});
// 设置为下拉框的范围
        CellRangeAddressList addressList =
                new CellRangeAddressList(0, 65536, 3, 3);
// 创建 DataValidation 对象
        XSSFDataValidation validation =
                (XSSFDataValidation)dvHelper.createValidation(dvConstraint, addressList);
        validation.setShowErrorBox(true);

// 作用于指定工作表
        sheet.addValidationData(validation);
        try {
            FileOutputStream outputStream = new FileOutputStream("test_categray.xlsx");

            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }        workbook.close();
    }

}