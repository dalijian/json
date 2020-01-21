package com.lijian.algorithms.leecode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.lijian.algorithms.leecode.StackExample.isValid;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        // force 双重for循环
//            算法 复杂度 O(n2)
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }

        }
        return null;


    }

    public int[] twoSum2(int[] nums, int target) {
        int[] indexs = new int[2];

        // 建立k-v ，一一对应的哈希表
        HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; i++){
            if(hash.containsKey(nums[i])){
                indexs[0] = i;
                indexs[1] = hash.get(nums[i]);
                return indexs;
            }
            // 将数据存入 key为补数 ，value为下标
            hash.put(target-nums[i],i);
        }
        // // 双重循环 循环极限为(n^2-n)/2
        // for(int i = 0; i < nums.length; i++){
        //     for(int j = nums.length - 1; j > i; j --){
        //         if(nums[i]+nums[j] == target){
        //            indexs[0] = i;
        //            indexs[1] = j;
        //            return indexs;
        //         }
        //     }
        // }
        return indexs;
    }

    @Test
    public void test() {

        int[] result = twoSum2(new int[]{3, 2, 4}, 6);

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    @Test
    public void linked() {

        ListNode listNode1 = new ListNode(9);
        ListNode listNode2 = new ListNode(9);
//        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
//        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(9);
//        ListNode listNode5 = new ListNode(6);
//        ListNode listNode6 = new ListNode(4);
//        listNode4.next = listNode5;
//        listNode5.next = listNode6;
        ListNode result = addTwoNumbers2(listNode1, listNode4);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
    // 合并链表
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
////        看来 不能 创建 新 listNode , 只能 将  计算 好的 值 赋值 在 旧 listNode 上
//        ListNode head = new ListNode(0);
//        while (l1 != null || l2 != null) {
//            int i = 1;
//            int n1Val = l1 == null ? 0 : l1.val;
//            int n2Val = l2 == null ? 0 : l2.val;
//            int value = n1Val + n2Val;
//            if (value > 9) {
//                head.val = head.val + 1;
//                value = value - 10;
//            }
//            ListNode listNode = new ListNode(value);
//            ListNode next;
//            for (int j = 0; j < i; j++) {
//                next = head.next;
//            }
//            if (listNode.next == null) {
//                head.next = listNode;
//            }
//
//            l1.val = value;
//            l1 = l1.next;
//            l2 = l2.next;
//        }
//        return l1;
//    }

    // 合并链表
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        if (l1 == null) return l2;
//        if (l2 == null) return l1;
        if (l1 == null && l2 == null) {
            return l1;
        }
        if (l1 != null && l2 != null) {
            l1.val = l1.val + l2.val;
        }
        if (l1.next == null) {
            l1.next = new ListNode(0);
        }
        if (l2.next == null) {
            l2.next = new ListNode(0);
        }

        ListNode head = null;


        head = l1;
        if (head.val > 9) {
            head.val = head.val - 10;
            if (l1.next == null) {
                l1.next = new ListNode(1);
            } else {
                l1.next.val = head.next.val + 1;
            }
        }
        head.next = addTwoNumbers(l1.next, l2.next);
//        if (head.next!=null&&head.next.val > 9) {
//            head.val=head.val+1;
//            head.next.val=head.next.val-10;
//        }
        return head;
    }

    // 使用 循环 合并 链表
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;

            ListNode sumNode = new ListNode(sumVal % 10);
            cursor.next = sumNode;
            cursor = sumNode;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return root.next;
    }


    public int lengthOfLongestSubstring(String s) {

        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {


                String current = s.substring(j, j);

                String subStr = s.substring(i, j);
                if (j != 0) {
                    current = s.substring(j - 1, j);

                    subStr = s.substring(i, j - 1);

                }


                if (subStr.contains(current)) {
                    break;
                }

                if (maxLength < j - i) {
                    maxLength = j - i;
                }


//                if (Stream.of(subStr.split("")).distinct().count() == j - i) {
//                    if (maxLength < j - i) {
//                        maxLength = j - i;
//                    }
//
//                }
            }
        }

        return maxLength;
    }

    @Test
    public void lengthMaxTest() {
        System.out.println(lengthOfLongestSubstring("pwwkew"));

        System.out.println(hashCode());

        System.out.println();

    }

    @Test
    public void myAtoiTest() {

        int num = myAtoi("+1");
        System.out.println(num);
    }

    public int myAtoi(String str) {
        char[] strlist = str.toCharArray();
        if (str.length() == 0) {
            return 0;
        }
        int j = 0, i = 0, f = 0,z=0;
        while (i < strlist.length) {

            if (strlist[i] == ' ') {
                i++;
                continue;
            }
            if (i<strlist.length&&strlist[i] == '-' && f <= 1) {
                i++;
                f++;
            }
            if (i<strlist.length&&strlist[i] == '+' && z <= 1) {
                i++;
                z++;
            }

            if (i<strlist.length&&strlist[i] >= '0' && strlist[i] <= '9') {
                j = 0;
                while (i + j < str.length() && strlist[i + j] >= '0' && strlist[i + j] <= '9') {
                    j++;
                }
//                i=i+j;
                String cStr = str.substring(i, i+j);
                Long num = Long.valueOf(cStr);
                if (f == 0) {
                    if (num.longValue() > Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }else{
                        return num.intValue();
                    }

                }else{
                    if (-num.longValue() < Integer.MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    }
                    return -num.intValue();
                }


            }
            if (i<strlist.length&&(strlist[i] <= '0' || strlist[i] >= '9')) {
                return 0;
            }

        }
        if (j == 0) {
            return 0;
        }
        String cStr = str.substring(i-j-1, i-1);
        Long num = Long.valueOf(cStr);
        return num.intValue();
    }

    @Test
    public void testMedian() {

        double result = findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
        System.out.println(result);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

//        1. 先将 两个 有序 数组 合并
//                2. 在 查找 中位数
        int[] array = new int[nums1.length + nums2.length];

        System.arraycopy(nums1, 0, array, 0, nums1.length);
        System.arraycopy(nums2, 0, array, nums1.length, nums2.length);

        int[] temp = new int[array.length];
        merge(array, 0, nums1.length - 1, array.length - 1, temp);

        if (array.length % 2 == 0) {
            int middle1 = array[array.length / 2];
            int middle2 = array[array.length / 2 - 1];

            return (new Double(middle1) + middle2) / 2;
        }
        return array[array.length / 2];

    }

    private void merge(int[] array, int left, int middle, int right, int[] temp) {

//        1. array 现在分为 两个 有序 数组 ，从 left 到 middle, 和 从 middle 到 right

        int i = left;
        int j = middle + 1;
        int g = 0;
        while (i <= middle && j <= right) {
            if (array[i] <= array[j]) {
                temp[g++] = array[i++];
            } else {
                temp[g++] = array[j++];
            }
        }
        while (i <= middle) {
            temp[g++] = array[i++];
        }
        while (j <= right) {
            temp[g++] = array[j++];
        }
        g = 0;
        System.arraycopy(temp, 0, array, left, right - left + 1);

    }

    @Test
    public void longestTest() {
        System.out.println(longestPalindrome("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc"));
    }


    //    找出 最长 回文子段
    public String longestPalindrome(String s) {
        char[] array = s.toCharArray();
        String max = "";
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if (isPalindrome(s, i, j)) {
                    String temp = s.substring(i, j + 1);
                    if (max.length() < temp.length()) {
                        max = temp;
                    }
                }
            }
        }
        return max;
    }

    // 对称字符串 str[i] = str[str.length-i]
    public boolean isPalindrome(String s, int k, int j) {
        char[] array = s.substring(k, j + 1).toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != array[array.length - i - 1]) {
                return false;
            }
        }
        return true;
    }


    @Test
    public void longestPalindrome2Test() {

        String result = longestPalindrome2("bananas");

        System.out.println(result);
    }


    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
