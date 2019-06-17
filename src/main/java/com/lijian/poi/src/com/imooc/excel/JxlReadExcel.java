package com.lijian.poi.src.com.imooc.excel;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class JxlReadExcel {

	/**
	 * JXL����Excel
	 * @author David
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			//����workbook
			Workbook workbook = Workbook.getWorkbook(new File("e:/jxl_test.xls"));
			//��ȡ��һ��������sheet
			Sheet sheet = workbook.getSheet(0);
			//��ȡ����
			for (int i = 0; i < sheet.getRows(); i++) {
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j,i);
					System.out.print(cell.getContents() + "  ");
				}
				System.out.println();
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
