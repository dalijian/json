package com.lijian.poi.src.com.imooc.excel;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class PoiReadExcel {

	/**
	 * POI����Excel�ļ�����
	 * @author David
	 * @param args
	 */
	public static void main(String[] args) {

		//��Ҫ������Excel�ļ�
		File file = new File("e:/poi_test.xls");
		try {
			//����Excel����ȡ�ļ�����
			HSSFWorkbook workbook = 
				new HSSFWorkbook(FileUtils.openInputStream(file));
			//��ȡ��һ��������workbook.getSheet("Sheet0");
//			HSSFSheet sheet = workbook.getSheet("Sheet0");
			//��ȡĬ�ϵ�һ��������sheet
			HSSFSheet sheet = workbook.getSheetAt(0);
			int firstRowNum = 0;
			//��ȡsheet�����һ���к�
			int lastRowNum = sheet.getLastRowNum();
			for (int i = firstRowNum; i <=lastRowNum; i++) {
				HSSFRow row = sheet.getRow(i);
				//��ȡ��ǰ�����Ԫ���к�
				int lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					HSSFCell cell = row.getCell(j);
					String value = cell.getStringCellValue();
					System.out.print(value + "  ");
				}
				System.out.println();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
