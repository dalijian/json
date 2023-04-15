package com.lijian.antlr.arrayinit;






import com.lijian.antlr.arrayinit.auto.ArrayInitLexer;
import com.lijian.antlr.arrayinit.auto.ArrayInitParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {

        ANTLRInputStream input = new ANTLRInputStream("{2,99,{1,2,34},4}");
        //词法解析器，处理input
        ArrayInitLexer lexer = new ArrayInitLexer(input);
        //词法符号的缓冲器，存储词法分析器生成的词法符号
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        //语法分析器，处理词法符号缓冲区的内容
        ArrayInitParser parser = new ArrayInitParser(tokens);
        // 针对 inti 规则，开始 语法分析
        ParseTree tree = parser.init();
        // 用 LISP  风格 打印 生成 的 树
        System.out.println(tree.toStringTree(parser));

        // 新建 一个 通用 的 ， 能够 触发 回调 函数的 语法 分析 树 遍历 器
        ParseTreeWalker walker = new ParseTreeWalker();
// 遍历 语法分析 过程 中 生成 的 语法 分析 树 ，触发 回调
        walker.walk(new ShortToUnitcodeString(), tree);

    }
}
