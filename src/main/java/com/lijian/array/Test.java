package com.lijian.array;

import java.util.Arrays;

/**
 * @author zhengbinMac
 */
public class Test {
    public static void main(String[] args) {
        int[] array1 = {1,2,3,4,5};
        // 1.通过for循环
        int[] array2 = new int[5];
        for(int i = 0;i < array1.length;i++) {
            array2[i] = array1[i];
        }
        array1[1]=9;
        for(int i = 0;i < array2.length;i++) {
            System.out.print(array2[i]);
        }
        System.out.println("*********");
        System.out.println();
        //2.通过System.arraycopy()
        int[] array3 = new int[5];
        System.arraycopy(array1, 0, array3, 0, 5);
        for (int i = 0; i < array3.length; i++) {
            System.out.print(array3[i]);
        }
        System.out.println();
        //3.通过Arrays.copyOf()
        int[] array4 = new int[5];
        array4 = Arrays.copyOf(array1, 5);
        for (int i = 0; i < array4.length; i++) {
            System.out.print(array4[i]);
        }
        System.out.println();
        //4.通过Object.clone()
        int[] array5 = new int[5];
        array5 = array4.clone();
        for (int i = 0; i < array5.length; i++) {
            System.out.print(array5[i]);
        }
    }
}