package com.lijian.doc;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.TableWidthType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

//import com.hongcheng.javadoc_generator.entity.ClassComment;
//import com.hongcheng.javadoc_generator.entity.FieldComment;
//import com.hongcheng.javadoc_generator.entity.MethodComment;

public class WordExport {

    public void export(List<ClassComment> result,String path) throws Exception {
        XWPFDocument xwpfDocument = this.newWord();
        
        for(ClassComment classComment: result) {
            this.newParagraph(xwpfDocument, "类名：" + classComment.getClassName());
            this.newParagraph(xwpfDocument, "说明：" + classComment.getClassComment());
            // 字段
            if(classComment.getFields() != null && !classComment.getFields().isEmpty()) {
                XWPFTable table = this.newTable(xwpfDocument, classComment.getFields().size() + 3, 5);
                this.mergeCell(table.getRow(0), 0, 4, "类名：" + classComment.getClassName());
                this.mergeCell(table.getRow(1), 0, 4, "属性：");
                this.setTableRowText(table.getRow(2), 0, 4, "序号","参数类型","参数名","常量值","说明");
                this.setCellWidth(table.getRow(2), 0, 4, "10%","22.5%","22.5%","22.5%","22.5%");
                
                for(int i = 0,j = 3;i < classComment.getFields().size();i++,j++ ) {
                    FieldComment field = classComment.getFields().get(i);
                    this.setTableRowText(table.getRow(j), 0, 4, String.valueOf(i + 1)
                            ,field.getSimpleClassName()
                            ,field.getFieldName()
                            ,field.getDefaultValue() == null?"":field.getDefaultValue().toString()
                            ,field.getFieldComment());
                }
                this.newBlankLine(xwpfDocument);
                this.newBlankLine(xwpfDocument);
            }
            // 方法
            if(classComment.getMethods() != null && !classComment.getMethods().isEmpty()) {
                for(MethodComment method: classComment.getMethods()) {
                    XWPFTable table = this.newTable(xwpfDocument, 3, 4);
                    this.mergeCell(table.getRow(0), 0, 3, "类名：" + classComment.getClassName());
                    this.mergeCell(table.getRow(1), 0, 3, "方法名：" + method.getMethodName());
                    this.mergeCell(table.getRow(2), 0, 3, "方法说明：" + method.getMethodComment());
                    // 参数
                    if(method.getParams() == null || method.getParams().isEmpty()) {
                        this.mergeCell(table.createRow(), 0, 3, "参数：无" );
                    }else {
                        this.mergeCell(table.createRow(), 0, 3, "参数：" );
                        this.setTableRowText(table.createRow(), 0, 3, "序号","参数类型","参数名","说明");
                        this.setCellWidth(table.getRow(table.getRows().size()-1), 0, 3, "10%","25%","25%","40%");
                        for(int i = 0;i < method.getParams().size(); i++) {
                            FieldComment field = method.getParams().get(i);
                            this.setTableRowText(table.createRow(), 0, 3, String.valueOf(i + 1)
                                    ,field.getSimpleClassName()
                                    ,field.getFieldName()
                                    ,field.getFieldComment());
                        }
                    }
                    // 返回值
                    this.mergeCell(table.createRow(), 0, 3, "返回值：" + method.getReturnEntity().getSimpleClassName() + "  " + method.getMethodComment());
                    this.newBlankLine(xwpfDocument);
                    this.newBlankLine(xwpfDocument);
                }
            }
            this.newBlankLine(xwpfDocument);
            this.newBlankLine(xwpfDocument);
            this.newBlankLine(xwpfDocument);
            this.newBlankLine(xwpfDocument);

        }
        this.writeFile(xwpfDocument, path);
    }
    
    
    
    
    /**
     *     设置单元格宽度
     *     @param row  
     *     @param startCell 起始单元格下标，row的单元格下标从0开始
     *     @param endCell 结束单元格下标
     *     @param percentages 各个单元格的百分百大小，例如"25.5%"
     * */
    private void setCellWidth(XWPFTableRow row,int startCell,int endCell,String ...percentages) {
        if(percentages == null || percentages.length <= 0) {
            throw new IllegalArgumentException("percentages不能为空");
        }
        if((endCell - startCell + 1) > percentages.length) {
            throw new IllegalArgumentException("percentages的元素不够");
        }
        int i = 0;
        for(XWPFTableCell cell: row.getTableCells()) {
            cell.setWidth(String.valueOf(percentages[i++]));
            cell.setWidthType(TableWidthType.PCT);
        }
    }
    
    
    /**
     *     设置单元格宽度
     *     @param row  
     *     @param startCell 起始单元格下标，row的单元格下标从0开始
     *     @param endCell 结束单元格下标
     *     @param sizes 各个单元格的宽度大小
     * */
    @SuppressWarnings("unused")
    private void setCellWidth(XWPFTableRow row,int startCell,int endCell,int ...sizes) {
        if(sizes == null || sizes.length <= 0) {
            throw new IllegalArgumentException("sizes不能为空");
        }
        if((endCell - startCell + 1) > sizes.length) {
            throw new IllegalArgumentException("sizes的元素不够");
        }
        int i = 0;
        for(XWPFTableCell cell: row.getTableCells()) {
            cell.setWidth(String.valueOf(sizes[i++]));
            cell.setWidthType(TableWidthType.DXA);
        }
    }
    
    
    
