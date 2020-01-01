package com.lijian.algorithms.practise;

import org.junit.Test;

public class MergeSort {


    @Test
    public void test(){

        int[] array = {5, 4, 7, 6, 8, 1, 9, 2};
                array= new int [] { 3,2,1,5,6,4 };
        int[] temp = new int[array.length];
        mergeSort(array, 0, array.length-1, temp);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    private void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {


//        1. 分
            int middle = (left + right) / 2;

            mergeSort(array, left, middle, temp);
            mergeSort(array, middle + 1, right, temp);

//            2. 并
            merge(array, left, middle, right, temp);
        }
    }

    private void merge(int[] array, int left, int middle, int right, int[] temp) {

//        1. array 现在分为 两个 有序 数组 ，从 left 到 middle, 和 从 middle 到 right

        int i = left;
        int j = middle+1;
        int g=0;
        while (i <= middle && j <= right) {
            if (array[i] <= array[j]) {
                temp[g++] = array[i++];
            }else{
                temp[g++]=array[j++];
            }
        }
        while (i <= middle) {
            temp[g++] = array[i++];
        }
        while (j <= right) {
            temp[g++] = array[j++];
        }
        g=0;
        System.arraycopy(temp, 0, array, left, right - left+1);

    }
}
