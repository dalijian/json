package com.lijian.algorithms.leecode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// 回溯算法

public class BackTrack {
    @Test
    public void letterCombinationsTest() {
        List<String> result = letterCombinations("3");
        System.out.println(result);
    }

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return Collections.emptyList();
        }
        Map<Character, Character[]> map = new LinkedHashMap<>();
        map.put('2', new Character[]{'a', 'b', 'c'});
        map.put('3', new Character[]{'d', 'e', 'f'});
        map.put('4', new Character[]{'g', 'h', 'i'});
        map.put('5', new Character[]{'j', 'k', 'l'});
        map.put('6', new Character[]{'m', 'n', 'o'});
        map.put('7', new Character[]{'p', 'q', 'r', 's'});
        map.put('8', new Character[]{'t', 'u', 'v'});
        map.put('9', new Character[]{'w', 'x', 'y', 'z'});
        List<String> list = new ArrayList<>();
        letterCombinations(list, 0, digits.toCharArray(), map, new char[digits.length()]);
        return list;
    }

    /**
     * 使用 回溯 算法
     *
     * @param list  回溯 结果集
     * @param i     当前选择次数 第几次
     * @param nums  输入值
     * @param map   输入值 映射map
     * @param chars 递归中 存放 结果值
     */
    private void letterCombinations(List<String> list, int i, char[] nums, Map<Character, Character[]> map, char[] chars) {
        if (i == nums.length) {
            list.add(new String(chars));
            return;
        }
        for (int j = 0; j < map.get(nums[i]).length; j++) {
            if (!listContains(i, j, nums, chars)) {
                // 这里 必须 是 替换 ，而不能 是 新增
                chars[i] = map.get(nums[i])[j];
                letterCombinations(list, i + 1, nums, map, chars);
            }
        }
    }

    /**
     * 判断 元素 是否 可以 插入
     *
     * @param i        插入 元素 在 tempList 的 下标
     * @param j        插入 元素 在  nums 的 下标
     * @param nums     递归-循环问题 中的 中问题  使用  循环 解决  从 集合 中 选择 一个
     * @param tempList 当前 排列
     * @return
     */
    private boolean listContains(int i, int j, char[] nums, char[] tempList) {
//        if (tempList.length == 0) {
//            return false;
//        }
//        // 在 i 之前 的 元素 都不用 判断 是否 重复  ， 在 i 之后 的 元素 要 判断 是否 存在 插入值  即 nums[j]
//        for (int k = 0; k < i; k++) {
//            if (tempList[k] == nums[j]) {
//                return true;
//            }
//        }
        return false;
    }


    @Test
    public void isMatchTest() {
        isMatch("asdf", "as*");

    }

    // 不适合 使用 回溯 算法
    public boolean isMatch(String s, String p) {

        char[] charS = s.toCharArray();
        char[] charP = p.toCharArray();
        char[] charTemp = new char[charS.length];

        return match(charS, charP, 0, charTemp);

    }

    private boolean match(char[] charS, char[] charP, int i, char[] charTemp) {
        if (i == charS.length) {
            if (new String(charS).equals(new String(charTemp))) {
                System.out.println(new String(charTemp));
                return true;
            }
        }
        if (charP[i] == '*') {
            for (int j = 0; j < charS.length - i + 1; j++) {
                System.arraycopy(charS, i, charTemp, i, j);
            }
        } else if (charP[i] == '?') {
            charTemp[i] = charS[i];

        } else {
            if (charP[i] == charS[i]) {

                charTemp[i] = charP[i];
            } else {
                return false;
            }
        }
        match(charS, charP, i + 1, charTemp);
        return false;
    }

    @Test
    public void combinationSumTest() {


        List<List<Integer>> result = combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(result);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int[] values = new int[target];
        List<List<Integer>> list = new LinkedList<>();
        int[] tempCandidates = new int[candidates.length + 1];

        System.arraycopy(candidates, 0, tempCandidates, 0, candidates.length);
        combinationSumBT(tempCandidates, target, values, list, 0);
        return list;
    }

    private void combinationSumBT(int[] candidates, int target, int[] values, List<List<Integer>> list, int j) {


        int sum = IntStream.of(values).sum();
        if (sum == target) {
            list.add(IntStream.of(values).boxed().collect(Collectors.toList()));
            return;
        } else if (sum > target) {
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            if (checkSum(candidates[i], j, values, target)) {
                values[j] = candidates[i];
                combinationSumBT(candidates, target, values, list, j + 1);
            }
        }

    }

    private boolean checkSum(int candidate, int j, int[] values, int target) {
        if (j >= values.length) {
            return false;
        }
        values[j] = candidate;
        int sum = IntStream.of(values).sum();
        return sum > target ? false : true;
    }


    @Test
    public void combinationSumTest2() {

        List<List<Integer>> result = combinationSum2(new int[]{1, 2, 2, 4}, 8);
        System.out.println(result);
    }


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        //System.out.println(candidates);
        backtrack(candidates, target, res, 0, new ArrayList<Integer>());
        return res;
    }

    /**
     * 使用 list 传值  通过 1 增 1减   达到 list  在 递归 上下 文 中  保存 相对 稳定， 类似 于 使用 array [j]= candidate[i] 这种 跟新 操作
     *
     * @param candidates
     * @param target
     * @param res
     * @param i
     * @param tmp_list
     */
    private void backtrack(int[] candidates, int target, List<List<Integer>> res, int i, ArrayList<Integer> tmp_list) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(tmp_list));
            return;
        }
        for (int start = i; start < candidates.length; start++) {
            if (target < 0) break;
            // 通过 start >i ,  控制  【2，2，4，4】 只会 出现 【2，2，4】 不会 有 【2，2，4】，【2，2，4】
//                start=1 时 ，tmp_list 为 【2】， 尽管 candidates[0]=candidates[1]  ,但通过 start>1  确保   2 可以 添加 进 tmp_list

            if (start > i && candidates[start - 1] == candidates[start]) {
                continue;
            }

            tmp_list.add(candidates[start]);

            backtrack(candidates, target - candidates[start], res, start + 1, tmp_list);

            tmp_list.remove(tmp_list.size() - 1);

        }
    }


    @Test
    public void combinationSumTest22() {

        List<List<Integer>> result = combinationSum22(new int[]{10, 1, 2, 2, 7, 4, 6, 1, 5}, 8);
        System.out.println(result);
    }

    public List<List<Integer>> combinationSum22(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        // 先将数组排序，这一步很关键
        Arrays.sort(candidates);

        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(candidates, len, 0, target, path, res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param len
     * @param begin      从候选数组的 begin 位置开始搜索
     * @param residue    表示剩余，这个值一开始等于 target，基于题目中说明的"所有数字（包括目标数）都是正整数"这个条件
     * @param path       从根结点到叶子结点的路径
     * @param res
     */
    private void dfs(int[] candidates, int len, int begin, int residue, Deque<Integer> path, List<List<Integer>> res) {
        if (residue == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            // 大剪枝
            if (residue - candidates[i] < 0) {
                break;
            }
            // 小剪枝
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.addLast(candidates[i]);
            // 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
            dfs(candidates, len, i + 1, residue - candidates[i], path, res);
            path.removeLast();
        }
    }

}
