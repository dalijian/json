package com.lijian.antlr.labeled_expr;

import com.lijian.antlr.expr.auto.LabeledExprBaseVisitor;
import com.lijian.antlr.expr.auto.LabeledExprParser;

import java.util.LinkedHashMap;
import java.util.Map;

public class EvalVisitor extends LabeledExprBaseVisitor<Integer> {

    /**
     * 存储 变量名 和 变量值 的 对应 关系
     */
    Map<String,Integer> memory = new LinkedHashMap<>();

    @Override
    public Integer visitProg(LabeledExprParser.ProgContext ctx) {
        return super.visitProg(ctx);
    }

    /**
     *  expr NEWLINE
     * @param ctx
     * @return
     */
    @Override
    public Integer visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr()); // 计算 expr 子节点 的 值
        System.out.println(value);
        return 0;
    }

    /**
     *  ID '=' expr NEWLINE
     *  赋值 语句
     * @param ctx
     * @return
     */
    @Override
    public Integer visitAssign(LabeledExprParser.AssignContext ctx) {
        String id = ctx.ID().getText(); // id 在 '=' 的 左 侧
        int value = visit(ctx.expr());  // 计算 右侧 表达式 的 值
        memory.put(id, value);          // 存储 映射
        return  value;
    }

    @Override
    public Integer visitBlank(LabeledExprParser.BlankContext ctx) {
        return super.visitBlank(ctx);
    }

    /**
     *  '('expr ')'
     * @param ctx
     * @return
     */
    @Override
    public Integer visitParens(LabeledExprParser.ParensContext ctx) {
        return visit(ctx.expr()); // 返回 子 表达式 的 值
    }

    /**
     * expr op=('*'|'/') expr
     * @param ctx
     * @return
     */
    @Override
    public Integer visitMulDiv(LabeledExprParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0)); // 计算 左 侧 子表达式 的 值
        int right = visit(ctx.expr(1));// 计算 右 侧 子 表达式 的 值
        if (ctx.op.getType()==LabeledExprParser.MUL){
            return left*right;
        }

        return  left/right;
    }

    /**
     * expr op=('+'|'-') expr
     * @param ctx
     * @return
     */
    @Override
    public Integer visitAddSub(LabeledExprParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));

        if (ctx.op.getType() == LabeledExprParser.ADD) {
            return left+right;
        }
        return left- right; // 不是 加法 ，就是 减法
    }

    @Override
    public Integer visitId(LabeledExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) {
            return memory.get(id);

        }
        return 0;
    }

    @Override
    public Integer visitInt(LabeledExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }
}
