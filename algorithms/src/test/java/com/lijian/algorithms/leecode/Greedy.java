package com.lijian.algorithms.leecode;

import org.junit.Test;

// 贪心算法
public class Greedy {

    @Test
    public void jumpTest(){

        int result = jump(new int[]{2, 3, 1, 1, 4});
        System.out.println(result);

    }

    public int jump(int[] nums) {
        int t=0;
        for (int i = 0; i < nums.length; i++) {
            int j=0;
            int k =0;
            while (j < nums[i]) {

                if (nums[k] < nums[j]) {
                    nums[k] = nums[j];
                    k=j;
                }
                j++;
            }
            i=k;
            t++;
        }
        return t;
    }

}