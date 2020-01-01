package com.lijian.algorithms.match;

import org.junit.Test;

public class BM {
    private static final int SIZE = 256;
    /***
     * 利用散列表 在 模式串中 查找  xi  地址， 数组的下标 对应字符的ASCII码值，数组中存储这个字符在模式串中出现的位置
     * @param b 变量 b 是 模式串
     * @param m m 是模式串的长度
     * @param bc 散列表
     */
    private void generateBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE; i++) {
            bc[i] = -1; // 初始化 bc
        }
        for (int i = 0; i < m; ++i) {
            int ascii = b[i]; // 计算  b[i] 的 ascii 值
            bc[ascii] =i;    // 将 b[
        }
    }

    /***
     *
     * @param a 主串
     * @param n
     * @param b 字串
     * @param m
     * @return
     */
    public int bm(char[] a, int n, char[] b, int m) {
        // 记录模式串中每个字符最后出现的位置
        int[] bc = new int[SIZE];
        // 构建 坏字符哈希表
        generateBC(b, m, bc);
        // i 表示主串 与模式 串 对齐的第一个字符
        int i=0;

        while (i <= n - m) {
            int j; // j  用来标记 模式串的 下标
            for (j = m - 1; j >= 0; --j) {  // 模式串 从后 往前 匹配
                // 模式串 从后 往前 匹配， 不满足 则 跳过
                if (a[i + j] != b[j]) {     // 坏字符对应模式串中的下标 是 j
                    break;
                }
            }
            // j<0  表示 模式串 每个 字符 都 匹配 成功
            if (j < 0) {
                return i;      // 匹配 成功，返回 主串与模式串第一个匹配的字符的位置
            }
            //a[i+j] 为
            i = i + (j - bc[a[i + j]]);  // 等同于将模式串往后滑动 j-bc[(int)a[i+j]] 位
        }
        return -1;
    }

    @Test
    public void bmTest(){
        String m = "abbaccabaa";
        String s = "aba";
        bm(m.toCharArray(), m.length(), s.toCharArray(), s.length());
    }


 // 后缀 处理，
    //1. 在 模式串中，查找跟好后缀匹配的另一个子串
    //2. 在好 后缀的 后缀字串中，查找最长的，能跟模式串前缀字串匹配的后缀子串
    /***
     *
     * @param b 模式串
     * @param m 模式串 长度
     * @param suffix 好后缀的 字串  数组  (下标 位 后缀子串 长度， 值为 后缀 字串 在 模式串 中 存在 的 位置，不存在 为 -1)
     * @param prefix 好后缀 字串 能否 匹配 模式串 前缀， (下标 为 后缀字串 长度，值为 后缀 字串 是否是 模式串 起始 位置)
     */
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        //初始化
        for (int i = 0; i < m; ++i) {
            suffix[i] = -1;
            prefix[i] = false;

        }
        //b[0,i]
        for (int i = 0; i < m - 1; ++i) {
            int j=i;
            int k=0;  //公共后缀字串长度
            while (j >= 0 && b[j] == b[m - 1 - k]) { // 与b[0,m-1] 求公共后缀子串
                --j;
                ++k;
                suffix[k]=j+1; //j+1 表示 公共后缀字串在 b[0,i] 中的起始下标

            }

            if (j == -1) {
                prefix[k] =true;  //如果 公共后缀字串也是模式串的前缀字串
            }
        }
    }



    // a,b表示主串和模式串；n，m表示主串和模式串的长度。