//         保存起始位置，测试了用数组似乎能比全局变量稍快一点
        int[] range = new int[2];
        char[] str = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
//             把回文看成中间的部分全是同一字符，左右部分相对称
//             找到下一个与当前字符不同的字符
            i = findLongest2(str, i, range);
        }
        // 使用 range[0],range[1] 存放 回文数的 起始下标和终止下标
        return s.substring(range[0], range[1] + 1);
    }

    public static int findLongest2(char[] str, int low, int[] range) {
//         查找中间部分
        int high = low;
        while (high < str.length - 1 && str[high + 1] == str[low]) {
            high++;
        }
//         定位中间部分的最后一个字符
        int ans = high;
//         从中间向左右扩散
        while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
            low--;
            high++;
        }
//         记录最大长度
        if (high - low > range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }
        // 这边 返回 ans 为什么 只由 定位 中间 字段 确定， 那 从 中间 向左右 扩展 的  str 匹配 的 下标 变动 怎么 没有 记录 ？？ 为什么 不返回 high
        return ans;
//        return high;
    }


    @Test
    public void reverseTest() {

        System.out.println(Math.abs(-1563847412));


        System.out.println(reverse2(-7412));
    }

    public int reverse(int x) {
//        1. 反转 整数， 首尾 交换   ， 利用 数组

//            1. 判断 x 的 位数
        String str = String.valueOf(x);
        boolean negative = str.startsWith("-");
        if (negative) {
            str = str.substring(str.lastIndexOf("-") + 1);
        }
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length / 2; i++) {
//            首尾交换
            char temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        if (negative) {
            return (int) -Double.valueOf(new String((array))).longValue();
        }
        return (int) Double.valueOf(new String(array)).longValue();

    }

    // 这里 使用 stack 用点 浪费
    public int reverse2(int t) {
        int x = t;

        Long number = 0L;
        while (x != 0) {
            number = number * 10 + x % 10;
            x = x / 10;
        }

        if (number > new Long(Integer.MAX_VALUE) || number < new Long(Integer.MIN_VALUE)) {
            return 0;
        }
        return number.intValue();


    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String str = String.valueOf(x);
        char[] array = str.toCharArray();
        for (int i = 0; i < str.length() / 2; i++) {
            if (array[i] != array[array.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void matchTest() {
        System.out.println(isMatch2("ab", ".*c"));
    }

    public boolean isMatch(String s, String p) {
        if (s == null) {
            return false;
        }


        //    "aab"
//    "c*a*b"
//    不好判断

//        2. s 不为空
        char[] chars = s.toCharArray();
        char[] regex = p.toCharArray();
        int j = 0;
        for (int i = 0; i < regex.length; i++) {
            while (j < chars.length) {
                if (regex[i] != '*' && regex[i] != '.') {
                    if (chars[j] != regex[i]) return false;
                    else j++;
                    break;
                } else {
                    return true;
                }
            }
        }
        if (j == chars.length) {
            return true;
        }
        return false;
    }


    @Test
    public void isMatch2Test() {


        boolean result = isMatch2("aa", "aaa");
        System.out.println(result);
    }

    // 状态转移矩阵甩起来
    public boolean isMatch2(String s, String p) {
        if (s == null) {
            return false;
        }
        int[] array = new int[s.length()]; //  状态转义 数组
        char[] str = s.toCharArray();
        char[] regex = p.toCharArray();

        int pointer = 0;  // 指向的 是  当前  验证 str 数组  下标
        char value = str[pointer];
        for (int i = 0; i < regex.length && pointer < regex.length; i++) {
            if (regex[i] == '.') {
                value = str[pointer];
                array[pointer++] = 1;
                continue;
            }
            if (regex[i] == '*' && pointer == 0) {
                continue;
            }
            if (regex[i] == '*' && array[pointer - 1] == 0) {
                continue;
            }
            if (regex[i] == '*' && pointer == str.length - 1 && pointer == regex.length - 1) {
                value = str[pointer];
                array[pointer++] = 1;
                continue;
            }

            if (regex[i] == str[pointer]) {
                value = str[pointer];
                array[pointer++] = 1;
                continue;
            }

            while (regex[i] == '*' && array[pointer - 1] == 1 && pointer < array.length && value == str[pointer]) {
                value = str[pointer];
                array[pointer++] = 1;

            }


            if (pointer == str.length) {
                break;
            }

        }
        if (array[str.length - 1] == 1) {
            return true;
        }
        return false;

    }


    public boolean isMatch3(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] memory = new boolean[sLen + 1][pLen + 1];
        memory[0][0] = true;
        for (int i = 0; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (p.charAt(j - 1) == '*') {
//                    memory[i][j] = memory[i][j-2] || (i > 0 && (s.charAt(i-1) == p.charAt(j-2) ||
//                            p.charAt(j-2) == '.') && memory[i-1][j]);
                    if (memory[i][j - 2] || (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) ||
                            p.charAt(j - 2) == '.') && memory[i - 1][j])) {

                        memory[i][j] = true;
                    } else {
                        memory[i][j] = false;
                    }
                } else {
//                    memory[i][j] = i > 0 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')
//                            && memory[i-1][j-1];
                    if (i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
                            && memory[i - 1][j - 1]) {
                        memory[i][j] = true;
                    } else {
                        memory[i][j] = false;
                    }
                }
            }
        }
        return memory[sLen][pLen];
    }


    public boolean isMatch4(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] memory = new boolean[2][pLen + 1];
        memory[0][0] = true;
        int cur = 0, pre = 0;
        for (int i = 0; i <= sLen; i++) {
            cur = i % 2;
            pre = (i + 1) % 2;
            if (i > 1) {
                for (int j = 0; j <= pLen; j++) {
                    memory[cur][j] = false;
                }
            }
            for (int j = 1; j <= pLen; j++) {
                if (p.charAt(j - 1) == '*') {

                    memory[cur][j] = memory[cur][j - 2] || (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) ||
                            p.charAt(j - 2) == '.') && memory[pre][j]);
                } else {
                    memory[cur][j] = i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
                            && memory[pre][j - 1];
                }
            }
        }
        return memory[cur][pLen];
    }


    @Test
    public void maxAreaTest() {
        System.out.println(maxArea(1, 8, 6, 2, 5, 4, 8, 3, 7));
    }

    public int maxArea(int... height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                if (max < (j - i) * (Math.min(height[i], height[j]))) {
                    max = (j - i) * (Math.min(height[i], height[j]));
                }
            }
        }
        return max;
    }

    @Test
    public void intToRomanTest() {

        String result = intToRoman(2000);

        System.out.println(result);
    }

    public String intToRoman(int num) {
        String roman = "";
        int[] array = {0, 1, 5, 10, 50, 100, 500, 1000};
//        1. 创建映射
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");

        map.put(4, "IV");
        map.put(9, "IX");

        map.put(40, "XL");

        map.put(90, "XC");

        map.put(400, "CD");

        map.put(900, "CM");

        Stack<Integer> stack = new Stack<>();
        while (num != 0) {
            int n = num % 10;
            stack.push(n);
            num = num / 10;
        }
        while (!stack.isEmpty()) {
            int result = (int) (Math.pow(10, stack.size() - 1) * stack.pop());

            // 拿到 每一位
            if (map.containsKey(result)) {
                String value = map.get(result);
                roman += value;
            } else {
                // 拿到 這個 數字的 前 一個 羅馬 字符
                int maxLess = 0;
                while ((maxLess = getMaxLess(result, array)) != 0) {
                    roman += map.get(maxLess);
                    result = result - maxLess;
                }
            }


        }


        return roman;

    }

    private int getMaxLess(int result, int[] array) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] > result) {
                return array[i - 1];
            }
        }
        if (result >= array[array.length - 1]) {
            return array[array.length - 1];
        }
        return 0;
    }

    // 拿到 一个 数 的 每一位 stack
    @Test
    public void testNumber() {
        int num = 10;
        Stack<Integer> stack = new Stack<>();
        while (num != 0) {
            int n = num % 10;
            stack.push(n);
            num = num / 10;
        }
        while (!stack.isEmpty()) {
            System.out.println(Math.pow(10, stack.size() - 1) * stack.pop());
        }

    }

    @Test
    public void romanToIntTest() {

        int result = romanToInt("IV");

        //罗马 字符 如何 拆分
        System.out.println(result);

    }

    private int romanToInt(String sdafa) {
        int value = 0;
        int values[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String reps[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

//        使用双重循环 很慢 怎么办
        while (sdafa.length() != 0) {
            for (int i = 0; i < reps.length; i++) {
                if (sdafa.startsWith(reps[i])) {
                    value += values[i];
                    sdafa = sdafa.replaceFirst(reps[i], "");
                    break;
                }

            }
        }

        return value;
    }

    @Test
    public void testLongestCommonPrefix() {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));

    }


    public String longestCommonPrefix(String[] strs) {

        String minLength = strs[0];

        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minLength.length()) {
                minLength = strs[i];
            }
        }

        String subStr = "";

        for (int i = 0; i < minLength.length(); i++) {
            for (int j = i + 1; j < minLength.length(); j++) {

                for (int k = 0; k < strs.length; k++) {
                    if (!strs[k].contains(minLength.substring(i, j)) && subStr.length() < (j - i)) {
                        break;

                    }
                    subStr = minLength.substring((i), j);

                }
            }
        }
        return subStr;
    }


    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        char[] charArray = strs[0].toCharArray();
        if (charArray.length == 0) {
            return "";
        }
        String prefix = "";
        // 这个 双重循环 有点 费
        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].length() > i) {
                    if (strs[j].toCharArray()[i] != charArray[i]) {
                        return prefix;
                    }
                } else {
                    return prefix;
                }
            }
            prefix += charArray[i];
        }

        return prefix;
    }


    @Test
    public void threeSumTest() {

        List<List<Integer>> result = threeSum(new int[]{-1,0,1,2,-1,-4});
        System.out.println(result);
    }
    //三数之和 为0；
    // 先使用 根据 0 分组 ， 从 小于 等于 0 中 查找 出 两个 数， C(n,2)  再 在 大于 0 的 组中 查找 是否 有 正数 等于 小于 等于 0 组 的 两数 之 和


    // fix one use tow_sum(target-one)

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        Map<Integer,Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                continue;
            }else{
                map.put(nums[i], i);
            }
            list.addAll(twoSum3(nums, nums[i], i));
        }
        return list;
    }

    public List<List<Integer>> twoSum3(int[] nums, int target, int index) {
        List<List<Integer>> totalList = new ArrayList<>();
        Map<Integer,Integer> map =new LinkedHashMap<>();
        for (int i = index+1; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                List<Integer> result = Arrays.asList(nums[i],nums[ map.get(nums[i])], target);
                while (i+1<nums.length&&nums[i] == nums[ i+1]) i++;
                totalList.add(result);
            }else {
                map.put(-target - nums[i], i);
            }
        }
        return totalList;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums); // 排序 数组
        List<List<Integer>> ls = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {  // 跳过可能重复的答案
                    // 固定 第一个 值
                int l = i + 1, r = nums.length - 1, sum = 0 - nums[i];
                while (l < r) {
                    if (nums[l] + nums[r] == sum) {
                        ls.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) l++; // 跳过 (0，0，0，0，0)
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        l++;
                        r--;
                    } else if (nums[l] + nums[r] < sum) {
                        while (l < r && nums[l] == nums[l + 1]) l++;   // 跳过重复值
                        l++;
                    } else {
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        r--;
                    }
                }
            }
        }
        return ls;
    }

    // 拿出三個數 之和 与 target 最接近
    //  有三次 放入机会，
    //       a1+a2+a3  与 target  接近
