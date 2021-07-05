package com.lijian.algorithms.charpter_1;


import com.lijian.algorithms.stack.StackArray;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Evaluate {

    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();

        Stack<Double> vals = new Stack<>();


        String content = "((1+2)*3)";
        Queue<String> queue = Stream.of(content.split("")).collect(Collectors.toCollection(ArrayDeque::new));



        String value =null;
        while ((value =queue.poll())!=null) {

            // 读取 字符， 如果是 运算符  压入 栈

           String  s=value;

            if (s.equals("(")) {

            } else if (s.equals("+")) {
                ops.push(s);

            } else if (s.equals("-")) {
                ops.push(s);
            } else if (s.equals("*")) {
                ops.push(s);
            } else if (s.equals("/")) {
                ops.push(s);
            } else if (s.equals("sqrt")) {
                ops.push(s);
            } else if (s.equals(")")) {


                //  弹出 运算符，和 操作数，  计算 结果 并 压入 栈
                String op = ops.pop();

                double v= vals.pop();

                if (op.equals("+")) {
                    v = vals.pop() + v;
                } else if (op.equals("-")) {
                    v = vals.pop() -v;
                } else if (op.equals("*")) {
                    v = vals.pop()*v;
                } else if (op.equals("/")) {
                    v = vals.pop()/v;
                } else if (op.equals("sqrt")) {
                    v = Math.sqrt(v);

                }
                vals.push(v);
                }
                else{
                //  如果 字符 既不是 运算符，也不是 括号， 将 它 作为 double  值 压入 栈
                vals.push(Double.parseDouble(s));
            }

        }
        System.out.println(vals.pop());
        }
}
