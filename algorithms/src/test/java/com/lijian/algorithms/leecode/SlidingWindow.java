package com.lijian.algorithms.leecode;

import org.junit.Test;

public class SlidingWindow {


//    初始，leftleft指针和rightright指针都指向SS的第一个元素.
////
////    将 rightright 指针右移，扩张窗口，直到得到一个可行窗口，亦即包含TT的全部字母的窗口。
////
////    得到可行的窗口后，将lefttleftt指针逐个右移，若得到的窗口依然可行，则更新最小窗口大小。
////
////    若窗口不再可行，则跳转至 2。


    @Test
    public void minWindowTest(){

        String result = minWindow(
                "ADOBECODEBANC", "ABC");
        System.out.println(result);
    }
    public String minWindow(String s, String t) {

        if (t.length() > s.length()) {
            return "";
        }
        int min = s.length();
        int i = 0, j = 0;
        int k = 0, v = 0;
        while (i < s.length() - t.length()&&j<s.length()) {

            while (!containsArray(s.substring(i, j), t) && j <=s.length()) {
                j++;
            }
            while (containsArray(s.substring(i, j),t)) {

                if (min > j - i) {
                    min = j - i;
                    k = j;
                    v = i;
                }
                i++;
            }
        }


        return s.substring(v,k+1);


    }

    public boolean containsArray(String str, String str2) {

        for (int i = 0; i < str2.length(); i++) {
            if (!str.contains(str2.substring(i, i+1))) {

                return false;
            }

        }
        return true;
    }


    @Test
    public void test(){
        String str = "li";
        System.out.println(str.substring(0, 3));
    }

}
