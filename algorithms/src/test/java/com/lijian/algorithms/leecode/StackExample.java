package com.lijian.algorithms.leecode;

import org.junit.Test;

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

}
