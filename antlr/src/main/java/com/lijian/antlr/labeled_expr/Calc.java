package com.lijian.antlr.labeled_expr;

import com.lijian.antlr.expr.auto.LabeledExprLexer;
import com.lijian.antlr.expr.auto.LabeledExprParser;
import com.lijian.antlr.labeled_expr.EvalVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Calc {
    public static void main(String[] args) throws IOException {
        String inputFile =null;
        if (args.length>0) inputFile = args[0];
        InputStream is = System.in;

        if (inputFile != null) {
            is = new FileInputStream(inputFile);
        }
        ANTLRInputStream inputStream = new ANTLRInputStream("1+10+1");
        LabeledExprLexer lexer = new LabeledExprLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        LabeledExprParser parser = new LabeledExprParser(tokenStream);
        ParseTree parseTree = parser.prog(); // prog 规则 开始 进行 语法 分析
        System.out.println(parseTree.toStringTree(parser)); // 以 文本 形式 打印 树


        System.out.println("****************************");
        EvalVisitor evalVisitor = new EvalVisitor();
        // 使用 visitor 计算 计算器 的 结果
        evalVisitor.visit(parseTree);

    }
}
