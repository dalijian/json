package com.lijian.antlr.visitors;

import com.lijian.antlr.next.auto.PropertyFileBaseVisitor;
import com.lijian.antlr.next.auto.PropertyFileLexer;
import com.lijian.antlr.next.auto.PropertyFileParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestPropertyFileVisitor {

    public static void main(String[] args) {

        PropertyFileLexer lexer = new PropertyFileLexer(new ANTLRInputStream("user=\"parrt\"\n" +
                "machine=\"maniac\""));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        PropertyFileParser propertyFileParser = new PropertyFileParser(commonTokenStream);
        PropertyFileVisitor loader = new PropertyFileVisitor();
        // visitor 不需要 ParseTreeWalker
        // 通过 访问器 来 访问 语法分析器 生成 的 树
        loader.visit(propertyFileParser.file());
        System.out.println(loader.props);

    }

    public static class PropertyFileVisitor extends PropertyFileBaseVisitor<Void>{
        Map<String,String> props = new LinkedHashMap<>();

        @Override
        public Void visitProp(PropertyFileParser.PropContext ctx) {
            props.put(ctx.ID.getText(), ctx.STRING.getText());
            return null;
        }
    }
}
