package com.lijian.antlr.stringtemplate;

import com.lijian.antlr.stringtemplate.JavaLexer;
import com.lijian.antlr.stringtemplate.JavaParser;
import com.lijian.antlr.stringtemplate.UnitTestGenerator;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class JavaCodeParseTest {

    public static void main(String[] args) {
        String code =
                "package com.jcache.store;" +
                        "public class CacheStore {" +
                        "Object getCache(int a) {" +
                        "if (a == 1)" +
                        "return 1;" +
                        "else " +
                        "return 2;" +
                        "}" +
                        "void setCache(int a) {" +
                        "return;" +
                        "}" +
                        "}";

        // 1.Lexical analysis
        JavaLexer lexer = new JavaLexer(new ANTLRInputStream(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 2.Syntax analysis
        JavaParser parser = new JavaParser(tokens);
        ParseTree tree = parser.compilationUnit();

        // 3.Application based on Syntax Tree
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new UnitTestGenerator(), tree);
    }



}