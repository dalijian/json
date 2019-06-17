package com.lijian.poi.src.com.imooc.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiExpExcel2 {

	/**
	 * POI����Excel�ļ�
	 * @author David
	 * @param args
	 */
	public static void main(String[] args) {

		String[] title = {"id","name","sex"};
		
		//����Excel������
		XSSFWorkbook workbook = new XSSFWorkbook();
		//����һ��������sheet
		Sheet sheet = workbook.createSheet();
		//������һ��
		Row row = sheet.createRow(0);
		Cell cell = null;
		//�����һ������ id,name,sex
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
		}
		//׷������
		for (int i = 1; i <= 10; i++) {
			Row nextrow = sheet.createRow(i);
			Cell cell2 = nextrow.createCell(0);
			cell2.setCellValue("a" + i);
			cell2 = nextrow.createCell(1);
			cell2.setCellValue("user" + i);
			cell2 = nextrow.createCell(2);
			cell2.setCellValue("��");
		}
		//����һ���ļ�
		File file = new File("e:/poi_test.xlsx");
		try {
			file.createNewFile();
			//��Excel���ݴ���
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
