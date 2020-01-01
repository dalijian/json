package com.lijian.algorithms.charpter_2;

import static com.lijian.algorithms.charpter_2.QuickSort.exch;
import static com.lijian.algorithms.charpter_2.QuickSort.sort;


public class Quick3way {

    private static void sort3(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int lt = lo;
        int i=lo+1;
        int gt = hi;

        Comparable v = a[lo];

        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exch(a, lt++, i++);
            } else if (cmp > 0) {
                exch(a, i, gt--);
            }else {
                i++;
            }
        }
        // 现在 a[lo...lt-1] <v = a[lt..gt] <a[gt+1..hi] 成立
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);

    }
}
