package com.lijian.test.poi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lijian.json.jackon.Friend;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Commission {
     static  ObjectMapper  objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        Map<String,String> json =null;
        try {
             json = objectMapper.readValue(new File("result.json"), LinkedHashMap.class);
            System.out.println(json);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        Commission commission = new Commission();
        List<Tag> totalList = new ArrayList<>();
        totalList.add(new Tag("单位名称", "清华"));
        totalList.add(new Tag("综合评估得分", "10"));
        totalList.add(new Tag("风险等级", "危险"));
        totalList.add(new Tag("评估时间", "2018-1-1"));

        List<Tag> modelList = new ArrayList<>();
        modelList.add(new Tag("消防安全管理", "100"));
        modelList.add(new Tag("子建筑A", "100"));
        modelList.add(new Tag("子建筑B", "100"));
        modelList.add(new Tag("室外", "100"));
        Map<String,List<Tag>> models = new HashMap<>();

        commission.create(totalList, modelList,  models);



    }

    public void create(List<Tag> totalList, List<Tag> modelList,Map<String,List<Tag>> models) {
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph p1 = doc.createParagraph();
        XWPFRun run = p1.createRun();
        run.setText("综合消防安全评估报告");


        XWPFTable table = doc.createTable(4, 2);
        // CTTblBorders borders=table.getCTTbl().getTblPr().addNewTblBorders();
        CTTblPr tblPr = table.getCTTbl().getTblPr();
        tblPr.getTblW().setType(STTblWidth.DXA);
        tblPr.getTblW().setW(new BigInteger("7000"));
        for (int i = 0; i < 4; i++) {
            List<XWPFTableCell> row0List = table.getRow(i).getTableCells();

            row0List.get(0).setText(totalList.get(i).getName());
            row0List.get(1).setText(totalList.get(i).getValue());
        }
        doc.createParagraph();

        XWPFParagraph p2 = doc.createParagraph();
        XWPFRun run2 = p2.createRun();
        run2.setText("各模块安全评估得分");

        XWPFTable table2 = doc.createTable(modelList.size(), 2);
        tblPr = table2.getCTTbl().getTblPr();
        tblPr.getTblW().setType(STTblWidth.DXA);
        tblPr.getTblW().setW(new BigInteger("7000"));
        for (int i = 0; i < modelList.size(); i++) {
            List<XWPFTableCell> row0List = table2.getRow(i).getTableCells();

            row0List.get(0).setText(modelList.get(i).getName());
            row0List.get(1).setText(modelList.get(i).getValue());


        }
        doc.createParagraph();
        XWPFParagraph p3 = doc.createParagraph();
        XWPFRun run3 = p3.createRun();
        run3.setText("各模块评分详细");

        for (Map.Entry<String, List<Tag>> entry : models.entrySet()) {

                String title = entry.getKey();
                List<Tag> tagList = entry.getValue();

                XWPFTable table3 = doc.createTable(tagList.size()+1, 2);
                tblPr = table3.getCTTbl().getTblPr();
                tblPr.getTblW().setType(STTblWidth.DXA);
                tblPr.getTblW().setW(new BigInteger("7000"));
                List<XWPFTableCell> rowtitle = table3.getRow(0).getTableCells();
                XWPFTableCell cellTitle = rowtitle.get(0);
                cellTitle.setText(title);
                mergeCellsHorizontal(table3,1,0,1);
                for (int i = 1; i < modelList.size()+1; i++) {
                    List<XWPFTableCell> row0List = table3.getRow(i).getTableCells();
                    row0List.get(0).setText(modelList.get(i).getName());
                    row0List.get(1).setText(modelList.get(i).getValue());


                }


        }



        try {
            doc.write(new FileOutputStream(new File("C:/users/lijian/desktop/qinghua.docx")));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                doc.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * @Description: 合并列
     */
    public  void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if ( cellIndex == fromCell ) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }
}

class Tag {

    private String name;
    private String value;

    public Tag(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