//    public int bm(char[] a, int n, char[] b, int m) {
//        int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
//        generateBC(b, m, bc); // 构建坏字符哈希表
//        int[] suffix = new int[m];
//        boolean[] prefix = new boolean[m];
//        generateGS(b, m, suffix, prefix);
//        int i = 0; // j表示主串与模式串匹配的第一个字符
//        while (i <= n - m) {
//            int j;
//            for (j = m - 1; j >= 0; --j) { // 模式串从后往前匹配
//                if (a[i+j] != b[j]) break; // 坏字符对应模式串中的下标是j
//            }
//            if (j < 0) {
//                return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
//            }
//            int x = j - bc[(int)a[i+j]];
//            int y = 0;
//            if (j < m-1) { // 如果有好后缀的话
//                y = moveByGS(j, m, suffix, prefix);
//            }
//            i = i + Math.max(x, y);
//        }
//        return -1;
//    }

    // j表示坏字符对应的模式串中的字符下标; m表示模式串长度
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        int k = m - 1 - j; // 好后缀长度
        if (suffix[k] != -1) return j - suffix[k] +1;
        for (int r = j+2; r <= m-1; ++r) {
            if (prefix[m-r] == true) {
                return r;
            }
        }
        return m;
    }


    @Test
    public void badCharacter(){
        String str = "abacbcdhijk";
        String subStr = "abad";

        int index = badCharacterRule(str.toCharArray(), subStr.toCharArray());

        System.out.println( index);
    }

    /***
     *  BM  坏字符 处理
     * @param pStr   主 字符串
     * @param cStr    模式串
     */
    public int badCharacterRule(char[] pStr, char[] cStr) {
        // m 是 模式串 的 长度 -1
        int m = cStr.length-1;
        // 预处理  模式串
        // 记录模式串中每个字符最后出现的位置 ， bc  下标 是 cstr 字符 , 存储的是 cstr 字符 所占 下标
        int[] bc = new int[SIZE];
        // 构建 坏字符哈希表
        generateBC(cStr, m, bc);
        //用来 记录 当前 模式串 匹配 到 主串 的 位置
        int h=m;
        while (h < pStr.length) {
            //模式串逆序 计较
            if (pStr[h] == cStr[m]) {
                //  暴力 匹配
                int j=0;
                while (j < cStr.length) {
                    if (pStr[h-j] == cStr[m-j]) {
                        j++;
                    }else{
                        break;
                    }
                }
                if (j == cStr.length) {
                    return h-m;
                }else{
                    //   后移 m
                    h+= m;
                }
            } else {
                // 确定 向后 移动 位数
                //1. 在 模式串 中 查找 pstr[h] 是否 存在 ， 不存在 则 直接 后移 m 位
                //2. 存在 则  找到 cstr 等于 pstr[h] 的 下标 k, 向后 移动 k 为， 循环 判断

                if (bc[pStr[h]] == -1) {
                    h+=m;
                }else{
                    h += m-bc[pStr[h]];
            }
        }
    }
        return -1;
    }


//        BM 好前缀 匹配
//    @Test
//    public void betterSuffixTest(){
//
//        String str = "alsdkfjaiodhganfaephtasdgha";
//        String subStr = "cha";
//
//        int index = betterSuffixRule(str.toCharArray(), subStr.toCharArray());
//
//        System.out.println( index);
//    }

//    private int betterSuffixRule(char[] pStr, char[] cStr) {
//
//        int m= pStr.length -1;
//
//        // 生成 suffix 数组  初始 化
//
//        //初始化
//        int[] suffix = new int[m];
//        boolean[] prefix = new boolean[m];
//        generateGS(cStr, m, suffix, prefix);
//
//        //用来 记录 当前 模式串 匹配 到 主串 的 位置
//        int h =m;
//        while (h < pStr.length) {
//            //模式串逆序 计较
//            if (pStr[h] == cStr[m]) {
//                //  暴力 匹配
//                int j=0;
//                while (j < cStr.length) {
//                    if (pStr[h-j] == cStr[m-j]) {
//                        j++;
//                    }else{
//                        break;
//                    }
//                }
//                if (j == cStr.length) {
//                    return h-m;
//                }else{
//                    //   现在 找到的 与 模式串 匹配 的 最大 字串 长度 为 j
//                    if (suffix[j] == -1) {
//                        h+=m;
//                    }
//                    // 在 模式串 的 字串 中 找到了 好后缀 的 子串 并且 好后缀的 子串  匹配 模式串 的 前缀
//                    if (suffix[j] != -1 && prefix[j]) {
//                        h += suffix[j];
//                    }
//
//                    h+= m;
//                }
//            } else {
//                // 确定 向后 移动 位数
//                //1. 找到 与 模式串 匹配的 最大字串 长度
//                if (bc[pStr[h]] == -1) {
//                    h+=m;
//                }else{
//                    h += m-bc[pStr[h]];
//                }
//            }
//        }
//        return -1;
//
//        }
//    }
}
