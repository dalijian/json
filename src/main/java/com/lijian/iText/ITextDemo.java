package com.lijian.iText;

import java.awt.Color;
import java.io.FileOutputStream;


import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Section;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
public class ITextDemo {
    public boolean iTextTest() {
        try {
            /** 实例化文档对象 */
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            /** 创建 PdfWriter 对象 */
            PdfWriter.getInstance(document,// 文档对象的引用
                    new FileOutputStream("d://ITextTest.pdf"));//文件的输出路径+文件的实际名称
            document.open();// 打开文档
            /** pdf文档中中文字体的设置，注意一定要添加iTextAsian.jar包 */
            BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);//加入document：
            /** 向文档中添加内容，创建段落对象 */
            document.add(new Paragraph("First page of the document."));// Paragraph添加文本
            document.add(new Paragraph("我们是害虫", FontChinese));
            /** 创建章节对象 */
            Paragraph title1 = new Paragraph("第一章", FontChinese);
            Chapter chapter1 = new Chapter(title1, 1);
            chapter1.setNumberDepth(0);
            /** 创建章节中的小节 */
            Paragraph title11 = new Paragraph("表格的添加", FontChinese);
            Section section1 = chapter1.addSection(title11);
            /** 创建段落并添加到小节中 */
            Paragraph someSectionText = new Paragraph("下面展示的为3 X 2 表格.",
                    FontChinese);
            section1.add(someSectionText);
            /** 创建表格对象（包含行列矩阵的表格） */
            Table t = new Table(3, 2);// 2行3列
            t.setBorderColor(new Color(220, 255, 100));
            t.setPadding(5);
            t.setSpacing(5);
            t.setBorderWidth(1);
            Cell c1 = new Cell(new Paragraph("第一格", FontChinese));
            t.addCell(c1);
            c1 = new Cell("Header2");
            t.addCell(c1);
            c1 = new Cell("Header3");
            t.addCell(c1);
            // 第二行开始不需要new Cell()
            t.addCell("1.1");
            t.addCell("1.2");
            t.addCell("1.3");
            section1.add(t);
            /** 创建章节中的小节 */
            Paragraph title13 = new Paragraph("列表的添加", FontChinese);
            Section section3 = chapter1.addSection(title13);
            /** 创建段落并添加到小节中 */
            Paragraph someSectionText3 = new Paragraph("下面展示的为列表.", FontChinese);
            section3.add(someSectionText3);
            /** 创建列表并添加到pdf文档中 */
            List l = new List(true, true, 10);// 第一个参数为true，则创建一个要自行编号的列表，
            // 如果为false则不进行自行编号
            l.add(new ListItem("First item of list"));
            l.add(new ListItem("第二个列表", FontChinese));
            section3.add(l);
            document.add(chapter1);
            /** 创建章节对象 */
            Paragraph title2 = new Paragraph("第二章", FontChinese);
            Chapter chapter2 = new Chapter(title2, 1);
            chapter2.setNumberDepth(0);
            /** 创建章节中的小节 */
            Paragraph title12 = new Paragraph("png图片添加", FontChinese);
            Section section2 = chapter2.addSection(title12);
            /** 添加图片 */
            section2.add(new Paragraph("图片添加: 饼图", FontChinese));
            Image png = Image.getInstance("D:/CheatSheet.jpg");//图片的地址
            section2.add(png);
            document.add(chapter2);
            document.close();
            return true;
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
        }
        return false;
    }
    public static void main(String args[]) {
        System.out.println(new ITextDemo().iTextTest());
    }
}