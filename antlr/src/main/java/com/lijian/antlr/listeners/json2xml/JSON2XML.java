package com.lijian.antlr.listeners.json2xml;

import com.lijian.antlr.listeners.csv.LoadCSV;
import com.lijian.antlr.listeners.csv.auto.CSVLexer;
import com.lijian.antlr.listeners.csv.auto.CSVParser;
import com.lijian.antlr.listeners.json2xml.auto.JSONBaseListener;
import com.lijian.antlr.listeners.json2xml.auto.JSONLexer;
import com.lijian.antlr.listeners.json2xml.auto.JSONParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class JSON2XML {

    public static void main(String[] args) throws IOException {
//        JSONLexer csvLexer = new JSONLexer(new ANTLRInputStream("{\n" +
//                "\t\t\"name\":\"test-source-sqlite-jdbc-autoincrement\",\n" +
//                "\t\t\"tasks.max\":1,\n" +
//                "\t\t\"connector.class\":\"io.confluent.connect.jdbc.JdbcSourceConnector\",\n" +
//                "\t\t\"topic.prefix\":\"test-sqlite-jdbc-\",\n" +
//                "\t\t\"topic\":\"mysql_database_test\",\n" +
//                "\t\t\"connection.url\":\"jdbc:mysql://localhost:3306/test?serverTimezone=Hongkong\",\n" +
//                "\t\t\"connection.user\":\"root\",\n" +
//                "\t\t\"connection.password\":\"645143\",\n" +
//                "\t\t\"connection.attempts\":3,\n" +
//                "\t\t\"connection.backoff.ms\":100,\n" +
//                "\t\t\"mode\":\"timestamp+incrementing\",\n" +
//                "\t\t\"incrementing.column.name\":\"id\",\n" +
//                "\t\t\"timestamp.column.name\":\"update_time\",\n" +
//                "\t\t\"table.types\":\"table\",\n" +
//                "\t\t\"dialect.name\":\"MySqlDatabaseDialect\"\n" +
//                "}"));
//
//        CommonTokenStream tokenStream = new CommonTokenStream(csvLexer);
//        JSONParser parser = new JSONParser(tokenStream);
//
//        ParseTreeWalker walker = new ParseTreeWalker();
//        JSON2XML.XMLEmitter loader = new JSON2XML.XMLEmitter();
//        walker.walk(loader, parser.json());
//
//        System.out.println(loader.getXML(parser.json()));


        String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) {
            is = new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\antlr\\src\\main\\java\\com\\lijian\\antlr\\listeners\\json2xml\\test.json");
        }
        ANTLRInputStream input = new ANTLRInputStream(is);
        JSONLexer lexer = new JSONLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JSONParser parser = new JSONParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.json();
        // show tree in text form
//        System.out.println(tree.toStringTree(parser));

        ParseTreeWalker walker = new ParseTreeWalker();
        XMLEmitter converter = new XMLEmitter();
        walker.walk(converter, tree);
        System.out.println(converter.getXML(tree));

    }
