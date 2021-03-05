package com.lijian.algorithms.leecode;

import org.junit.Test;

public class BitExample {

@Test
public void singleNumberTest(){

    singleNumber(new int[]{4,1,2,1,2});
}

    public int singleNumber(int[] nums) {
        int ans = nums[0];
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                ans = ans ^ nums[i];
            }
        }
        return ans;

    }

    public int singleNumber2(int[] nums) {
        return 0;
    }
}
