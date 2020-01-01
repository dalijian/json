package com.lijian.algorithms.charpter_1;

import java.util.Arrays;

public class ThreeSumFast {
    public static int count(int[] a) {

        Arrays.sort(a);

        int n = a.length;

        int cnt =0;

        for (int i = 0; i < n; i++) {
            // 保证 i,与 j 不相等
            for (int j = i + 1; j < n; j++) {

                if(TwoSumFast.binarySearchRank(-a[i] - a[j], a) > j){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = {-9, -8, 1, 3, 4};

        count(a);


    }
}
