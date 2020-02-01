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
    public void searchInsertTest() {

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

    @Test
    public void mySqrtTest() {
        int result = mySqrt(3);
        System.out.println(result);
    }

    public int mySqrt(int x) {
        // Math.pow((x/2),2) >= x
        // 注意：针对特殊测试用例，例如 2147395599
        // 要把搜索的范围设置成长整型
        // 为了照顾到 0 把左边界设置为 0
        long left = 0;
        // # 为了照顾到 1 把右边界设置为 x // 2 + 1
        long right = x / 2 + 1;
        while (left < right) {
            // 注意：这里一定取右中位数，如果取左中位数，代码会进入死循环
            // long mid = left + (right - left + 1) / 2;
            long mid = (left + right + 1) >>> 1;
            long square = mid * mid;
            if (square > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        // 因为一定存在，因此无需后处理
        return (int) left;
    }


    @Test
    public void mySqrt2Test() {

        int result = mySqrt2(10);
        System.out.println(result);
    }

    public int mySqrt2(int x) {
        int s = x;
        if (x == 0) return 0;
        return ((int) (sqrts(x, s)));
    }

    /**
     * 牛顿法 不断 逼近
     *
     * @param x
     * @param s
     * @return
     */
    public double sqrts(double x, int s) {
        double res = (x + s / x) / 2;
        System.out.println(res);
        if (res == x) {
            return x;
        } else {
            return sqrts(res, s);
        }
    }


    @Test
    public void searchMatrixTest() {
        boolean result = searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}}, 50);
        System.out.println(result);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }

        boolean result = binarySearchMatrix(matrix, 0, matrix.length * matrix[0].length-1, target);
        return result;
    }

    private boolean binarySearchMatrix(int[][] matrix, int left, int right, int target) {
        if (left <= right) {

            int middle =  (left + right) / 2;
            int middleRow = middle / matrix[0].length ;
            int middleColume = middle % matrix[0].length;

            if (matrix[middleRow][middleColume]==target){
                return true;
            }
            if (matrix[middleRow][middleColume]<target){
                return binarySearchMatrix(matrix, middle + 1, right, target);
            }
            if (matrix[middleRow][middleColume]>target){
                return binarySearchMatrix(matrix, left, middle-1, target);
            }
        }
        return false;
    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] numsTotal = new int[nums1.length];
        merge2(nums1, nums2, m-n, n, numsTotal);

    }

    private void merge2(int[] nums1, int[] nums2, int m, int n, int[] numsTotal) {
        int i=0;
        int j=0;
        int t=0;

        while (i <=m && j <= n) {

            if (nums1[i] <= nums2[j]) {
                numsTotal[t++] = nums1[i++];
            }else{
                numsTotal[t++] = nums2[j++];
            }
        }

        while (i <= m) {
            numsTotal[t++] = nums1[i++];
        }
        while (j <= n) {
            numsTotal[t++] = nums2[j++];
        }

        System.arraycopy(numsTotal, 0, nums1, 0, numsTotal.length);
    }

//    81. 搜索旋转排序数组 II
//    处理
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[start] == nums[mid]) {
                start++;
                continue;
            }
            //前半部分有序
            if (nums[start] < nums[mid]) {
                //target在前半部分
                if (nums[mid] > target && nums[start] <= target) {
                    end = mid - 1;
                } else {  //否则，去后半部分找
                    start = mid + 1;
                }
            } else {
                //后半部分有序
                //target在后半部分
                if (nums[mid] < target && nums[end] >= target) {
                    start = mid + 1;
                } else {  //否则，去后半部分找
                    end = mid - 1;

                }
            }
        }
        //一直没找到，返回false
        return false;

    }

}
