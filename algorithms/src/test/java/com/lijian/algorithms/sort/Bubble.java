package com.lijian.algorithms.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//冒泡排序
public class Bubble {

    @Test
    public void test(){

        int[] array = {1, 5, 3, 8, 2};

        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length -1- i; j++) {
                if (array[j + 1] > array[j]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j+1]=temp;
                }
            }

        }
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }



    }
}
