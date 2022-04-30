package com.lijian.antlr.extract_interface_tool;

import com.lijian.antlr.stringtemplate.JavaLexer;
import com.lijian.antlr.stringtemplate.JavaParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.IOException;

public class ExtractInterfaceTool {

    public static void main(String[] args) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(
                new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\antlr\\src\\main\\java\\com\\lijian\\antlr\\extract_interface_tool\\TestJava.java")
        );

        JavaLexer lexer = new JavaLexer(input);

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        JavaParser javaParser = new JavaParser(tokenStream);

        ParseTree tree = javaParser.compilationUnit();// 开始 语法 分析 的 过程

        ParseTreeWalker walker = new ParseTreeWalker();
        System.out.println(tree.toStringTree(javaParser));
        ExtractInterfaceListener extractInterfaceListener = new ExtractInterfaceListener(javaParser);

        walker.walk(extractInterfaceListener, tree);



    }
}
