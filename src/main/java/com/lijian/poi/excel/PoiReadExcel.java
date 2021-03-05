package com.lijian.poi.excel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class PoiReadExcel {
	private Logger logger = LoggerFactory.getLogger(PoiReadExcel.class);
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * POI解析Excel文件内容
	 * @author David
	 * @param args
	 */
	public static void main(String[] args) {
		PoiReadExcel poiReadExcel = new PoiReadExcel();
		try {
			poiReadExcel.setInputStream("C:\\Users\\lijian\\Desktop\\BI\\bi查询.xlsx");
			List<LinkedHashMap<String, Object>> result = poiReadExcel.readExcelContent("Sheet5");
			System.out.println(result);

			mapper.writeValue(new File("C:/users/lijian/desktop/药剂名称.json"),result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Workbook wb;
	private Sheet sheet;
	private Row row;
	private InputStream inputStream;
	/**
	 * 读取Excel数据内容
	 * @return Map 包含单元格数据内容的Map对象
	 * map 格式 <行号，<列号，value>>
	 */
	public List<LinkedHashMap<String,Object>> readExcelContent(String  k) throws Exception{

		if(wb==null){
			throw new Exception("Workbook对象为空!");
		}

//LinkedHashMap<Integer,LinkedHashMap<Integer,Object>> content = new LinkedHashMap<>();
//        LinkedHashMap<Integer,LinkedHashMap<String,Object>> content = new LinkedHashMap<>();
		List<LinkedHashMap<String,Object>> content = new ArrayList<>();

//        sheet = wb.getSheetAt(k);
		sheet =wb.getSheet(k);
//        if (sheet == null) {
//            throw new RuntimeException("sheetName,必须 在 [" + modelNameMap.keySet().stream().collect(Collectors.joining(","))+"] 之中 ");
//        }
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		Row headrow = sheet.getRow(0);

//        int colNum = row.getPhysicalNumberOfCells();
		int colNum = row.getLastCellNum();
		// 拿到 全部 数据 在 根据 xml 模板 匹配
		for (int i = 0; i <= rowNum; i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			int j = 0;

			LinkedHashMap<String,Object> cellValue = new LinkedHashMap<>();

			while (j < colNum) {
				Object obj = getCellFormatValue(row.getCell(j));
				//设置 map key 为 String 表头
				cellValue.put(headrow.getCell(j).getStringCellValue(), obj);
				j++;
			}
			content.add(cellValue);
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
//                    cellvalue=cell.getNumericCellValue();
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
						//处理科学计数法
						if (((String)cellvalue).contains("E")) {
							System.out.println("cellValue->"+cellvalue);
							DecimalFormat df = new DecimalFormat("0");
							cellvalue = df.format(cell.getNumericCellValue());
						}
					}
					break;
//                case Cell.CELL_TYPE_FORMULA: {
				case FORMULA:{  // 公式  Formula cell type 处理公式值
					FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
					cellvalue= evaluator.evaluate(cell).getStringValue();
					break;
				}
//                case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
				case STRING:
					// 取得当前的Cell字符串
					cellvalue = cell.getRichStringCellValue().getString();
					break;
				case BLANK:
					cellvalue=null;
					break;
				default:// 默认的Cell值
					cellvalue = "";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}
	public void  setInputStream(String filepath) {
		if(filepath==null){
			return;
		}
		String ext = filepath.substring(filepath.lastIndexOf("."));
		try {
			File file = new File(filepath);
			InputStream is =null;
			if (!file.exists()) {
				is=getInputStream();
			}else{
				is = new FileInputStream(filepath);
			}
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
	public InputStream getInputStream() {
		return inputStream;
	}
}
