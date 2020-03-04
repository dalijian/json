package com.lijian.poi.excel;




import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateBigXlsx {


//    通过设置SXXFWorkbook的构造参数，可以设置每次在内存中保持的行数，当达到这个值的时候，那么会把这些数据flush到磁盘上，这样就不会出现内存不够的情况
    public static void main(String[] args) throws Throwable {
        SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
        Sheet sh = wb.createSheet();
        for(int rownum = 0; rownum < 1000; rownum++){
            Row row = sh.createRow(rownum);
            for(int cellnum = 0; cellnum < 10; cellnum++){
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }

        }

        // Rows with rownum < 900 are flushed and not accessible
        for(int rownum = 0; rownum < 900; rownum++){
            assertNotNull(sh.getRow(rownum));

        }

        // ther last 100 rows are still in memory
        for(int rownum = 900; rownum < 1000; rownum++){
           assertNotNull(sh.getRow(rownum));
        }

        FileOutputStream out = new FileOutputStream("/temp/sxssf.xlsx");
        wb.write(out);
        out.close();

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }


}