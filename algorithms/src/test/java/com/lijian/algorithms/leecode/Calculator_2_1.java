package com.lijian.algorithms.leecode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

// 计算器 支持 负数 和 小数

public    class Calculator_2_1 {
    public static void main(String[] args) {
        Calculator_2_1 calculator_2 =new Calculator_2_1();
//        Double result = calculator_2.calculate("");
//         Double result = calculator_2.calculate("(-1.1+1)");
//         Double result = calculator_2.calculate("1-(2-3.22222)*3");
        System.out.println(calculator_2.calculate("0.234-0.111-0.2+0.34"));
    }
       Map<Character, Integer> map = new HashMap(){{
           put('-', 1);
           put('+', 1);
           put('*', 2);
           put('/', 2);
           put('%', 2);
           put('^', 3);
       }};
       public Double calculate(String s) {
           s = s.replaceAll(" ", "");
           s = s.replaceAll("\\(-", "(0-");
           s = s.replaceAll("\\(\\+", "(0+");
           char[] cs = s.toCharArray();
           int n = s.length();
           Deque<Double> nums = new ArrayDeque<>();
           nums.addLast(0D);
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
                       Double  u = 0D;
                       int j = i;
                       while ((j < n && isNumber(cs[j]))||(j<n && '.'==cs[j])){
                           u = u * 10 + (cs[j++] - '0');
                       }
                    u=   Double.valueOf(new String(Arrays.copyOfRange(cs, i, j)));
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
       void calc(Deque<Double> nums, Deque<Character> ops) {
           if (nums.isEmpty() || nums.size() < 2) return;
           if (ops.isEmpty()) return;
           Double b = nums.pollLast(), a = nums.pollLast();
           char op = ops.pollLast();
           Double ans = 0D;
           if (op == '+') {
               ans = a + b;
           } else if (op == '-') {
               ans = a - b;
           } else if (op == '*') {
               ans = a * b;
           } else if (op == '/') {
//               ans = a / b;
               ans = new BigDecimal( a).divide(new BigDecimal(b),2,RoundingMode.HALF_UP).doubleValue();
           }
           nums.addLast(ans);
       }
       boolean isNumber(char c) {
           return Character.isDigit(c);
       }
   }

