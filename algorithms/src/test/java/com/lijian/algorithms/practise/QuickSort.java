package com.lijian.algorithms.practise;

import org.junit.Test;

public class QuickSort {

    @Test
    public void test() {


        int[] array = {5, 4, 7, 6, 8, 1, 9, 2};

        sort(array, 0, array.length - 1);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    private void sort(int[] array, int left, int right) {

        if (left < right) {

            int partition = getPartition(array, left, right);
            sort(array, left, partition);
            sort(array, partition + 1, right);
        }

    }


    private int getPartition(int[] array, int left, int right) {
//        1. 以 左侧 为 基准，
        int base = array[left];
        while (left < right) {


//        2.  从 右向 左遍历  小于 base 的 值 放 左边

            while (left < right && array[right] < base) {
                right--;
            }
            array[left] = array[right];

//        3. 从 左 向 右 遍历   大于  base 的 值 放 右边
            while (left < right && array[left] > base) {
                left++;
            }
            array[right] = array[left];

//        4. 这样 一 左 一 右 从而 实现 了 原地 排序

        }
        array[left] = base;

        return left;
    }
}
