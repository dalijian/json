package com.lijian.algorithms.search;

import org.junit.Test;

//二分查找法

public class BinarySearch {


    @Test
    public void test(){


        int[] array = {1, 1,1,4, 9, 10, 34, 45, 65, 78, 99};



      int index =  bSearch(array, 0, array.length - 1, 99);

        int num = bSearchFirst(99);
        System.out.println(index);

    }
//    递归式
    private int bSearch(int[] array, int left,int right,int i) {
        int middle = (left+right)/2;
        if (i == array[middle]) {
            return middle;
        }
                if (left == right) {
            return -1;
        }
        if ( i>array[middle]) {
        return     bSearch(array, middle+1, right, i);
        }else{
        return     bSearch(array, left, middle-1, i);
        }

    }

//    循环式
   int  bSearch(int i ) {
       int[] array = {1, 4, 9, 10, 34, 45, 65, 78, 99};

       int left =0;

       int right =array.length;

       while (left <= right) {
           int middle = (left + right) / 2;

           if (array[middle] == i) {
               return middle;
           }
           if (i > array[middle]) {
               left=middle+1;

           }else{
               right=middle-1;
           }
       }

       return -1;
    }
//    查找第一个值等于给定值的元素
    int  bSearchFirst(int i ) {
        int[] array = {1, 1,1,4, 9, 10, 34, 45, 65, 78, 99};

        int left =0;

        int right =array.length;

        while (left <= right) {
            int middle = (left + right) / 2;
            if (i > array[middle]) {
                left=middle+1;

            }else if (i<array[middle]){
                right=middle-1;
            }
            else {
                if (middle == 0 || array[middle] != i) {
                    return middle;
                }else   {
                    right = middle-1;
                }
            }
        }

        return -1;
    }
//    查询第一个 元素
    public int bsearchFirst(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                //进入 else 表示 a[mid]==value,由于 array 是顺序排列的所以 重复元素必定相连
                if ((mid == 0) || (a[mid - 1] != value)) return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }


//查询最后一个元素
    public int bsearchLast(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == n - 1) || (a[mid + 1] != value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

//    查询第一个大于给定元素的值
    public int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || (a[mid - 1] < value)) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }



//    查询第一个小于给定元素的值
    public int bsearch7(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == n - 1) || (a[mid + 1] > value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }
    @Test
    public void te(){
        int[] array = {1, 1,1,4,4, 9, 10, 34, 45, 65, 78, 99};

        int index = bsearchFirst(array,array.length,2);
        System.out.println(index);
    }
}
