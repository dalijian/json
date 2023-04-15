//package com.lijian.antlr.listeners.annotate;
//
//import com.lijian.antlr.listeners.explain.auto.LExprBaseListener;
//import com.lijian.antlr.listeners.explain.auto.LExprParser;
//import org.antlr.v4.runtime.tree.ParseTree;
//import org.antlr.v4.runtime.tree.ParseTreeProperty;
//
//
//public class TestLEvaluatorWithProps extends LExprBaseListener {
//    public static void main(String[] args) {
//
//    }
//
//    // 使用 Map<ParseTree,Integer> 将 节点 映射到 对应的 整数值
//    // 通过 ParseTreeProperty 实例 将 局部 计算结果 和LExpr 语法 分析树 节点 关联 起来
//    ParseTreeProperty<Integer> values = new ParseTreeProperty<>();
//
//    @Override
//    public void exitInt(LExprParser.IntContext ctx) {
//        String intText = ctx.INT().getText();
//        setValue(ctx, Integer.valueOf(intText));
//    }
//
//    @Override
//    public void exitAdd(LExprParser.AddContext ctx) {
//        int left = getValue(ctx.e(0));
//        int right = getValue(ctx.e(1));
//        setValue(ctx, left + right);
//    }
//
//    private int getValue(LExprParser.EContext e) {
//        return values.get(e);
//    }
//
//    private void setValue(ParseTree ctx, Integer integer) {
//        values.put(ctx, integer);
//    }
//
//    public void existS(LExprParser.SContext context) {
//        setValue(context, getValue(context.e()));
//    }
//}
