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
//80 删除排序数组中的重复项 II

    public int removeDuplicates(int[] nums) {
        // 使用双指针
        if (nums == null || nums.length == 1) {
            return nums.length;
        }
        // 快慢指针
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[i] == nums[j]) {
                j++;

            } else {
                i++;
                nums[i] = nums[j];
                j++;
            }
        }
        return i + 1;
    }


    @Test
    public void removeDuplicatesTest(){
        int result = removeDuplicates2(new int[]{1, 1, 2});
        System.out.println(result);
    }

//    1,1,2
//    1,2,2

    public int removeDuplicates2(int[] nums) {
        // 使用双指针
        if (nums == null || nums.length == 1) {
            return nums.length;
        }
        // 快慢指针
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[i] == nums[j]) {
                j++;
            } else {
                i++;
                nums[i] = nums[j];
                j++;
            }
        }
        return i + 1;
    }


    public int removeDuplicates22(int[] nums) {
        int i = 0;
        for (int n : nums){
            if (i < 2 || n != nums[i-2]){
                nums[i] = n;
                i++;
            }
        }
        return i;
    }

//977. 有序数组的平方
    @Test
    public void sortedSquaresTest(){
        int[] result = sortedSquares(new int[]{-4, -1, 0, 3, 10});
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);

        }
    }
    public int[] sortedSquares(int[] a) {

        int i=0,j=a.length-1;
        int k=a.length-1;
        int [] result = new int[a.length];
        while (i <= j) {
            if (Math.abs(a[i]) < Math.abs(a[j])) {
                result[k] = a[j] * a[j];
                j--;
                k--;
            }else{
                result[k] = a[i] * a[i];
                i++;
                k--;
            }
        }
        return result;
    }
}
