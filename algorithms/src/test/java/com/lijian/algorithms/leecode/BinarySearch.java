package com.lijian.algorithms.leecode;

import org.junit.Test;

public class BinarySearch {


    @Test
    public void divideTest() {

        int result = divide(-2147483648, -1);
        System.out.println(result);
        //为什么 答案给的是 -2147483647 ？？
    }

    public int divide(int dividend, int divisor) {
        int t = dividend;
        if (dividend == 0) {
            return 0;
        }
        int count = 0;
        if (dividend > 0) {
            while (dividend >= Math.abs(divisor)) {
                dividend = dividend - Math.abs(divisor);
                count++;
            }
        }
        if (dividend < 0) {
            while (dividend <= -Math.abs(divisor)) {
                dividend = dividend + Math.abs(divisor);
                count++;
            }
        }
        if ((t > 0 && divisor > 0) || (t < 0 && divisor < 0)) {
            return count;
        }
        return -count;
    }


    @Test
    public void searchRangeTest() {

        int[] result = searchRange(new int[]{5
        }, 5);

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public int[] searchRange(int[] a, int value) {
        int[] result = new int[2];
        int n = a.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                //进入 else 表示 a[mid]==value,由于 array 是顺序排列的所以 重复元素必定相连
                if ((mid == 0) || (a[mid - 1] != value)) {
                    result[0] = mid;

                    while (mid < a.length && a[mid] == value) {
                        mid++;
                    }
                    result[1] = mid - 1;
                    return result;
                } else high = mid - 1;
            }
        }

        result[0] = -1;
        result[1] = -1;
        return result;
    }

@Test
public void searchInsertTest(){

    int result = searchInsert(new int[]{1, 3, 5, 6}, 7);
    System.out.println(result);
}


    public int searchInsert(int[] a, int value) {
        int n = a.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || (a[mid - 1] < value)) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return a.length;
    }
}
