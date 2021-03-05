package com.lijian.algorithms.leecode;

import org.junit.Test;

import java.util.Arrays;

/***
 *  排序
 */
public class Sorted {
    @Test
    public void mergeListTest() {
        int[] array = new int[]{2, 3};
        int left = 0;
        int right = array.length - 1;

        mergeList(array, left, right);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 归并 排序
     *
     * @param array
     * @param left
     * @param right
     */
    private void mergeList(int[] array, int left, int right) {
        //   子 递归 结果 不能 改变 当前 变量 值， 所以  while (left ,right) 中 left,right  是 不变的 ，不能 使用 while ，应该 使用 if(left,right)
        //  递归 中 循环 条件  不能 使用  非对象 引用 ， 因为 非对象 引用 是 不随 递归 发生 变化 的 ，所以 会 一直 处于  循环 中 无法 跳出 循环
//        while (left < right) {
        if (left < right) {

            int middle = (left + right) / 2;
            mergeList(array, left, middle);
            mergeList(array, middle + 1, right);
            merge(array, left, middle, right);
        }

    }

    // 数组 array  中 在 middle  左边 有序， 在 middle  右遍 有序
    private void merge(int[] array, int left, int middle, int right) {
        int[] tempArray = new int[right - left + 1];
        int i = left, j = middle + 1;
        int u = 0;
        while (i <= middle && j <= right) {

            if (array[i] < array[j]) {
                tempArray[u++] = array[i++];
            }
            if (array[i] > array[j]) {
                tempArray[u++] = array[j++];
            }
        }
        while (i <= middle) {
            tempArray[u++] = array[i++];
        }
        while (j <= right) {
            tempArray[u++] = array[j++];
        }
        u = 0;
        System.arraycopy(tempArray, 0, array, left, right - left + 1);
    }

    /**
     * 快排
     */
    @Test
    public void quicklySortTest() {

        int[] array = new int[]{2, 3, 1, 4, 7, 0, 5};
        int left = 0;
        int right = array.length - 1;

        quickSort(array, left, right);
        System.out.println(Arrays.toString(array));
    }

    public void quickSort(int[] array, int left, int right) {
        if (left < right) {

            int position = division(array, left, right);
            quickSort(array, left, position-1);
            quickSort(array, position + 1, right);

        }
    }

    /**
     * 将 array position 左边  大于  position 右边 的 值 进行 互换
     *
     * @param array
     * @param left
     * @param right
     */
    private int division(int[] array, int left, int right) {
        // 默认 position 为 right
        int base = array[right];

        while (left < right) {
            // 小于   处理
            while (array[left] <= base && left < right) {
                left++;
            }
//            int temp = array[left];
            array[left] = array[right];
            // 大于   处理
            while (array[right] >= base && left<right) {
                right--;
            }
//            // 交换 array[m] 与 array[i]
//            array[left] = array[right];
//            array[right] = temp;
            array[right] = array[left];
        }
        array[left] = base;

        return left;
    }
}
