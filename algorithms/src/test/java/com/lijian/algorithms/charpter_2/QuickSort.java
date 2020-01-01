package com.lijian.algorithms.charpter_2;

import org.junit.Test;

import static com.lijian.algorithms.charpter_2.MergeSort.less;

public class QuickSort {

    public static void sort(Comparable[] comparables) {
        sort(comparables, 0, comparables.length - 1);
    }

    public static void sort(Comparable[] comparables, int lo, int hi) {

        if (hi <= lo) {
            return;
        }
        int j = partition(comparables, lo, hi);
        sort(comparables, lo, j - 1);
        sort(comparables, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {

        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];

        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public  static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;

    }
}
