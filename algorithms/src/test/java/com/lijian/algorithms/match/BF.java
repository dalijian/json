package com.lijian.algorithms.match;

import org.junit.Test;

//
public class BF {


    @Test
    public void match() {
        String str = "alsd";
        String subStr = "sd";

        int index = index(str.toCharArray(), subStr.toCharArray());

        System.out.println(index);
    }

    /***
     *
     * @param pStr 主串
     * @param cStr 字串
     * @return 字串在 主串中的 位置， 找不到 返回 -1
     */

    public int index(char[] pStr, char[] cStr) {
        for (int i = 0; i < pStr.length - cStr.length+1; i++) {
            int j = 0;
            while (j < cStr.length) {
                if (pStr[i + j] == cStr[j]) {
                    j++;
                }else{
                    break;
                }
            }
            if (j == cStr.length ) {
                return i;
            }


        }
        return -1;
    }




}
