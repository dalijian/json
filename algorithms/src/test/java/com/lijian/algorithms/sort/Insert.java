package com.lijian.algorithms.sort;

import org.junit.Test;
//插入排序
public class Insert {
//    插入排序 是 取未排序区间中的元素，在已排序区间中找到合适的插入位置将其插入，并保证已排序区间一直有序
    @Test
    public void test(){
        int [] a ={4,6,2,9,1} ;
        int n=5;

        if (n <= 1) {
            return;
        }
        //查找插入的位置，在 已排序 区间中 找到位置
        for (int i = 0; i < n; i++) {
            int value = a[i];
            int j=i-1;
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    //数据移动
                    a[j + 1] = a[j];
                }else{
                    break;
                }
            }
            //插入数据
            a[j+1]= value;

        }

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
