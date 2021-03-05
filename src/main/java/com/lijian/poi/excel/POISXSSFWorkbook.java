package com.lijian.poi.excel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lijian.json.jackon.Friend;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.easymock.EasyMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class POISXSSFWorkbook {

   public static  Logger logger = LoggerFactory.getLogger(POISXSSFWorkbook.class);

    public static void main(String[] args) {
        try {
            List<Map<String,Object>>  result = new ObjectMapper().readValue(new File("hjkbi_leave.json"), List.class);
            List<String> excelHeader = Stream.of("住院机构", "姓名", "性别", "生日", "身份证号", "住院号", "入院科室", "入院时间", "出院科室", "出院时间", "农合号", "诊断疾病名称").collect(Collectors.toList());
            List<String> columnList = Stream.of("jgmc","fullname","gender","birthday","sfz","visit_id","ruks","admdate","dpmname","cyrqsj","insuranceno","ryzdmc").collect(Collectors.toList());


        HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
            downloadExcel(response, result, "出院记录", excelHeader,"出院记录", columnList);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void downloadExcel(HttpServletResponse response, List<Map<String, Object>> result, String fileName, List<String> excelHeader, String sheetName, List<String> columnList) {

        try {
            new ObjectMapper().writeValue(new File("hjkbi_leave.json"), result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");//指定Content-Disposition可以让前端获取
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

//        Workbook wb = new SXSSFWorkbook(new XSSFWorkbook(), 100);
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet(sheetName);

        setColumnWidth(wb, sheet, result);
        try {
            // 设置 表头
            if (excelHeader != null && excelHeader.size() != 0) {
                if (StringUtils.isNotBlank(sheetName)) {
                    Row row = sheet.createRow(0);
                    Cell cell = row.createCell(0, CellType.STRING);
                    cell.setCellValue(sheetName);


                    Font font = wb.createFont();
                    font.setBold(true);
                    font.setFontName("宋体");
                    font.setFontHeightInPoints(Short.valueOf("12"));
//                    cellStyle(wb, sheet, cell, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, font);
                    margeCell(sheet, 0, 0, 0, result.get(0).size());
                }
                Row row = sheet.createRow(1);
                for (int i = 0; i < excelHeader.size(); i++) {
                    Cell cell = row.createCell(i, CellType.STRING);
                    cell.setCellValue(excelHeader.get(i));
                    Font font = wb.createFont();
                    font.setBold(false);
                    font.setFontName("宋体");
                    font.setFontHeightInPoints(Short.valueOf("11"));
//                    cellStyle(wb,sheet,cell, XSSFCellStyle.ALIGN_CENTER, XSSFCellStyle.VERTICAL_CENTER,font);
                }
            }

            // 设置 表体
            if (result != null && result.size() != 0) {

                for (int i = 0; i < result.size(); i++) {
                    Row tempRow = sheet.createRow(i + 2);
                    int j = 0;
                    for (String columnName : columnList) {
                        Object value = result.get(i).getOrDefault(columnName, "--");

                        Font font = wb.createFont();
                        font.setBold(false);
                        font.setFontName("宋体");
                        font.setFontHeightInPoints(Short.valueOf("11"));
                        Cell cell = null;
                        if (value == null) {
                            cell = tempRow.createCell(j, CellType.STRING);
                            cell.setCellValue("");
                        } else {
                            cell = tempRow.createCell(j, CellType.STRING);
                            cell.setCellValue(String.valueOf(value));
                        }
//                        cellStyle(wb,sheet,cell, XSSFCellStyle.ALIGN_CENTER, XSSFCellStyle.VERTICAL_CENTER,font);
                        j++;
                    }
                }
            }


            // 合并 首行
//            System.out.println("row'num:"+sheet.getPhysicalNumberOfRows());
//           int lastCol= sheet.getRow(1).getLastCellNum();
//            margeCell(sheet, 0, 0, 0, result.get(0).size());


            wb.write(new FileOutputStream("hjkbi_leave.xlsx"));
//            wb.write(response.getOutputStream());
//
//            response.getOutputStream().flush();
//            response.getOutputStream().close();
//            wb.dispose();
        } catch (
                IOException e)

        {
            e.printStackTrace();
            logger.error("生成excel失败,exception:{}", e.getMessage());
        }

    }
    /**
     * 设置 单元 格 格式
     *
     * @param workbook
     * @param sheet
     * @param cell
     * @param xssfCellStyleAlignment 水平 对齐 方式 使用 CellStyle 常量
     * @param verticalAlignment      垂直 对齐 方式 使用 CellStyle 常量
     */
    public static void cellStyle(Workbook workbook, Sheet sheet, Cell cell, HorizontalAlignment xssfCellStyleAlignment, VerticalAlignment verticalAlignment, Font font) {
        CellStyle style1 = workbook.createCellStyle();
        style1.setAlignment(xssfCellStyleAlignment);
        style1.setVerticalAlignment(verticalAlignment);
        style1.setFont(font);
        cell.setCellStyle(style1);
    }
    private static void setColumnWidth(Workbook wb, Sheet sheet, List<Map<String, Object>> content) {

//        sheet =   wb.getXSSFWorkbook().getSheetAt(0);
        for (int i = 0; i < content.size(); i++) {
            int maxLength = 8;
            for (Map.Entry<String, Object> entry : content.get(i).entrySet()) {
                if (String.valueOf(entry.getValue()).toString().length() > maxLength) {
                    maxLength = String.valueOf(entry.getValue()).toString().length();
                }
            }
            sheet.setColumnWidth(i, Double.valueOf(maxLength * 256 * 1.5).intValue());
        }

    }
    /**
     * 合并 单元格
     *
     * @param sheet
     * @param firstRow 起始 行
     * @param lastRow  终止 行
     * @param firstCol 起始 列
     * @param lastCol  终止  列
     */
    public static void margeCell(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        sheet.addMergedRegion(new CellRangeAddress(
                firstRow, //first row (0-based)
                lastRow, //last row (0-based)
                firstCol, //first column (0-based)
                lastCol //last column (0-based)
        ));
    }
}