//          求 min(Math.abs(a1+a2+a3-target))


    @Test
    public void threeSumClosestTest(){

        int result = threeSumClosest(new int[]{-1, 2, 1, -4}, 1);
        System.out.println(result);
    }
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int closest = target - nums[0] - nums[1] - nums[2];
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1;
            int a = target - nums[i];
            int r = nums.length-1;

            while (l < r) {
                if (nums[l] + nums[r] == a) {
                    closest= nums[i] + nums[l] + nums[r];
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (nums[l] + nums[r] < a) {
                    if (Math.abs(closest) < Math.abs(a - nums[l] - nums[r] - nums[i])) {
                        closest=a - nums[l] - nums[r] - nums[i];
                    }
                    while (l < r && nums[l] == nums[l + 1]) l++;   // 跳过重复值
                    l++;
                } else {
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    r--;
                }

            }
        }

        return closest;
    }


    public Stack<Integer> stack = new Stack<Integer>();

    @Test
    public void fTest() {
        int shu[] = {1, 2, 3, 4, 5};
        f2(shu, 2, 0);
        System.out.println("count:" + count);
    }

    /**
     * @param shu   待选择的数组
     * @param targ  要选择多少个次
     * @param cur   当前选择的是第几次
     */
    private static int count = 0;

    private void f(int[] shu, int targ, int cur) {
        // TODO Auto-generated method stub
        count++;
        if (cur == targ) {
            System.out.println(stack);
            return;
        }
        for (int i = 0; i < shu.length; i++) {
            stack.add(shu[i]);
            f(shu, targ, cur + 1);
            stack.pop();
            // 通过 stack 大小 可以 看出 ,每次 调用 for 执行时， 都会将 当前 位置 的 值 删除，流出 位置 给 for 的 新值 插入

            System.out.println(stack.size());
        }
    }

    /**
     * 排列
     * 剔除 重复值
     *
     * @param shu  待选择的数组
     * @param targ 要选择多少个次
     * @param cur  当前选择的是第几次
     */
    private void f2(int[] shu, int targ, int cur) {
        // TODO Auto-generated method stub
        if (cur == targ) {
            System.out.println(stack);
            return;
        }
        for (int i = 0; i < shu.length; i++) {
            if (!stack.contains(shu[i])) {
                stack.add(shu[i]);
                f2(shu, targ, cur + 1);
                stack.pop();
            }
        }
    }

    @Test
    public void f3Test() {
        int shu[] = {1, 2, 3, 4, 5};
        f3(shu, 2, 0, 0);
        System.out.println("count:" + count);
    }

    /**
     * 组合 不考虑 顺序 比如在 3个数中选择 2个数,组合方法有C（3,2）=3种,是12、13、23
     *
     * @param shu  元素
     * @param targ 要选多少个元素
     * @param has  当前有多少个元素
     * @param cur  当前选到的下标
     *             <p>
     *             1    2   3     //开始下标到2
     *             1    2   4     //然后从3开始
     */
    private void f3(int[] shu, int targ, int has, int cur) {
        if (has == targ) {
            System.out.println(stack);
            return;
        }
        for (int i = cur; i < shu.length; i++) {
            if (!stack.contains(shu[i])) {
                stack.add(shu[i]);
                //通过 i 使得  递归 时 stack 放入 元素 下标 是递减 的 所以  导致 不会 出现 下标 大 的 排到 下标 小 的 前面 ，
                // 例如 只会 出现 (1,2) 不会 出现  (2,1)
                f3(shu, targ, has + 1, i);
                stack.pop();
                System.out.println("stack'size:" + stack.size());
            }
        }

    }


    // 分治 二分法
