package com.lijian.antlr.listeners;

import com.lijian.antlr.listeners.auto.PropertyFileBaseListener;
import com.lijian.antlr.listeners.auto.PropertyFileLexer;
import com.lijian.antlr.listeners.auto.PropertyFileParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.UnbufferedCharStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestPropertyFile {


    public static class PropertyFileLoader extends PropertyFileBaseListener {

        Map<String, String> props = new LinkedHashMap<>();

        @Override
        public void exitProp(PropertyFileParser.PropContext ctx) {
            String id = ctx.ID().getText();
            String value = ctx.STRING().getText();
            props.put(id, value);
        }
    }

    public static void main(String[] args) throws IOException {
        PropertyFileLexer lexer = new PropertyFileLexer(new ANTLRInputStream(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\antlr\\src\\main\\java\\com.lijian.antlr\\listeners\\t.properties")));

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        PropertyFileParser parser = new PropertyFileParser(tokenStream);

        // 新建  ANTLR 语法 分析树 遍历器
        ParseTreeWalker walker = new ParseTreeWalker();
        // 新建  监听器
        PropertyFileLoader loader = new PropertyFileLoader();
        //遍历 语法分析树
        walker.walk(loader, parser.file());
    }
}
