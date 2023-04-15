package com.lijian.antlr.expr;

import com.lijian.antlr.expr.auto.ExprLexer;
import com.lijian.antlr.expr.auto.ExprParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ExprJoyRide {
    public static void main(String[] args) throws IOException {
        String inputFile = null;
        if (args.length > 0) inputFile = args[0];
        InputStream is = System.in;

        if (inputFile != null) {
            is = new FileInputStream(inputFile);
        }
        ANTLRInputStream inputStream = new ANTLRInputStream("1 + 2 * 3/4 ");
        ExprLexer lexer = new ExprLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokenStream);
        ParseTree parseTree = parser.prog(); // prog 规则 开始 进行 语法 分析
        System.out.println(parseTree.toStringTree(parser)); // 以 文本 形式 打印 树


    }
}