//     x=x*x; 会 随着 x 的 增大  x, 并不是 原始的 x
//    public double myPow(double x, int n) {
//        double y = x;
//        while (n != 0) {
//
//            if (n % 2 == 0) {
//                x=x*x;
//                n=n/2;
//            }else {
//                x=x*y;
//            }
//        }
//        if (n < 0) {
//            return 1/x;
//        }
//        return x;
//    }

    @Test
    public void myPowTest() {

        double result = myPow(2, 7);

        System.out.println(result);
    }

    public double myPow(double x, int n) {
        double res = 1.0;

        for (int i = n; i != 0; i /= 2) {
            if (i % 2 != 0) {
                res *= x;
            }
            x *= x;
        }
        return n < 0 ? 1 / res : res;
    }

    @Test
    public void mypowReTest() {
        double result = mypoww(0.00001, 2147483647
        );
        System.out.println(result);
    }

    public double mypoww(double x, int n) {
        if (n < 0) {
            return 1 / mypowre(x, -n);
        }
        return mypowre(x, n);
    }

    //f(2,3) =f(2,1.5)*f(2,1.5) 而 3/2 取整 1， f(2,3)=f(2,1)*f(2,1)  丢失了 一个 x, 所以 判断 n 是不是 偶数
//        递归 处理， 例如 f(2,5)=f(2,2)*f(2,2) 由于 f(2,2) 调用 两次 会 补充 两个 x,就 多了个 x
    public double mypowre(double x, int n) {
        double res = 1.0;
        if (n % 2 == 1) {
//            n 为 奇数
            res *= x;
        }
        if (n == 0) {
            return 1;
        }
        double temp = res * mypowre(x, n / 2) * mypowre(x, n / 2);
        return temp;
    }

    public int power(int x, int n) { // 1.
        int y; // 2.
        if (n == 0) // 3. 递归终止条件
            y = 1; // 4.
        else { // 5.
            y = power(x, n / 2); // 6. 递归调用
            y = y * y; // 7.
            if (n % 2 == 1) y = y * x; // 8.
        } // 9.
        return y; //10.
    }


    @Test
    public void testCanJump() {
        System.out.println(canJump(new int[]{
                3, 2, 1, 0, 4}));
    }

    // nums 的 value  表示 最大  可 跳跃 的 最 大 长度，
    //  每次选择 与 前一次选择 相关 ，  用动态规划 写
    // num[i] +i 表示 当前 位置 能 到达的 最远 距离 ，
    // 之前 使用 +  法 计算 总距离， 没有 考虑到 位置  选择 的 关联 性， 使用 max 比较 ，可以 拿到 最大 距离
    public boolean canJump(int[] nums) {

        int current = 0;
        int maxDis = nums[0];


        for (int i = 0; i < nums.length - 1; i++) {
            maxDis = Math.max(maxDis, nums[i] + i);

        }
        return maxDis >= nums.length - 1;
    }
