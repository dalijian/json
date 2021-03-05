package com.lijian.algorithms.leecode;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class StackExample {




    // 这个 不适合 用 回文数 判断
    @Test
    public void isValidTest() {
        boolean flag = isValid("()");

        System.out.println(flag);
    }

    public static boolean isValid(String s) {

        char[] chars = s.toCharArray();
        if (chars.length == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '(':
                    stack.push(chars[i]);
                    break;
                case '[':
                    stack.push(chars[i]);
                    break;
                case '{':
                    stack.push(chars[i]);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.peek() != '(') {
                        return false;
                    } else {
                        stack.pop();
                    }
                    break;
                case ']':
                    if (stack.isEmpty() || stack.peek() != '[') {
                        return false;
                    } else {
                        stack.pop();
                    }
                    break;
                case '}':
                    if (stack.isEmpty() || stack.peek() != '{') {
                        return false;
                    } else {
                        stack.pop();
                    }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;

    }

@Test
public void simplifyPathTest(){

    String result = simplifyPath("/home/");
    System.out.println(result);
}
// 路径 转换 就是  处理 特殊 路径  /. /..
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        for (String item : path.split("/")) {
            if (item.equals("..")) {
                // 这里 利用的 stack 的 lifo
                if (!stack.isEmpty()) stack.pop();
            } else if (!item.isEmpty() && !item.equals(".")) stack.push(item);
        }
        String res = "";
        for (String d : stack) res = "/" + d + res;
        return res.isEmpty() ? "/" : res;
    }
//    84. 柱状图中最大的矩形
    public int largestRectangleArea(int[] heights) {
        int max =0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i; j < heights.length; j++) {
//                (j - i) * Math.min(heights[i], heights[j]);


            }

        }

        return 0;
    }
//224. 基本计算器
public int calculate(String s) {
    Stack<Integer> stack = new Stack<Integer>();
    // sign 代表正负
    int sign = 1, res = 0;
    int length = s.length();
    for (int i = 0; i < length; i++) {
        char ch = s.charAt(i);
        if (Character.isDigit(ch)) {
            int cur = ch - '0';
            while (i + 1 < length && Character.isDigit(s.charAt(i + 1)))
                cur = cur * 10 + s.charAt(++i) - '0';
            res = res + sign * cur;
        } else if (ch == '+') {
            sign = 1;
        } else if (ch == '-') {
            sign = -1;
        } else if (ch == '(') {
            stack.push(res);
            res = 0;
            stack.push(sign);
            sign = 1;
        } else if (ch == ')') {
            res = stack.pop() * res + stack.pop();
        }
    }
    return res;
}

}
