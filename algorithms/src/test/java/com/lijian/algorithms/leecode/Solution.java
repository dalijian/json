package com.lijian.algorithms.leecode;

import com.lijian.algorithms.link.LinkedList;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    public void test() {

        int[] result = twoSum(new int[]{3, 2, 4}, 6);

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

//        看来 不能 创建 新 listNode , 只能 将  计算 好的 值 赋值 在 旧 listNode 上
        ListNode head = new ListNode(0);
        while (l1 != null || l2 != null) {
            int i = 1;
            int n1Val = l1 == null ? 0 : l1.val;
            int n2Val = l2 == null ? 0 : l2.val;

            int value = n1Val + n2Val;
            if (value > 9) {
                head.val = head.val + 1;
                value = value - 10;
            }
            ListNode listNode = new ListNode(value);
            ListNode next;
            for (int j = 0; j < i; j++) {
                next = head.next;

            }

            if (listNode.next == null) {
                head.next = listNode;
            }

            l1.val = value;
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1;
    }


    @Test
    public void linked() {

        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);


        listNode1.next = listNode2;
        listNode2.next = listNode3;


        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(4);


        listNode4.next = listNode5;
        listNode5.next = listNode6;

        ListNode result = addTwoNumbers(listNode1, listNode4);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }


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
    public void reverseTest() {
        System.out.println(reverse(1534236469));
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


    public boolean isMatch(String s, String p) {
        if (s == null) {
            return false;
        }

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

    //    "aab"
//    "c*a*b"
//    不好判断
    @Test
    public void matchTest() {
        System.out.println(isMatch("aa", "a"));
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




    public String intToRoman(int num) {

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


        char[] chars = String.valueOf(num).toCharArray();

//        for (int i = chars.length - 1; i >= 0; i--) {
//
//           if (map.containsKey( new String(char[i]
//
//        }
//
        return null;

    }

    // 拿到 一个 数 的 每一位
    @Test
    public void testNumber() {
        int num = 1234567;
        Stack<Integer> stack = new Stack<>();
        while (num % 10 != 0) {
            int n = num % 10;
            stack.push(n);
            num = num / 10;
        }
        while (!stack.isEmpty()) {
            System.out.println(Math.pow(10, stack.size() - 1) * stack.pop());
        }

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

    @Test
    public void testLongestCommonPrefix() {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));

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

    public int power (int x, int n) { // 1.
        int y; // 2.
        if (n == 0) // 3. 递归终止条件
            y = 1; // 4.
        else { // 5.
            y = power (x, n/2); // 6. 递归调用
            y = y * y; // 7.
            if (n%2 == 1) y = y * x; // 8.
        } // 9.
        return y; //10.
    }


    public List<List<Integer>> permuteUnique(int[] nums) {


        return null;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list0 = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if (list.contains(nums[j])) {
                    continue;
                }
                list.add(nums[j]);
            }
        }
        return list0;
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
    public void canCompleteCircuitTest(){

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
                    maxGas  = beginPos + gasCount >= gas.length ? maxGas + gas[beginPos + gasCount - gas.length] : maxGas + gas[beginPos + gasCount];
                    gasCount++;
                    if (gasCount >= gas.length&&maxGas>=maxCost) {
                        return  beginPos;
                    }
                }
            }
            beginPos++;
        }

        return -1;
    }

    @Test
    public void findKthLargestTest(){
        findKthLargest(  new int [] { 3,2,1,5,6,4 },2);


    }

    public int findKthLargest(int[] nums, int k) {

        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length-1, temp);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
        return  nums[nums.length-k];
    }

    private void mergeSort(int[] nums, int left, int right, int[] temp) {

        if (left <right) {
            int middle = (left+right)/2;

            mergeSort(nums, left, middle, temp);
            mergeSort(nums, middle+1, right, temp);
            position(nums, left, middle, right, temp);


        }

    }


    private void position(int[] nums, int left, int middle, int right, int[] temp) {

        int i= left;
        int j = middle+1;
        int k =0;
        while (i<=middle&&j<=right) {

            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            }else{
                temp[k++] = nums[j++];
            }

        }
        while (i <= middle) {
            temp[k++]=nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }
        k=0;
        System.arraycopy(temp, 0, nums, left, right - left+1);

    }

    @Test
    public void calculateTest() {
        calculate("(1+1+1)");

    }
    public void   calculate(String ss){


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

@Test
public void mergeKListsTest(){

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
        mergeKLists( new ListNode[]{listNode,listNode1,listNode2},0,2);
}
//    递归 关系式  merge(list,left,right) = mergeSort(merge(list,left,middle),merge(list,middle+1,right))
    public ListNode mergeKLists(ListNode[] lists,int left,int right){
        if(lists.length == 0)
            return null;
        if(right== left)
            return lists[left];
        if(lists.length == 2){
            return mergeTwoLists(lists[0],lists[1]);
        }
        if (right-left==1){
            return mergeTwoLists(lists[left], lists[right]);
        }
        int mid = (left+right)/2;
        return mergeTwoLists(mergeKLists(lists,left,mid),mergeKLists(lists,mid+1,right));
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = null;
        if (l1.val <= l2.val){
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }


//    使用循环 代替 递归
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {

        Queue<ListNode> queue = new java.util.LinkedList<>();
        while (l1!= null && l2!= null) {
            if (l1.val <= l2.val) {
                ((java.util.LinkedList<ListNode>) queue).add(l1);
                l1 = l1.next;
            }else{
                ((java.util.LinkedList<ListNode>) queue).add(l2);
                l2 = l2.next;
            }
        }
        while (l1 != null) {
            ((java.util.LinkedList<ListNode>) queue).add(l1);
            l1 = l1.next;
        }
        while (l2!= null) {
            ((java.util.LinkedList<ListNode>) queue).add(l2);
            l2 = l2.next;
        }
        for (int i = 0; i < queue.size()-1; i++) {

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
    public void min_dist_Test(){

        int[] []w= {{1,3,5,9},{2,1,3,4},{5,2,6,7},{6, 8, 4, 3}};

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
        mem2[i][j]=dis;
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
        coding(new int[]{0,0}, 1);
    }
    public void coding (int[] b, int n) {
        if (n==0) {
            b[n] = 0;
            outBn(b);
            b[n] = 1;
            outBn(b);
        }
        else {
            b[n] = 0;
            coding(b,n-1);
            b[n] = 1;
            coding(b,n-1);
        }
    }
    private void outBn (int[] b) {
        for (int i=0;i<b.length;i++)
            System.out.print(b[i]);
        System.out.println();
    }















@Test
public void longestPalindrome2Test(){

    String result = longestPalindrome2("12345432332213");
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
            i = findLongest(str, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }

    public static int findLongest(char[] str, int low, int[] range) {
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
        return ans;
    }

}