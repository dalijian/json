package com.lijian.antlr_java.java8.test;

import com.lijian.antlr_java.java8.Java8Parser;
import com.lijian.antlr_java.java8.Java8ParserBaseListener;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class InsertSerialIdListener  extends Java8ParserBaseListener {

    TokenStreamRewriter rewriter;

    public InsertSerialIdListener(TokenStream tokenStream) {
        this.rewriter = new TokenStreamRewriter(tokenStream);

    }

    @Override
    public void enterClassBody(Java8Parser.ClassBodyContext ctx) {

        String field = "\n\t public static final long serialVersionUID=1L";

        rewriter.insertAfter(ctx.start, field);
    }
}