//    贪心算法，每个位置都计算自己能达到的最远距离，同时每个位置要判断自己是否可达，也就是本位置需要在当前最远能到达的距离中。
//    最终计算出来能到达的最远距离，与数组长度比较即可。

//    bool canJump(vector<int>& nums) {
//        int len = nums.size();
//        if (len <= 1) return true;
//
//        int maxDis = nums[0];
//
//        for (int i = 1; i < len - 1; i++) {
//            if (i <= maxDis) {
//                maxDis = max(maxDis, nums[i]+i);
//            }
//        }
//
//        return maxDis >= len - 1;
//    }

    @Test
    public void canCompleteCircuitTest() {

        int arrive = canCompleteCircuit(new int[]{4}, new int[]{4});
        System.out.println(arrive);
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {

        int beginPos = 0;

        while (beginPos < gas.length) {
            int maxGas = 0;
            int maxCost = 0;
            if (gas[beginPos] >= cost[beginPos]) {
                int gasCount = 0;
                while (maxGas >= maxCost) {
                    maxCost = beginPos + gasCount >= gas.length ? maxCost + cost[beginPos + gasCount - gas.length] : maxCost + cost[beginPos + gasCount];
                    maxGas = beginPos + gasCount >= gas.length ? maxGas + gas[beginPos + gasCount - gas.length] : maxGas + gas[beginPos + gasCount];
                    gasCount++;
                    if (gasCount >= gas.length && maxGas >= maxCost) {
                        return beginPos;
                    }
                }
            }
            beginPos++;
        }

        return -1;
    }

    @Test
    public void findKthLargestTest() {
        findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);


    }

    public int findKthLargest(int[] nums, int k) {

        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, temp);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
        return nums[nums.length - k];
    }

    private void mergeSort(int[] nums, int left, int right, int[] temp) {

        if (left < right) {
            int middle = (left + right) / 2;

            mergeSort(nums, left, middle, temp);
            mergeSort(nums, middle + 1, right, temp);
            position(nums, left, middle, right, temp);


        }

    }


    private void position(int[] nums, int left, int middle, int right, int[] temp) {

        int i = left;
        int j = middle + 1;
        int k = 0;
        while (i <= middle && j <= right) {

            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }

        }
        while (i <= middle) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }
        k = 0;
        System.arraycopy(temp, 0, nums, left, right - left + 1);

    }

    @Test
    public void calculateTest() {
        calculate("(1+1+1)");

    }

    public void calculate(String ss) {


        Stack<String> ops = new Stack<String>();

        Stack<Double> vals = new Stack<>();

        List<String> list = Stream.of(ss.split("")).collect(Collectors.toList());


        for (int i = 0; i < list.size(); i++) {

            // 读取 字符， 如果是 运算符  压入 栈

            String s = list.get(i);

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
                double v = vals.pop();
                if (op.equals("+")) {
                    v = vals.pop() + v;
                } else if (op.equals("-")) {
                    v = vals.pop() - v;
                } else if (op.equals("*")) {
                    v = vals.pop() * v;
                } else if (op.equals("/")) {
                    v = vals.pop() / v;
                } else if (op.equals("sqrt")) {
                    v = Math.sqrt(v);

                }
                vals.push(v);
            } else {
                //  如果 字符 既不是 运算符，也不是 括号， 将 它 作为 double  值 压入 栈
                vals.push(Double.parseDouble(s));


            }

        }
        System.out.println(vals.pop());
    }

    @Test
    public void mergeKListsTest() {

        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(4);
        listNode.next.next = new ListNode(5);

        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(3);
        listNode1.next.next = new ListNode(4);

        ListNode listNode2 = new ListNode(2);
        listNode2.next = new ListNode(6);

        ListNode listNode3 = new ListNode(6);
        listNode3.next = new ListNode(7);

        listNode3.next = new ListNode(9);
        mergeKLists(new ListNode[]{listNode, listNode1, listNode2}, 0, 2);
    }

    //    递归 关系式  merge(list,left,right) = mergeSort(merge(list,left,middle),merge(list,middle+1,right))
    public ListNode mergeKLists(ListNode[] lists, int left, int right) {
        if (lists.length == 0)
            return null;
        if (right == left)
            return lists[left];
        if (lists.length == 2) {
            return mergeTwoLists(lists[0], lists[1]);
        }
        if (right - left == 1) {
            return mergeTwoLists(lists[left], lists[right]);
        }
        int mid = (left + right) / 2;
        return mergeTwoLists(mergeKLists(lists, left, mid), mergeKLists(lists, mid + 1, right));
    }

    // 将 有序 链表 合并
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = null;
        if (l1.val <= l2.val) {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }

    @Test
    public void reverseListTest() {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        ListNode result = reverse(node);
//        ListNode result2 = reverseList(node);
//        System.out.println(result);

    }

    // 反转 链表
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode temp = head.next;
        ListNode newHead = reverse(head.next);
        temp.next = head;
        head.next = null;
        return newHead;
    }

    @Test
    public void listTest() {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        ListNode reslt = list(node);
        System.out.println(reslt);
    }

    public ListNode list(ListNode node) {
        if (node.next == null) {
            return node;
        }
        ListNode temp = node;  // 保存当前值
        ListNode head = list(node.next);
        head.next = temp;
        temp.next = null;
        return head;
    }


    public ListNode reverseList(ListNode node) {
        ListNode pre = null;
        ListNode next = null;
        while (node != null) {
            next = node.next;
            node.next = pre;  // 第一个 node  拿到 当前值 ，  将 之前的  node 设置 成 现在 的 next 值，
            pre = node;        // 第一 次   pre   等于 当前值，                                    ，将
            node = next;
        }
        return pre;
    }

    //    使用循环 代替 递归
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {

        Queue<ListNode> queue = new java.util.LinkedList<>();
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                ((java.util.LinkedList<ListNode>) queue).add(l1);
                l1 = l1.next;
            } else {
                ((java.util.LinkedList<ListNode>) queue).add(l2);
                l2 = l2.next;
            }
        }
        while (l1 != null) {
            ((java.util.LinkedList<ListNode>) queue).add(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            ((java.util.LinkedList<ListNode>) queue).add(l2);
            l2 = l2.next;
        }
        for (int i = 0; i < queue.size() - 1; i++) {

            ((java.util.LinkedList<ListNode>) queue).get(i).next = ((java.util.LinkedList<ListNode>) queue).get(i + 1);
        }
        return ((java.util.LinkedList<ListNode>) queue).get(0);
    }