    /**
     *     跨行合并单元格
     *     @param table  
     *     @param startRow 起始行下标，table的行下标从0开始
     *     @param endRow 结束行下标
     *     @param startCell 行内起始单元格下标，row的单元格下标从0开始
     *     @param endCell 行内结束单元格下标
     *     @param text 合并后的单元格文本
     * */
    @SuppressWarnings("unused")
    private void mergeRow(XWPFTable table,int startRow,int endRow,int startCell,int endCell,String text) {
        List<XWPFTableRow> rows = table.getRows();
        for (int j = startRow; j <= endRow; j++) {
            List<XWPFTableCell> tableCells = rows.get(j).getTableCells();
            // 对每个单元格进行操作
            for (int i = startCell; i <= endCell; i++) {
            //对单元格进行合并的时候,要标志单元格是否为起点,或者是否为继续合并
                if (i == startCell )
                    tableCells.get(i).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
                else
                    tableCells.get(i).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);//继续合并
            }
        }
        for (int j = startRow; j <= endRow; j++) {
            List<XWPFTableCell> tableCells = rows.get(j).getTableCells();
            // 对每个单元格进行操作
            //对单元格进行合并的时候,要标志单元格是否为起点,或者是否为继续合并
            if (j == startRow )
                tableCells.get(startCell).getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            else
                tableCells.get(startCell).getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);//继续合并
        }
        rows.get(startRow).getCell(startCell).setText(text);//为第1行1到4合并之后的单元格设置内容
    }
    
    
    
    /**
     *     合并表格单元格，针对行内的单元格进行合并
     *     @param row  
     *     @param startCell 起始单元格下标，row的单元格下标从0开始
     *     @param endCell 结束单元格下标
     *     @param text 合并后的单元格文本
     * */
    private void mergeCell(XWPFTableRow row,int startCell,int endCell,String text) {
        List<XWPFTableCell> tableCells = row.getTableCells();
        //     对每个单元格进行操作
        for (int i = startCell; i <= endCell; i++) {
        //对单元格进行合并的时候,要标志单元格是否为起点,或者是否为继续合并
            if (i == startCell)
                tableCells.get(i).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            else
                tableCells.get(i).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);//继续合并
        }
        tableCells.get(startCell).setText(text);//为第1行1到4合并之后的单元格设置内容
    }
    
    
    
    /**
     *     给表格一行赋值，实际设置值是包括首尾单元格的，例如startCell=0，endCell=2，实际会设置0、1、2这三个单元格
     *     @param row
     *     @param startCell 起始单元格下标，row的单元格下标从0开始
     *     @param endCell 结束单元格下标
     *     @param texts 单元格的内容，依次赋值
     * */
    private void setTableRowText(XWPFTableRow row,int startCell,int endCell,String ...texts) {
        if(texts == null || texts.length <= 0) {
            throw new IllegalArgumentException("texts不能为空");
        }
        if((endCell - startCell + 1) > texts.length) {
            throw new IllegalArgumentException("texts的元素不够");
        }
        List<XWPFTableCell> tableCells = row.getTableCells();
        //     对每个单元格进行操作
        for (int i = startCell,j = 0; i <= endCell; i++,j++) {
            tableCells.get(i).setText(texts[j]);
        }
    }
    
    
    /**
     *     创建一个table
     *     @param xwpfDocument 
     *     @param rowNum 行数
     *     @param colNum 列数
     * */
    private XWPFTable newTable(XWPFDocument xwpfDocument,int rowNum,int colNum) {
        XWPFTable createTable = xwpfDocument.createTable(rowNum, colNum);
        createTable.setWidth("100%");
        createTable.setWidthType(TableWidthType.PCT);
        return createTable;
    }
    
    
    /**
     *     创建一个文本行
     * */
    private XWPFParagraph newParagraph(XWPFDocument xwpfDocument,String text) {
        XWPFParagraph createParagraph = xwpfDocument.createParagraph();
        createParagraph.createRun().setText(text);
        return createParagraph;
    }
    
    /**
     *     创建一个空行
     * */
    private XWPFParagraph newBlankLine(XWPFDocument xwpfDocument) {
        return this.newParagraph(xwpfDocument, "");
    }
    
    /**
     *     创建一个word文档
     * */
    private XWPFDocument newWord() {
        XWPFDocument xwpfDocument = new XWPFDocument();
        return xwpfDocument;
    }
    
    /**
     *     写文件
     * */
    private void writeFile(XWPFDocument xwpfDocument,String path) throws Exception {
        xwpfDocument.write(new FileOutputStream("C:\\Users\\HongCheng\\Desktop\\1.docx"));
        xwpfDocument.close();
    }
}