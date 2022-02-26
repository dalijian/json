package com.lijian.algorithms.backtracking;

import org.junit.Test;

import java.util.List;
import java.util.Stack;

public class BackTrackingTest {

    /**
     * 组合
     */
    @Test
    public void combinationTest() {

    }


    /**
     * 排列
     */
    @Test
    public void permutationTest() {

        int[] array = {1, 2, 3, 4, 5};
        int[] temp = new int[2];
        permutationArray(array, 2, 0, temp);
    }

    /**
     * @param array
     * @param target
     * @param current
     * @param temp    容器 使用 数组
     */
    private void permutationArray(int[] array, int target, int current, int[] temp) {
        if (target == current) {

            System.out.println("*************");
            for (int i = 0; i < temp.length; i++) {
                System.out.print(temp[i]);
            }
            return;
        }
        for (int i = 0; i < array.length; i++) {
            temp[current] = (array[i]);
            permutationArray(array, target, current + 1, temp);

            int[] new_temp = new int[2];
            System.arraycopy(temp, current, new_temp, current, 1);// 数组 不支持 删除 需要 重新 建立 数组

        }
    }

    /**
     * @param array
     * @param target
     * @param current
     * @param temp    容器  使用 list
     */
    private void permutationList(int[] array, int target, int current, List<Integer> temp) {
        if (target == current) {

            System.out.println(temp);
            return;
        }
        for (int i = 0; i < array.length; i++) {
            temp.add(array[i]);
            permutationList(array, target, current + 1, temp);
            temp.remove(current); // 需要 清空 回溯 时 上次递归 放进容器 的 值
        }
    }



    /**
     * 排列
     */
    @Test
    public void permutationStackTest() {

        int[] array = {1, 2, 3,4,5,6,7};
        Stack temp = new Stack();
        permutationStack(array, 3, 0,temp,0);
    }

    /**
     * @param array
     * @param target
     * @param current
     * @param temp
     * @param curr
     */
    private void permutationStack(int[] array, int target, int current, Stack temp,int curr) {
        if (target == current) {
            System.out.println(temp);
            return;
        }
        // i 不是 从 0 开始 ，而是 从 curr 开始 ，curr 是 选择 第一个 数 的 下标 ，导致 后续 选择 的 数 索引 都 大于 第一个 数 的 索引
        for (int i = curr; i < array.length; i++) {
            if (!temp.contains(array[i])){
                temp.add(array[i]);
                permutationStack(array, target, current + 1, temp,i);
                temp.pop(); // 需要 清空 回溯 时 上次递归 放进容器 的 值
            }

        }
    }

    /**
     * 组合 不考虑 顺序 比如在 3个数中选择 2个数,组合方法有C（3,2）=3种,是12、13、23
     *
     * @param shu  元素
     * @param targ 要选多少个元素
     * @param has  当前有多少个元素
     * @param cur  当前选到的下标
     *             <p>
     *             1    2   3     //开始下标到2
     *             1    2   4     //然后从3开始
     */
    private void f3(int[] shu, int targ, int has, int cur) {
        if (has == targ) {
            System.out.println(stack);
            return;
        }
        for (int i = cur; i < shu.length; i++) {
            if (!stack.contains(shu[i])) {
                stack.add(shu[i]);
                //通过 i 使得  递归 时 stack 放入 元素 下标 是递减 的 所以  导致 不会 出现 下标 大 的 排到 下标 小 的 前面 ，
                // 例如 只会 出现 (1,2) 不会 出现  (2,1)
                f3(shu, targ, has + 1, i);
                stack.pop();
            }
        }
    }
    public Stack<Integer> stack = new Stack<Integer>();
}
