package com.lijian.antlr.listeners.csv;

import com.lijian.antlr.listeners.csv.auto.CSVBaseListener;
import com.lijian.antlr.listeners.csv.auto.CSVLexer;
import com.lijian.antlr.listeners.csv.auto.CSVParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LoadCSV {
    public static void main(String[] args) throws IOException {

        CSVLexer csvLexer = new CSVLexer(new ANTLRInputStream(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\antlr\\src\\main\\java\\com\\lijian\\antlr\\listeners\\csv\\t.csv")));

        CommonTokenStream tokenStream = new CommonTokenStream(csvLexer);
        CSVParser parser = new CSVParser(tokenStream);

        ParseTreeWalker walker = new ParseTreeWalker();
        Loader loader = new Loader();
        walker.walk(loader, parser.file());

        System.out.println(loader.rows);

    }

    public static class Loader extends CSVBaseListener{

        public static final String EMPTY="";

        List<Map<String,String>> rows = new ArrayList<>();
        // 列名 列表
        List<String> header;
        // 存放一个当前行中所有字段值的列表
        List<String> currentRowFieldValues;


        @Override
        public void exitString(CSVParser.StringContext ctx) {
            currentRowFieldValues.add(ctx.STRING().getText());

        }

        @Override
        public void exitText(CSVParser.TextContext ctx) {
            currentRowFieldValues.add(ctx.TEXT().getText());

        }

        @Override
        public void exitEmpty(CSVParser.EmptyContext ctx) {
            currentRowFieldValues.add(EMPTY);
        }

        @Override
        public void exitHdr(CSVParser.HdrContext ctx) {
            header=new ArrayList<>();
            header.addAll(currentRowFieldValues);
        }

        @Override
        public void enterRow(CSVParser.RowContext ctx) {
            currentRowFieldValues=new ArrayList<>();
        }

        @Override
        public void exitRow(CSVParser.RowContext ctx) {
            // 如果 当前行 是 标题行，什么 都 不做
            if (ctx.getParent().getRuleIndex() == CSVParser.RULE_hdr) {
                return;
            }
            // 处理 数据行
            Map<String,String> m = new LinkedHashMap<>();
            for (int i = 0; i < currentRowFieldValues.size(); i++) {
                m.put(header.get(i), currentRowFieldValues.get(i));

            }
            rows.add(m);

        }
    }
}
