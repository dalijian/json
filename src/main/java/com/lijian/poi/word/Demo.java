package com.lijian.poi.word;


import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.io.IOException;
import java.util.List;

public class Demo {
    public static void main(String[] args) {


    }
    public void test(String inputUrl) throws IOException {
        //解析docx模板并获取document对象
        XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));
        //获取整个文本对象
        List<XWPFParagraph> allParagraph = document.getParagraphs();
        //获取整个表格对象
        List<XWPFTable> allTable = document.getTables();
        //获取图片对象
        XWPFPictureData pic = document.getPictureDataByID("PICId");
    }
}
