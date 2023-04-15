package com.lijian.antlr.listeners.explain;

import com.lijian.antlr.listeners.explain.auto.LExprBaseListener;
import com.lijian.antlr.listeners.explain.auto.LExprBaseVisitor;
import com.lijian.antlr.listeners.explain.auto.LExprParser;

import java.util.Stack;

public class TestEvalVisitor {

    public static void main(String[] args) {

    }

    public static class EvalVistor extends LExprBaseVisitor<Integer>{
        @Override
        public Integer visitMult(LExprParser.MultContext context) {
            return visit(context.e(0)) * visit(context.e(1));
        }

        @Override
        public Integer visitAdd(LExprParser.AddContext context) {
            return visit(context.e(0)) + visit(context.e(1));        }

        @Override
        public Integer visitInt(LExprParser.IntContext ctx) {
            return Integer.valueOf(ctx.INT().getText());
        }
    }


    public static  class Evaluator extends LExprBaseListener{
        Stack<Integer> stack = new Stack<>();

        @Override
        public void exitMult(LExprParser.MultContext ctx) {
            int right = stack.pop();
            int left = stack.pop();
            stack.push(left * right);

        }

        @Override
        public void exitAdd(LExprParser.AddContext ctx) {
            int right = stack.pop();
            int left = stack.pop();
            stack.push(left + right);
        }

        @Override
        public void exitInt(LExprParser.IntContext ctx) {
            stack.push(Integer.valueOf(ctx.INT().getText()));
        }
    }
}
