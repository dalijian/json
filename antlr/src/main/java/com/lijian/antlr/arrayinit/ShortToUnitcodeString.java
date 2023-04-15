package com.lijian.antlr.arrayinit;

import com.lijian.antlr.arrayinit.auto.ArrayInitBaseListener;
import com.lijian.antlr.arrayinit.auto.ArrayInitParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class ShortToUnitcodeString extends ArrayInitBaseListener {

    /**
     * 将 { 翻译 为 “
     * @param ctx
     */
    @Override
    public void enterInit(ArrayInitParser.InitContext ctx) {
        ctx.getStart().getText();
        System.out.println('"');

    }

    /**
     * 将 } 翻译 为 ”
     * @param ctx
     */
    @Override
    public void exitInit(ArrayInitParser.InitContext ctx) {
        System.out.println('"');
    }

    /**
     * 将 每个 整数 翻译 为 四位的 十六进制 形式 ， 然后 加 前缀
     *ctx.INT() 对应 value  语法 备选项 int
     *
     * @param ctx
     */
    @Override
    public void enterValue(ArrayInitParser.ValueContext ctx) {
//        假定不存在嵌套 结构
        try {
            int value = Integer.valueOf(ctx.INT().getText());
            System.out.printf("\\u%04x", value);
        } catch (NullPointerException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void exitValue(ArrayInitParser.ValueContext ctx) {
        super.exitValue(ctx);
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        super.enterEveryRule(ctx);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        super.exitEveryRule(ctx);
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        super.visitTerminal(node);
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);
    }
}
