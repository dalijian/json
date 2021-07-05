package com.lijian.algorithms.leecode;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

/**
 *  计算器
 */
public class Calculator {

    public Double  evaluateExpr(Stack<Object> stack) {

        Double res = 0D;

        if (!stack.empty()) {
            res = Double.valueOf((String) stack.pop());
        }

        // Evaluate the expression till we get corresponding ')'
        while (!stack.empty() && !((char) stack.peek() == ')')) {

            char sign = (char) stack.pop();

            if (sign == '+') {
                res = new  BigDecimal( (String)stack.pop()).add(new BigDecimal(res)).doubleValue();
            } else if  (sign == '*') {
                res = new  BigDecimal( (String)stack.pop()).multiply(new BigDecimal(res)).doubleValue();
            } else if (sign == '/') {
                res = new  BigDecimal( res).divide(new BigDecimal((String) stack.pop()),2,RoundingMode.HALF_UP).doubleValue();
            } else if (sign == '-') {
                res = new  BigDecimal( res).subtract(new BigDecimal((String) stack.pop())).doubleValue();
            }
        }
        return res;
    }

    public Double calculate(String s) {

        String operand = "";
        int n = 0;
        Stack<Object> stack = new Stack<Object>();

        for (int i = s.length() - 1; i >= 0; i--) {

            char ch = s.charAt(i);

            if (Character.isDigit(ch)||'.'==ch) {

                // Forming the operand - in reverse order.
                operand +=ch;
                n += 1;

            } else if (ch != ' ') {
                if (n != 0) {

                    // Save the operand on the stack
                    // As we encounter some non-digit.

                    stack.push(new StringBuffer(operand).reverse().toString() );
                    n = 0;
                    operand = "";

                }
                if (ch == '(') {

                    Double res = evaluateExpr(stack);
                    stack.pop();

                    // Append the evaluated result to the stack.
                    // This result could be of a sub-expression within the parenthesis.
                    stack.push(String.valueOf(res));

                } else {
                    // For other non-digits just push onto the stack.
                    stack.push(ch);
                }
            }
        }

        //Push the last operand to stack, if any.
        if (n != 0) {
            stack.push(new StringBuffer(operand).reverse().toString() );
        }

        // Evaluate any left overs in the stack.
        return evaluateExpr(stack);
    }
    // 计算器
    public static void main(String[] args) {
        Calculator solution = new Calculator();
        Double result = solution.calculate("((((1+1)*3)*2)*2)/0.52-1+1");
        System.out.println(result);
    }
}