//    public static  class XMLEmitter extends JSONBaseListener{
//        // 将 每颗 子树 翻译完的字符串 存储 在 该 子树 的 根节点 中。
//        // 工作 在 语法分析树 更高层 节点 上 的 方法 就 能 狗  获得 它们，从而 构造 出 更大的字符串
//        // 语法分析树 的 根节点 中 存储 的 字符串 就是 最终 的 翻译 结果
//        ParseTreeProperty<String> xml = new ParseTreeProperty<>();
//        String getXML(ParseTree ctx){
//            return xml.get(ctx);}
//
//            void setXML(ParseTree ctx,String s){
//                xml.put(ctx, s);}
//
//        @Override
//        public void exitAtom(JSONParser.AtomContext ctx) {
//            setXML(ctx, ctx.getText());
//
//        }
//
//        @Override
//        public void exitString(JSONParser.StringContext ctx) {
//            setXML(ctx, stringQuotes(ctx.getText()));
//        }
//        // 去除 string 的 双引号
//        private String stringQuotes(String text) {
//            if (text.startsWith("\"")) {
//           text=     text.replaceFirst("\"", "");
//            }
//            if (text.endsWith("\"")) {
//                text = text.split("\"$")[0];
//            }
//            return text;
//        }
//
//        // 处理 对象
//        @Override
//        public void exitObjectValue(JSONParser.ObjectValueContext ctx) {
//            setXML(ctx, getXML(ctx.object()));
//        }
//        // 需要 处理 键值对 ，将 它们 转换 为 标签 和 文本 。
//        // 生成 的 XML 的 标签 名 来自 于 STRING ':'value 备选分支 中 的STRING.
//        // 开始 和 结束 标签 之间 的 文本 来 源 于 value 子 节点
//        @Override
//        public void exitPair(JSONParser.PairContext ctx) {
//            // 生成 xml 标签
//            String tag = stringQuotes(ctx.STRING().getText());
//            JSONParser.ValueContext vctx = ctx.value();
//
//            String x = String.format("<%s>%s</%s>\n", tag, getXML(vctx), tag);
//            setXML(ctx, x);
//
//        }
//        // 对于 每个 object 规则 在 AnObject 备选 分支 中 发现 的 键值对，我们 将 其 对应 的 xml 追加 到 语法 分析 树 中 存储 的 结果 之后 。
//        @Override
//        public void exitAnObject(JSONParser.AnObjectContext ctx) {
//            StringBuilder buf = new StringBuilder();
//            buf.append("\n");
//            for (JSONParser.PairContext pctx : ctx.pair()) {
//                buf.append(getXML(pctx));
//            }
//            setXML(ctx, buf.toString());
//        }
//
//        @Override
//        public void exitEmptyObject(JSONParser.EmptyObjectContext ctx) {
//            setXML(ctx, "");
//        }
//
//        @Override
//        public void exitArrayOfValues(JSONParser.ArrayOfValuesContext ctx) {
//            StringBuilder buf  = new StringBuilder();
//
//            buf.append("\n");
//            for (JSONParser.ValueContext context : ctx.value()) {
//                buf.append("<element>");
//                buf.append(getXML(context));
//                buf.append("</element>");
//                buf.append("\n");
//
//            }
//
//            setXML(ctx, buf.toString());
//        }
//
//        @Override
//        public void exitEmptyArray(JSONParser.EmptyArrayContext ctx) {
//
//            setXML(ctx, "");
//        }
//
//        @Override
//        public void exitJson(JSONParser.JsonContext ctx) {
//            setXML(ctx, getXML(ctx.getChild(0)));
//        }
//    }

    public static class XMLEmitter extends JSONBaseListener {
        ParseTreeProperty<String> xml = new ParseTreeProperty<String>();
        String getXML(ParseTree ctx) { return xml.get(ctx); }
        void setXML(ParseTree ctx, String s) { xml.put(ctx, s); }

        @Override
        public void exitJson(JSONParser.JsonContext ctx) {
            setXML(ctx, getXML(ctx.getChild(0)));
        }

        @Override
        public void exitAnObject(JSONParser.AnObjectContext ctx) {
            StringBuilder buf = new StringBuilder();
            buf.append("\n");
            for (JSONParser.PairContext pctx : ctx.pair()) {
                buf.append(getXML(pctx));
            }
            setXML(ctx, buf.toString());
        }
        @Override
        public void exitEmptyObject(JSONParser.EmptyObjectContext ctx) {
            setXML(ctx, "");
        }

        @Override
        public void exitArrayOfValues(JSONParser.ArrayOfValuesContext ctx) {
            StringBuilder buf = new StringBuilder();
            buf.append("\n");
            for (JSONParser.ValueContext vctx : ctx.value()) {
                buf.append("<element>"); // conjure up element for valid XML
                buf.append(getXML(vctx));
                buf.append("</element>");
                buf.append("\n");
            }
            setXML(ctx, buf.toString());
        }

        @Override
        public void exitEmptyArray(JSONParser.EmptyArrayContext ctx) {
            setXML(ctx, "");
        }

        @Override
        public void exitPair(JSONParser.PairContext ctx) {
            String tag = stripQuotes(ctx.STRING().getText());
            JSONParser.ValueContext vctx = ctx.value();
            String x = String.format("<%s>%s</%s>\n", tag, getXML(vctx), tag);
            setXML(ctx, x);
        }

        @Override
        public void exitObjectValue(JSONParser.ObjectValueContext ctx) {
            // analogous to String value() {return object();}
            setXML(ctx, getXML(ctx.object()));
        }

        @Override
        public void exitArrayValue(JSONParser.ArrayValueContext ctx) {
            setXML(ctx, getXML(ctx.array())); // String value() {return array();}
        }

        @Override
        public void exitAtom(JSONParser.AtomContext ctx) {
            setXML(ctx, ctx.getText());
        }

        @Override
        public void exitString(JSONParser.StringContext ctx) {
            setXML(ctx, stripQuotes(ctx.getText()));
        }

        public static String stripQuotes(String s) {
            if ( s==null || s.charAt(0)!='"' ) return s;
            return s.substring(1, s.length() - 1);
        }
    }
}
