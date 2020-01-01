package com.lijian.algorithms.charpter_2;

public class MergeSort {

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i=lo;
        int j=mid+1;
        Comparable[] aux = new Comparable[a.length];

        for (int k = lo; k < hi; k++) {
            aux[k] = a[k];

        }
        for (int k = 0; k < hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            }else{
                a[k] = aux[i++];

            }
        }
    }

    public static boolean less(Comparable aux, Comparable aux1) {

        if (aux.compareTo(aux1) > 0) {
            return false;
        }
        return true;
    }

    // 自上而下 的 并归 排序
    private static Comparable [] aux;
    public static void sort(Comparable[] comparables) {
        aux = new Comparable[comparables.length];
        mergeSort(comparables, 0, aux.length - 1);
    }

    private static void mergeSort(Comparable[] comparables, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo+(hi-lo)/2;

        mergeSort(comparables, lo, mid);
        mergeSort(comparables, mid + 1, hi);

        merge(comparables, lo, mid, hi);
    }


}
