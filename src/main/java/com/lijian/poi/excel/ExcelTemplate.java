package com.lijian.poi.excel;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelTemplate {
    private HSSFWorkbook workbook = null;
    private HSSFSheet sheet = null;
    private String tableHeadName;


    List<Map<String, Object>> tableBody;


    public ExcelTemplate(String tableHeadName, List<Map<String, Object>> tableBody) {
        this.tableHeadName = tableHeadName;
        this.tableBody = tableBody;
    }

    public String getTableHeadName() {
        return tableHeadName;
    }

    public void setTableHeadName(String tableHeadName) {
        this.tableHeadName = tableHeadName;
    }

    public List<Map<String, Object>> getTableBody() {
        return tableBody;
    }

    public void setTableBody(List<Map<String, Object>> tableBody) {
        this.tableBody = tableBody;
    }

    public void createExcel() {
//创建Excel工作簿
        workbook = new HSSFWorkbook();
        //创建一个工作表sheet
        sheet = workbook.createSheet();
        createTableHead();

        createTableBodyHead();
        createTableBody();


        //创建一个文件
        File file = new File(System.currentTimeMillis() + ".xls");
        try {


            FileOutputStream stream = FileUtils.openOutputStream(file);
            workbook.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTableBodyHead() {
        //追加数据
        HSSFCellStyle cellStyle = workbook.createCellStyle();

        //水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFRow row = sheet.createRow(1);
        int i = 0;
        for (Map.Entry<String, Object> entry : tableBody.get(0).entrySet()) {
            String columnName = entry.getKey();
            HSSFCell cellTemp = row.createCell(i++);

            cellTemp.setCellValue(columnName);
            cellTemp.setCellStyle(cellStyle);
        }
    }

    public void createTableBody() {
        //追加数据
        for (int i = 0; i < tableBody.size(); i++) {
            HSSFRow nextrow = sheet.createRow(i + 2);
            int j = 0;
            for (Map.Entry<String, Object> entry : tableBody.get(i).entrySet()) {
                String value = entry.getValue().toString();
                HSSFCell cellTemp = nextrow.createCell(j++);
                cellTemp.setCellValue(value);
            }
        }

    }

    private void createTableHead() {
        //创建第一行
        HSSFRow row = sheet.createRow(0);
        // 合并 首行 单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, tableBody.get(0).size() - 1));

        HSSFCell cell = row.createCell(0);


        HSSFCellStyle cellStyle = workbook.createCellStyle();

        //水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);


        cell.setCellValue(tableHeadName);

        cell.setCellStyle(cellStyle);


        // 在workbook中创建一个字体
        Font font1 = workbook.createFont();
        // 设置字体为粗体
        font1.setBold(true);
        // 设置字体的字符集 - 默认字体集
        font1.setCharSet(Font.DEFAULT_CHARSET);
        // 设置字体的高度 - 以1pt的1/20位单位
        font1.setFontHeightInPoints((short) 15);
        // 设置字体的名字
        font1.setFontName("宋体");
        // 设置文字为斜体
        font1.setItalic(false);
        // 使用水平删除线
        font1.setStrikeout(true);
        // 设置字体颜色为默认黑色
        font1.setColor(Font.COLOR_NORMAL);

        cellStyle.setFont(font1);

    }

    public static void main(String[] args) {

        List list = new ArrayList();

        int i = 0;
        while (i < 1000) {
            list.add(new ImmutableMap.Builder<String, Object>().put("name", "tt").put("address", "kk").put("qq", 123456789).build());
            i++;
        }

        ExcelTemplate excelTemplate = new ExcelTemplate("test___!!!!!!!!!!", list);


        excelTemplate.createExcel();
    }

}
