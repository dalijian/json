package com.lijian.test.poi.world;


import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WordTest {

    //模板文件地址
    private static String inputUrl = "C:\\Users\\lijian\\Desktop\\model.docx";

    @Test
    public void testParagraphs(){


            try {
                //解析docx模板并获取document对象
                XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));
                //获取整个文本对象
                List<XWPFParagraph> allParagraph = document.getParagraphs();

                List<XWPFTable> tables = document.getTables();
                for (XWPFTable table : tables) {
                    List<XWPFTableRow> rows = table.getRows();
                    for (XWPFTableRow row : rows) {
                        List<XWPFTableCell> cells = row.getTableCells();
                        for (XWPFTableCell cell : cells) {
                            System.out.println(cell.getText());
                        }
                    }
                }
//                allParagraph.stream().map(x -> x.getRuns()).forEach(x -> System.out.println(x));
                allParagraph.stream().map(x -> x.getRuns()).map(p -> p.stream().map(x -> x.toString())).forEach(s-> System.out.println(s));
                //获取XWPFRun对象输出整个文本内容
                StringBuffer tempText = new StringBuffer();
                for (XWPFParagraph xwpfParagraph : allParagraph) {
                    List<XWPFRun> runList = xwpfParagraph.getRuns();
                    for (XWPFRun xwpfRun : runList) {
                        tempText.append(xwpfRun.toString());
                    }
                }
                System.out.println(tempText.toString());

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    @Test
    public void testTable(){
        try {

            StringBuffer tableText = new StringBuffer();

            //解析docx模板并获取document对象
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));
            //获取全部表格对象
            List<XWPFTable> allTable = document.getTables();

            for (XWPFTable xwpfTable : allTable) {
                //获取表格行数据
                List<XWPFTableRow> rows = xwpfTable.getRows();
                for (XWPFTableRow xwpfTableRow : rows) {
                    //获取表格单元格数据
                    List<XWPFTableCell> cells = xwpfTableRow.getTableCells();
                    for (XWPFTableCell xwpfTableCell : cells) {
                        List<XWPFParagraph> paragraphs = xwpfTableCell.getParagraphs();
                        for (XWPFParagraph xwpfParagraph : paragraphs) {
                            List<XWPFRun> runs = xwpfParagraph.getRuns();
                            for(int i = 0; i < runs.size();i++){
                                XWPFRun run = runs.get(i);
                                tableText.append(run.toString());
                            }
                        }
                    }
                }
            }

            System.out.println(tableText.toString());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //模板文件地址
    private static String inputUrl2 = "C:\\Users\\lijian\\Desktop\\simple.docx";
    //新生产的模板文件
    private static String outputUrl2 = "C:\\Users\\lijian\\Desktop\\test.docx";
@Test
    public void testReplace(){


    /**
     *
     * @param inputUrl 模板路径
     * @param outputUrl 模板保存路径
     */
        String outputUrl = outputUrl2;
        String inputUrl = inputUrl2;

        try {
            //获取word文档解析对象
            XWPFDocument doucument = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));
            //获取段落文本对象
            List<XWPFParagraph> paragraph = doucument.getParagraphs();
            //获取首行run对象
            XWPFRun run = paragraph.get(0).getRuns().get(0);

            System.out.println(paragraph.get(0).getText());
            //设置文本内容
            run.setText("hello-world");
            //生成新的word
            File file = new File(outputUrl);

            FileOutputStream stream = new FileOutputStream(file);
            doucument.write(stream);
            stream.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
