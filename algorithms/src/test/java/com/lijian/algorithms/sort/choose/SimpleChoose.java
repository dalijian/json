package com.lijian.algorithms.sort.choose;

import com.lijian.algorithms.Strategy;
import org.junit.Test;

import javax.swing.*;

//  简单 选择排序
public class SimpleChoose {

    Strategy strategy;

    @Test
    public void simple() {

        int[] array = {2, 3, 9, 6, 1, 2, 3, 4, 5, 6, 8};

//        sort(array, 0, array.length);

        sortRecur(array, 0, array.length);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    private void sort(int[] array, int i, int length) {
        int end = length;
        while (i < end) {
            int k = i; // k 为 最小值 所在 的 索引，
            //拿到 数组 i 到 end 之间的最小值，和最小值的 下标值
            for (int j = i; j < end; j++) {
                if (array[j] < array[k]) {
                    k = j;
                }
            }
            //循环结束后 ，k 是最小值的 下标值
            if (k != i) {
                int temp = array[k];
                array[k] = array[i];
                array[i] = temp;
            }
            i++;
        }
    }


    private void sortRecur(int[] array, int i, int length) {
        int end = length;
        if (i >= end - 1) {
            return;
        }
        int k = i; // k 为 最小值 所在 的 索引，
        //拿到 数组 i 到 end 之间的最小值，和最小值的 下标值
        for (int j = i; j < end; j++) {
            if (array[j] < array[k]) {
                k = j;
            }
        }
        //循环结束后 ，k 是最小值的 下标值
        if (k != i) {
            int temp = array[k];
            array[k] = array[i];
            array[i] = temp;
        }
        i++;
        sortRecur(array, i, length);
    }


    public void selectSort(Object[] r, int low, int high) {
        for (int k = low; k < high - 1; k++) { //作 n-1 趟选取
            int min = k;
            for (int i = min + 1; i <= high; i++) //选择关键字最小的元素
                if (strategy.compare(r[i], r[min]) < 0) min = i;
            if (k != min) {
                Object temp = r[k]; //关键字最小的元素与元素 r[k]交换
                r[k] = r[min];
                r[min] = temp;
            }//end of if
        }//end of for(int k=0…
    }//end of selectSort
}
