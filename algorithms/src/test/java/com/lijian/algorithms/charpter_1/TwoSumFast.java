package com.lijian.algorithms.charpter_1;

import com.lijian.algorithms.search.BinarySearch;

import java.util.Arrays;

public class TwoSumFast {


    public static int count(int[] a) {

        Arrays.sort(a);

        int n = a.length;

        int cnt = 0;

        for (int i = 0; i < n; i++) {

            if (binarySearchRank(-a[i], a) > i) {
                cnt++;
            }

        }
        return cnt;
    }

    public static int binarySearchRank(int i, int[] a) {

        return bSearch(a, 0, a.length, i);

    }

    //    递归式

    /***
     *
     * @param array 查找集合
     * @param left  左元素
     * @param right 右元素
     * @param i     查找值
     * @return
     */
    private static int bSearch(int[] array, int left, int right, int i) {
        int middle = (left + right) / 2;
        if (i == array[middle]) {
            return middle;
        }
        if (left == right) {
            return -1;
        }
        if (i > array[middle]) {
            return bSearch(array, middle + 1, right, i);
        } else {
            return bSearch(array, left, middle - 1, i);
        }

    }
    public static void main(String[] args) {
        int[] a = {1, 2, 4, 5, 6, 9};

        count(a);

    }
}
