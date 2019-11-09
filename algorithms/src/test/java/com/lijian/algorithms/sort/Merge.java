package com.lijian.algorithms.sort;

import org.junit.Test;



public class Merge {
    @Test
    public void test(){

        int[] a = {1, 4, 6, 2, 9, 3};
        int n = a.length;

        merge_sort_c(a, 0, n - 1);

    }

    private void merge_sort_c(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = (p + r) / 2;

        merge_sort_c(a, p, q);
        merge_sort_c(a, q + 1, r);

//        merge(a[p,q],a[q+1,r])

    }

    public void merge(int[] a, int b[], int c[]) {

    }
}
