package com.lijian.algorithms.match;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class KMP {


    // a, b分别是主串和模式串；n, m分别是主串和模式串的长度。

    // KMP 是 从 前 往 后 比较，  所以 外层 循环 要 有 n 次
    public static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; ++i) {
            while (j > 0 && a[i] != b[j]) { // 一直找到a[i]和b[j]
                j = next[j - 1] + 1;
            }
            if (a[i] == b[j]) {
                ++j;
            }
            if (j == m) { // 找到匹配模式串的了
                return i - m + 1;
            }
        }
        return -1;
    }


    // b表示模式串，m表示模式串的长度
    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; ++i) {
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }
            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }




    // 在 text 中寻找 pattern，返回所有匹配的位置开头
    List<Integer> search(String text, String pattern) {
        List<Integer> positions = new ArrayList<>();
        int[] maxMatchLengths = calculateMaxMatchLengths(pattern);
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            while (count > 0 && pattern.charAt(count) != text.charAt(i)) {
                count = maxMatchLengths[count - 1];
            }
            if (pattern.charAt(count) == text.charAt(i)) {
                count++;
            }
            if (count == pattern.length()) {
                positions.add(i - pattern.length() + 1);
                count = maxMatchLengths[count - 1];
            }
        }
        return positions;
    }

    // 构造 pattern 的最大匹配数表
    int[] calculateMaxMatchLengths(String pattern) {
        int[] maxMatchLengths = new int[pattern.length()];
        int maxLength = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (maxLength > 0 && pattern.charAt(maxLength) != pattern.charAt(i)) {
                maxLength = maxMatchLengths[maxLength - 1]; // ①
            }
            if (pattern.charAt(i) == pattern.charAt(maxLength)) {
                maxLength++; // ②
            }
            maxMatchLengths[i] = maxLength;
        }
        return maxMatchLengths;
    }
    @Test
    public void testCalculateMaxMatchLengths(){
        String str = "aabbabac";
        int[] result = calculateMaxMatchLengths(str);

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);

        }
    }


    //根据对称算法 预处理 模式串 在 i 位置不匹配 时，应后移 位数

    /***
     *
     * @return 数组 下标 表示 模式串 在 下标 i 位置 不匹配， value  为 应 后移 位数
     */
    public int[]  duicheng(char[] cStr){
        int[] result = new int[cStr.length];
        result[0]=1;

        if (cStr[0] == cStr[1]) {
            result[1]=1;
        }else{
            result[2]=2;
        }
        for (int i = 0; i < cStr.length; i++) {

        }
        return null;
    }

    @Test
    public void nextTest(){
        String str = "aabbabac";
        int[] result = next(str.toCharArray());

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);

        }
    }

    public int[] next(char[] cStr) {
        int[] lengthArray = new int[cStr.length];
//        初始化 前缀 与 后缀 的 公共 最长 子 缀 长度 为 0   , 当 i 为 0时
        int lengthMax =0;
        for (int i = 1; i < cStr.length; i++) {

          while (lengthMax>0&&cStr[i] != cStr[lengthMax]) {
                // 寻找 次长 子缀 ,并且 循环 判断,使用  lengthMax = lengthArray[i-1]  会造成 死循环 ， 在 while 循环 中 i 是 不变的值

                lengthMax = lengthArray[lengthMax-1];
            }
                // 当  当前值 与  之前  最大 前后缀 公共 子缀长度 +1 的下标 在 cStr 中 的 值 相等
                // 则 当前 最大 前后缀 的 公共子缀 长度 +1
            if (cStr[i] == cStr[lengthMax]) {
              lengthMax++;
            }
            //往 数组 赋值
            lengthArray[i]=lengthMax;
        }
        return lengthArray;
    }
}