//    public ListNode mergeKLists(ListNode[] lists){
//        if(lists.length == 0)
//            return null;
//        if(lists.length == 1)
//            return lists[0];
//        if(lists.length == 2){
//            return mergeTwoLists(lists[0],lists[1]);
//        }
//
//        int mid = lists.length/2;
//        ListNode[] l1 = new ListNode[mid];
//        for(int i = 0; i < mid; i++){
//            l1[i] = lists[i];
//        }
//
//        ListNode[] l2 = new ListNode[lists.length-mid];
//        for(int i = mid,j=0; i < lists.length; i++,j++){
//            l2[j] = lists[i];
//        }
//
//        return mergeTwoLists(mergeKLists(l1),mergeKLists(l2));
//
//    }
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        if (l1 == null) return l2;
//        if (l2 == null) return l1;
//        ListNode head = null;
//        if (l1.val <= l2.val){
//            head = l1;
//            head.next = mergeTwoLists(l1.next, l2);
//        } else {
//            head = l2;
//            head.next = mergeTwoLists(l1, l2.next);
//        }
//        return head;
//    }


    // 计算 棋子 移动 距离
    // min_dist(i,j) = w[i][j] + minD(min_dist(i,j-1),min_dist(i-1,j))
    // 有了 递归 关系式，剩下的 就是 寻找 递归 终止 条件

    private int[][] mem2 = new int[4][4];

    @Test
    public void min_dist_Test() {

        int[][] w = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};

        int result = min_dist(3, 3, w);
        System.out.println(result);
    }

    // 使用 备忘录 private int[][] mem2 = new int[4][4];
    public int min_dist(int i, int j, int[][] w) {
        if (mem2[i][j] > 0) {
            return mem2[i][j];
        }
        if (i == 0 && j == 0) {
            return w[i][j];
        }
        if (i == 0) {
            return w[i][j] + min_dist(i, j - 1, w);
        }
        if (j == 0) {
            return w[i][j] + min_dist(i - 1, j, w);
        }

        int dis = w[i][j] + minD(min_dist(i, j - 1, w), min_dist(i - 1, j, w));
        mem2[i][j] = dis;
        return dis;
    }

//    public int min_dist(int i, int j, int[][] w) {

//        if (i == 0 && j == 0) {
//            return w[i][j];
//        }
//        if (i == 0) {
//            return w[i][j] + min_dist(i, j - 1, w);
//        }
//        if (j == 0) {
//            return w[i][j] + min_dist(i - 1, j, w);
//        }
//
//        return w[i][j] + minD(min_dist(i, j - 1,w), min_dist(i - 1, j,w));
//    }

    private int minD(int i, int j) {

        return Math.min(i, j);
    }


    @Test
    public void codingTest() {
        coding(new int[]{0, 0}, 1);
    }

    public void coding(int[] b, int n) {
        if (n == 0) {
            b[n] = 0;
            outBn(b);
            b[n] = 1;
            outBn(b);
        } else {
            b[n] = 0;
            coding(b, n - 1);
            b[n] = 1;
            coding(b, n - 1);
        }
    }

    private void outBn(int[] b) {
        for (int i = 0; i < b.length; i++)
            System.out.print(b[i]);
        System.out.println();
    }

    @Test
    public void removeNthFromEndTest() {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
//        head.next=head2;
//        head.next.next=head3;
//        head.next.next.next=head4;
//        head.next.next.next.next=head5;
//        拿到 ListNode  长度

        removeNthFromEnd(head, 1);
    }

    public ListNode removeNthFromEnd(ListNode head, int len) {
        ListNode temp = head;
        int length = 0;         // 这里 为什么 temp 不随着 head 改变 ？？
        while (head != null) {
            length++;
            head = head.next;
        }
        return removeNthFromEndRe(temp, len, 0, length);
    }


    public ListNode removeNthFromEndRe(ListNode head, int n, int i, int length) {

        if (length == n) {
            return head.next;
        }
        if (head == null) {
            return null;
        }
        ListNode node = null;
        node = head;
        i++;
        if (length - i == n) {
            node.next = removeNthFromEndRe(head.next.next, n, i, length);
            return node;
        }
        node.next = removeNthFromEndRe(head.next, n, i, length);
        //为什么 return node, 与 return head  ，head 与 node 相等
        return node;
    }


    // 生成 有效 括号
    //  回溯 算法 甩起来
//    [
//            "((()))",
//            "(()())",
//            "(())()",
//            "()(())",
//            "()()()"
//            ]

    @Test
    public void generateParenthesisTest() {

        List<String> result = generateParenthesis(4);
        System.out.println(result);
    }

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        char[] array = {'(', ')'};
        generateParenthesisRe(new char[2 * n], 0, array, list, n);
        return list.stream().filter(x -> isValid(x)).collect(Collectors.toList());

    }

    //递归 + 循环 就是合 从 集合 m 中，抽出 1 个，并 抽出 n次， 将 他们 组合 共有 多少 情况
    // 递归 适合 将 大问题 分解 成 小问题 ， 而 添加 的  循环 就是 为 解决 中间 问题
    // 例如  抽取 n 次 就是 大问题， 分解 成 小问题 抽一次， 一次 有 m 种 抽发 就是 中间 问题
    public void generateParenthesisRe(char[] str, int i, char[] array, List<String> list, int n) {

        if (i == 2 * n) {
            list.add(new String(str));
            return;
        }
        for (int j = 0; j < 2; j++) {
            if (checkParenth(str, array[j])) {
                // 要对 str 经行 修改 ，而不是 一直 添加 ，所以 使用 str 就会 不方便 改用 char []
                str[i] = array[j];
                generateParenthesisRe(str, i + 1, array, list, n);
            }
        }
    }

    private boolean checkParenth(char[] str, char j) {
        return true;
    }

    @Test
    public void generateTest() {
        List<String> list = new ArrayList<>();

        generate(list, "", 0, 0, 2);
        System.out.println(list);
    }


    public void generate(List<String> res, String ans, int count1, int count2, int n) {

        if (count1 > n || count2 > n) return;

        if (count1 == n && count2 == n) res.add(ans);

        // count1>= count2  确保在递归过程 种 不会 出现  ) 个数 不会  大于 ( 个数
        if (count1 >= count2) {
            String ans1 = new String(ans);
            generate(res, ans + "(", count1 + 1, count2, n);
            // 注意 这两个 递归 引用 的 ans 不同，一个 是 ans, 一个 是 ans1
//          第一个 generate 执行 完                 再执行 第二个 generate
//            由于 ans1 是 ans的 一个 复制,但是 由于 第二个 generate 落后 第一个 generate  ，所以 当 运行到 第二个 generate 时 ans1  与 ans
//            是 少一部 generate 的
            generate(res, ans1 + ")", count1, count2 + 1, n);
        }
    }

    // 交换链表
