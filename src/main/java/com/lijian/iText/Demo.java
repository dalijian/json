//package com.lijian.iText;
//
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.pdf.BaseFont;
//import javafx.scene.paint.Color;
//
//public class Demo {
//
//public static void main(String[]args){
//        // 向document 生成pdf表格
//        Table table = new Table(6);
//        table.setWidth(80); // 宽度
//        table.setBorder(1); // 边框
//        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER); // 水平对齐方式
//        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP); // 垂直对齐方式
//
//        // 设置表格字体
//        BaseFont cn = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
//        Font font = new Font(cn, 10, Font.NORMAL, Color.BLUE);
//
//        // 向表格写数据
//        // 表头
//        table.addCell(buildCell("编号", font));
//        table.addCell(buildCell("到达地", font));
//        table.addCell(buildCell("货物", font));
//        table.addCell(buildCell("数量", font));
//        table.addCell(buildCell("重量", font));
//        table.addCell(buildCell("配载要求", font));
//
//        // 表格数据
//        for (WorkOrderManage workOrderManage : workOrderManages) {
//        table.addCell(buildCell(workOrderManage.getId(), font));
//        table.addCell(buildCell(workOrderManage.getArrivecity(), font));
//        table.addCell(buildCell(workOrderManage.getProduct(), font));
//        table.addCell(buildCell(workOrderManage.getNum().toPlainString(), font));
//        table.addCell(buildCell(workOrderManage.getWeight().toString(), font));
//        table.addCell(buildCell(workOrderManage.getFloadreqr(), font));
//        }
//
//        // 向文档添加表格
//        document.add(table);
//        document.close();
//        }
//}
