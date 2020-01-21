package com.lijian.algorithms.leecode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// 双指针
public class DoubleIndex {

    public List<List<Integer>> fourSum(int[] nums, int target) {



        return Collections.emptyList();
    }




    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        Map<Integer,Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                continue;
            }else{
                map.put(nums[i], i);
            }
            list.addAll(twoSum3(nums, nums[i], i));
        }
        return list;
    }

    public List<List<Integer>> twoSum3(int[] nums, int target, int index) {
        List<List<Integer>> totalList = new ArrayList<>();
        Map<Integer,Integer> map =new LinkedHashMap<>();
        for (int i = index+1; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                List<Integer> result = Arrays.asList(nums[i],nums[ map.get(nums[i])], target);
                while (i+1<nums.length&&nums[i] == nums[ i+1]) i++;
                totalList.add(result);
            }else {
                map.put(-target - nums[i], i);
            }
        }
        return totalList;
    }


    @Test
    public void removeElementTest(){
        int result = removeElement2(new int[]{3,2,2,3}, 3);
        System.out.println(result);
    }

    public int removeElement(int[] nums, int val) {

        int i=0,j=nums.length-1;
        while (i < j) {
            if (nums[i] == val) {
                while (nums[j] == val) {
                    j--;
                }
                nums[i] = nums[j];
                i++;
                j--;
            }else{
                i++;
            }
        }

        while (j-1>=0&&nums[j] == nums[j - 1]) {
            j--;
        }

        return j;
    }
    public int removeElement2(int[] nums, int val) {
        if (nums == null || nums.length == 0)
            return 0;
        // 只要把不等于val 的个数 记录下来
         int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }



}