//    1，2，3，4 -> 2,1,4,3

    @Test
    public void swapPairsTest(){
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        swapPairs(node);
    }

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;  // 交换 第一个 元素 和 第二个 元素
        return next;
    }

    // 实现 indexOf(String)
//    haystack = "hello", needle = "eo"
    // 先 预处理 haystack , 生成 辅助 数组， array 下标 为 字符的 ascII 值，value 为 字符 所在 haystack 的 下标 值
//        但是 碰到 相同 元素 怎么 办 例如 aa, array[97]=0,array[97]=1, array 一个 下标 没法 存 两个 值

    @Test
    public void strStrTest() {

        System.out.println(strStr2("mississippi", "issip"));

    }

    public int strStr(String haystack, String needle) {

        char[] hchars = haystack.toCharArray();
        char[] nchars = needle.toCharArray();
        int j = 0;
        int i = 0;
        if (hchars.length == 0 && nchars.length == 0) {
            return 0;
        }
        if (hchars.length == 0 && nchars.length != 0) {
            return -1;
        }
        if (nchars.length == 0) {
            return 0;
        }


        while (true) {
            if (j < hchars.length && hchars[j] != nchars[i]) {
                j++;
                if (j == hchars.length) {
                    return -1;
                }
                continue;
            }
//            这种 写发 mississippi   模式串 不匹配 会 跳过 整个模式串的长度 ， 这样 就会 错过 合适 的 字符
//                      issip
//                           issip
            while (j < hchars.length && i < nchars.length && hchars[j] == nchars[i]) {
                j++;
                i++;
                if (i == nchars.length) {
                    return j - i;
                }
            }
            i = 0;

        }
    }

    @Test
    public void strStr2Test() {

        System.out.println(strStr2("mississippi", "pi"));
    }

    public int strStr2(String haystack, String needle) {

        if (haystack.length() == 0 && needle.length() == 0) {
            return 0;
        }
        if (haystack.length() == needle.length()) {
            if (haystack.equals(needle)) {
                return 0;
            }
            return -1;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }
        return BF(haystack.toCharArray(), needle.toCharArray());
    }

    public int BF(char[] pStr, char[] cStr) {
        for (int i = 0; i < pStr.length - cStr.length; i++) {
            int j = 0;
            while (j < cStr.length) {
                if (pStr[i + j] == cStr[j]) {
                    j++;
                } else {
                    break;
                }
            }
            if (j == cStr.length) {
                return i;
            }
        }
        return -1;
    }
//                1,1,2
//                1,2

