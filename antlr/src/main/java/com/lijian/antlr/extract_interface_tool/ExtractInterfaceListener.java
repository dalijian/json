package com.lijian.antlr.extract_interface_tool;

import com.lijian.antlr.stringtemplate.JavaParser;
import com.lijian.antlr.stringtemplate.JavaParserBaseListener;
import org.antlr.v4.runtime.TokenStream;

/**
 * 抽取 java 中 的方法 生成 接口
 */
public class ExtractInterfaceListener extends JavaParserBaseListener {
    JavaParser parser;

    public ExtractInterfaceListener(JavaParser parser) {
        this.parser = parser;
    }

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        System.out.println("interface I" + ctx.IDENTIFIER().getText() + " {");

    }

    @Override
    public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        System.out.println("}");
    }

    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        // 获取 词法符号
        TokenStream tokens = parser.getTokenStream();


        String   type=  tokens.getText(ctx.typeTypeOrVoid());
        String args = tokens.getText(ctx.formalParameters());
        System.out.println("\t " + type + " " + ctx.IDENTIFIER().getText() + args + ";");

    }

    @Override
    public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) {

        System.out.println(ctx.getText());
    }
}
