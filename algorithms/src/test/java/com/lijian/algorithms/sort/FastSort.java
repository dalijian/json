package com.lijian.algorithms.sort;

import org.junit.Test;

//快排
public class FastSort {

    @Test
    public void test(){
        int[] array = new int[]{1, 3, 5, 4, 8, 3};
        fast(array, 0, array.length - 1);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

    }

    public  void  fast(int[] array, int left,  int right) {

        if (left >= right) {
            return;
        }
        int q = division(array, left, right);
        fast(array,left,q);
        fast(array,q+1,right);
    }

    private int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i =left;

//        对 array 排序

        for (int j = 0; j < right - 1; j++) {
            if (array[i] < pivot) {
                int temp = array[j];
                array[j] = array[i];
                array[i]=temp;
            }
        }
//        int temp = array[right];
//        array[right] = array[i];
//        array[i]= temp;
        return i;
    }

    public int division(int[] list, int left, int right) {
        // 以最左边的数(left)为基准
        int base = list[left];
        while (left < right) {
            // 从序列右端开始，向左遍历，直到找到小于base的数
            while (left < right && list[right] >= base)
                right--;
            // 找到了比base小的元素，将这个元素放到最左边的位置
            list[left] = list[right];
//            由于 base = list[left], 所以 交换 list [left] 与 list[right]  ,list [left]  的 值 不会 丢失
            // 从序列左端开始，向右遍历，直到找到大于base的数
            while (left < right && list[left] <= base)
                left++;
            // 找到了比base大的元素，将这个元素放到最右边的位置
            list[right] = list[left];
        }

        // 最后将base放到left位置。此时，left位置的左侧数值应该都比left小；
        // 而left位置的右侧数值应该都比left大。
        list[left] = base;
        return left;
    }
}