//    0,0,1,1,1,2,2,3,3,4
//    0,1,1,1,2,2,3,3,4,4
//    0,1,2,2,3,3,4,4,4,4
//    0,1,2,3,3,4,4,4,4,4
//    0,1,2,3,4,4,4,4,4,4

    @Test
    public void removeDuplicatesTest() {

        int result = removeDuplicates2(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
//        int result = removeDuplicates2(new int[]{0,0,0,1});
        System.out.println(result);
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int begin = nums[0];
        int i = 0;
        int t = 0;
        while (i < nums.length - t - 1) {
            int c = 0;
            while (i + c < nums.length && nums[i + c] == begin) {
                c++;
            }
            // 下面 表示 nums[i]!= begin;

//            for (int j = i; j < nums.length-c+1; j++) {
//
//                // 动态 拿到 回退 步数 c
//                nums[j] = nums[j+c-1];
//            }
            int j = i;
            while (j < nums.length) {
                if (j < nums.length - c + 1) {
                    nums[j] = nums[j + c - 1];
                    j++;
                } else {
                    nums[j] = nums[nums.length - 1];
                    j++;
                }
            }

            t = t + c - 1; // 表示 总 回退 步数
            i++;
            begin = nums[i];

        }

        int k = 1;
        for (int j = 0; j < nums.length - 1; j++) {
            if (nums[j] != nums[j + 1]) {
                k++;
            }
        }

        return k;
    }


    public int removeDuplicates2(int[] nums) {
        // 使用双指针
        if (nums == null || nums.length == 1) {
            return nums.length;
        }
        // 快慢指针
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[i] == nums[j]) {
                j++;
            } else {
                i++;
                nums[i] = nums[j];
                j++;
            }
        }
        return i + 1;
    }

    @Test
    public void removeElementTest() {
        int result = removeElement(new int[]{2, 3, 3}, 3);
//        [0,1,4,0,3]
        System.out.println(result);
    }

    public int removeElement(int[] nums, int val) {

        // 使用双指针
        if (nums.length == 1 && nums[0] == val) {
            return 0;
        }
        if (nums == null || nums.length == 1) {
            return nums.length;
        }
        int i = 0, j = nums.length - 1;
        int t = 0;
        while (i < j) {
            while (i < nums.length && nums[i] != val) {
                i++;
                if (i == nums.length) return nums.length;
            }
            if (nums[j] == val) {
                t++;
            }
            while (nums[i] == val && nums[j] != val) {
                t++;
                nums[i] = nums[j];
                i++;
                j--;
            }
            if (i != nums.length - 1 && i == j && nums[i] == val) {
                t++;
            }
            j--;
        }
        if (i == 0) {
            return 0;
        }
        return nums.length - t;
    }

    //两数 相除
    @Test
    public void divideTest() {

        int result = divide(-2147483648, -1);

        System.out.println(result);
    }

    public int divide(int dividend, int divisor) {
        int t = dividend;
        if (dividend == 0) {
            return 0;
        }
        int count = 0;
        if (dividend > 0) {
            while (dividend >= Math.abs(divisor)) {
                dividend = dividend - Math.abs(divisor);
                count++;
            }
        }
        if (dividend < 0) {
            while (dividend <= -Math.abs(divisor)) {
                dividend = dividend + Math.abs(divisor);
                count++;
            }
        }
        if ((t > 0 && divisor > 0) || (t < 0 && divisor < 0)) {
            return count;
        }
        return -count;
    }

    @Test
    public void permuteTest() {
        List<List<Integer>> result = permute(new int[]{1, 2, 3, 4});
        System.out.println(result);
    }

    public List<List<Integer>> permute(int[] nums) {

        if (nums.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> list = new ArrayList<>();
        permuteRe(list, 0, nums, new int[nums.length]);
        return list;
    }

    private void permuteRe(List<List<Integer>> list, int i, int[] nums, int[] tempList) {
        if (i == nums.length) {
            // 好奇 这里 如果 不是 new List()  而是  使用 参数 传递 过来 的 list ， 输出的 list  只有 一个值  ，
            // 而 如果 new LIst()  那么 输出 的 值 包含 全部  排列 类型
            List<Integer> list1 = new ArrayList<>();
            for (int j = 0; j < tempList.length; j++) {
                list1.add(tempList[j]);
            }
            list.add(list1);
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            // 这边 是 指定 位置 nums[i] 不包含   nums[j]
            if (!listContains(i, j, nums, tempList)) {
                // 这里 必须 是 替换 ，而不能 是 新增
                tempList[i] = nums[j];
                permuteRe(list, i + 1, nums, tempList);
            }
        }
    }

    /**
     * 判断 元素 是否 可以 插入
     *
     * @param i        插入 元素 在 tempList 的 下标
     * @param j        插入 元素 在  nums 的 下标
     * @param nums     递归-循环问题 中的 中问题  使用  循环 解决  从 集合 中 选择 一个
     * @param tempList 当前 排列
     * @return
     */
    private boolean listContains(int i, int j, int[] nums, int[] tempList) {
        if (tempList.length == 0) {
            return false;
        }
        // 在 i 之前 的 元素 都不用 判断 是否 重复  ， 在 i 之后 的 元素 要 判断 是否 存在 插入值  即 nums[j]
        for (int k = 0; k < i; k++) {
            if (tempList[k] == nums[j]) {
                return true;
            }
        }
        return false;
    }

    // 从 存在 重复 集合 M 中 抽取  1 一个
    @Test
    public void pernuteUniqueTest() {

        List<List<Integer>> result = permuteUnique(new int[]{1, 1, 2});
        System.out.println(result);
    }


    public List<List<Integer>> permuteUnique(int[] nums) {

        if (nums.length == 0) {
            return Collections.emptyList();
        }
        // 预处理 nums
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        List<List<Integer>> list = new ArrayList<>();
        permuteUniqueRe(list, 0, nums, new int[nums.length], map);
        return list;

    }

    private void permuteUniqueRe(List<List<Integer>> list, int i, int[] nums, int[] tempList, Map<Integer, Integer> map) {
        if (i == nums.length) {
            // 好奇 这里 如果 不是 new List()  而是  使用 参数 传递 过来 的 list ， 输出的 list  只有 一个值  ，
            // 而 如果 new LIst()  那么 输出 的 值 包含 全部  排列 类型
            List<Integer> list1 = new ArrayList<>();
            for (int j = 0; j < tempList.length; j++) {
                list1.add(tempList[j]);
            }
            list.add(list1);
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            // 这边 是 指定 位置 nums[i] 不包含   nums[j]
            if (!listContainsUnique(i, j, nums, tempList, map)) {
                // 这里 必须 是 替换 ，而不能 是 新增
                tempList[i] = nums[j];
                permuteUniqueRe(list, i + 1, nums, tempList, map);
            }
        }
    }


    /**
     * 判断 元素 是否 可以 插入
     *
     * @param i        插入 元素 在 tempList 的 下标
     * @param j        插入 元素 在  nums 的 下标
     * @param nums     递归-循环问题 中的 中问题  使用  循环 解决  从 集合 中 选择 一个
     * @param tempList 当前 排列
     * @param map
     * @return
     */
    private boolean listContainsUnique(int i, int j, int[] nums, int[] tempList, Map<Integer, Integer> map) {

        if (tempList.length == 0) {
            return false;
        }
        int count = 0;

        for (int k = 0; k < i; k++) {
            if (tempList[k] == nums[j]) {
                count++;
            }
            if (tempList[k] == nums[j] && count >= map.get(nums[j])) {

                return true;
            }
            if (count >= map.get(nums[j])) {
                return true;
            }
        }
        return false;
    }


    public ListNode reverseList2(ListNode node) {
        ListNode pre = null;
        ListNode next = null;
        while (node != null) {
            next = node.next;
            node.next = pre;  // 第一个 node  拿到 当前值 ，  将 之前的  node 设置 成 现在 的 next 值，
            pre = node;        // 第一 次   pre   等于 当前值，  为什么 next != pre                                  ，将
            node = next;
        }
        return pre;
    }

    public ListNode reverseList3(ListNode node) {
        ListNode pre = null;
        ListNode next = null;
        while (node != null) {
            next = node.next;  // 相当于 将 node.next 做个 备份 , 注意 node.next 也是 一个 引用  ，所以 next 引用的是 node.next 的 引用地址
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }


    @Test
    public void queen8Test() {
        // 存放 皇后 所在 列的 下标
        int[] queenList = new int[8];

        List<List<String>> list = new ArrayList<>();

        addQueen(0, queenList, list);
        System.out.println(list);

    }

    public void addQueen(int i, int[] queenList, List<List<String>> list) {

        if (i == 8) { // 8个棋子都放置好了，打印结果
            printQueens(queenList, list);

            return; // 8行棋子都放好了，已经没法再往下递归了，所以就return
        }
        for (int j = 0; j < 8; j++) {
            if (checkQueenExist(i, j, queenList)) {
                queenList[i] = j;
                addQueen(i + 1, queenList, list);
            }
        }
    }

    private boolean checkQueenExist(int i, int j, int[] queenList) {
        if (i == 0) {
            return true;
        }
        for (int k = 0; k < i; k++) {   // 默认 i 是 递增的 ，即 行 是按 顺序 排放的
            if (Math.abs(k - i) == Math.abs(queenList[k] - j) ||
                    Math.abs(k - i) == 0 ||
                    Math.abs(queenList[k] - j) == 0) {
                return false;
            }
        }
        return true;
    }

    private void printQueens(int[] result, List<List<String>> list) { // 打印出一个二维矩阵
        List<String> tempList = new ArrayList<>();
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) tempList.add("Q");
                else tempList.add(".");
            }
            tempList.add("\r\n");
        }

        list.add(tempList);

    }

@Test
public void countAndSayTest(){


    String result = countAndSay(4);
    System.out.println(result);
}

    public String countAndSay(int n) {

        if (n == 1) {
            return "1";
        }

        return say(countAndSay(n - 1));
    }

    private String say(String s) {
      char [] chars=  s.toCharArray();
        String result = "";
        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            int j=0;
            while (i+j<chars.length&&chars[i+j] == temp) {

                j++;
            }
            i+=j-1;
            result += j + String.valueOf(chars[i]);
        }
        return result;
    }


}