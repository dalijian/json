package com.lijian.antlr_java.java8.test;

import com.lijian.antlr_java.java8.Java8Lexer;
import com.lijian.antlr_java.java8.Java8Parser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class JavaDemo {


    public static void main(String[] args) {
        ANTLRInputStream input = new ANTLRInputStream("package com.lijian.algorithms;\n" +
                "\n" +
                "import org.slf4j.Logger;\n" +
                "import org.slf4j.LoggerFactory;\n" +
                "import org.springframework.boot.SpringApplication;\n" +
                "import org.springframework.boot.autoconfigure.SpringBootApplication;\n" +
                "\n" +
                "import javax.annotation.PostConstruct;\n" +
                "\n" +
                "@SpringBootApplication\n" +
                "public class AlgorithmsApplication {\n" +
                "    Logger logger = LoggerFactory.getLogger(AlgorithmsApplication.class);\n" +
                "    public static void main(String[] args) {\n" +
                "        SpringApplication.run(AlgorithmsApplication.class, args);\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    @PostConstruct\n" +
                "    public void test(){\n" +
                "        for (int i = 0; i < 100000; i++) {\n" +
                "            logger.info(\"this is info log {}\", i);\n" +
                "            logger.error(\"this is info log {}\", i);\n" +
                "            logger.debug(\"this is info log {}\", i);\n" +
                "            logger.warn(\"this is info log {}\", i);\n" +
                "        }\n" +
                "\n" +
                "    }\n" +
                "}\n");
        //词法解析器，处理input
        Java8Lexer lexer = new Java8Lexer(input);
        //词法符号的缓冲器，存储词法分析器生成的词法符号
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        //语法分析器，处理词法符号缓冲区的内容
        Java8Parser parser = new Java8Parser(tokens);
//        // 针对 inti 规则，开始 语法分析
        ParseTree tree = parser.compilationUnit();
        // 用 LISP  风格 打印 生成 的 树
        System.out.println(tree.toStringTree(parser));

        // 新建 一个 通用 的 ， 能够 触发 回调 函数的 语法 分析 树 遍历 器
        ParseTreeWalker walker = new ParseTreeWalker();

// 遍历 语法分析 过程 中 生成 的 语法 分析 树 ，触发 回调
//        walker.walk(new ShortToUnitcodeString(), tree);
    }
}
