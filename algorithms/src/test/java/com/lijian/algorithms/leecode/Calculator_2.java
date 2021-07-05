package com.lijian.algorithms.leecode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

// 计算器 支持 负数
 public    class Calculator_2 {
     public static void main(String[] args) {
         Calculator_2 calculator_2 =new Calculator_2();
         int result = calculator_2.calculate("-1.111+1.22");
//         int result = calculator_2.calculate("(-1.1+1)");
//         int result = calculator_2.calculate("1-(2-3)");
         System.out.println(result);
     }
        Map<Character, Integer> map = new HashMap(){{
            put('-', 1);
            put('+', 1);
            put('*', 2);
            put('/', 2);
            put('%', 2);
            put('^', 3);
        }};
        public int calculate(String s) {
            s = s.replaceAll(" ", "");
            s = s.replaceAll("\\(-", "(0-");
            s = s.replaceAll("\\(\\+", "(0+");
            char[] cs = s.toCharArray();
            int n = s.length();
            Deque<Integer> nums = new ArrayDeque<>();
            nums.addLast(0);
            Deque<Character> ops = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                char c = cs[i];
                if (c == '(') {
                    ops.addLast(c);
                } else if (c == ')') {
                    while (!ops.isEmpty()) {
                        if (ops.peekLast() != '(') {
                            calc(nums, ops);
                        } else {
                            ops.pollLast();
                            break;
                        }
                    }
                } else {
                    if (isNumber(c)) {
                        int u = 0;
                        int j = i;
                        while (j < n && isNumber(cs[j])) u = u * 10 + (cs[j++] - '0');
                        nums.addLast(u);
                        i = j - 1;
                    } else {
                        while (!ops.isEmpty() && ops.peekLast() != '(') {
                            char prev = ops.peekLast();
                            if (map.get(prev) >= map.get(c)) {
                                calc(nums, ops);
                            } else {
                                break;
                            }
                        }
                        ops.addLast(c);
                    }
                }
            }
            while (!ops.isEmpty() && ops.peekLast() != '(') calc(nums, ops);
            return nums.peekLast();
        }
        void calc(Deque<Integer> nums, Deque<Character> ops) {
            if (nums.isEmpty() || nums.size() < 2) return;
            if (ops.isEmpty()) return;
            int b = nums.pollLast(), a = nums.pollLast();
            char op = ops.pollLast();
            int ans = 0;
            if (op == '+') {
                ans = a + b;
            } else if (op == '-') {
                ans = a - b;
            } else if (op == '*') {
                ans = a * b;
            } else if (op == '/') {
                ans = a / b;
            } else if (op == '^') {
                ans = (int)Math.pow(a, b);
            } else if (op == '%') {
                ans = a % b;
            }
            nums.addLast(ans);
        }
        boolean isNumber(char c) {
            return Character.isDigit(c);
        }
    }

