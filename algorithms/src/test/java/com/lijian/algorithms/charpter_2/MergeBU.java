package com.lijian.algorithms.charpter_2;

import static com.lijian.algorithms.charpter_2.MergeSort.merge;

public class MergeBU {
//    1. 自底向上的并归 排序

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        int n = a.length;

        aux = new Comparable[n];

        for (int sz = 1; sz < n; sz=sz+sz) { //sz 子数组 大小
            for (int lo = 0; lo < n - sz; lo += sz + sz) {  // lo:子数组索引
                merge(a, lo, lo + sz - 1, Math.max(lo + sz + sz - 1, n - 1));
            }
        }
    }
}
