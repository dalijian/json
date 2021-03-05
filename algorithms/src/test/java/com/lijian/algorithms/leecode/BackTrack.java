package com.lijian.algorithms.leecode;

import org.junit.Test;
import org.springframework.util.StringUtils;

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


//    @Test
//    public void isMatchTest() {
//        isMatch("asdf", "as*");
//
//    }
//
//    // 不适合 使用 回溯 算法
//    public boolean isMatch(String s, String p) {
//
//        char[] charS = s.toCharArray();
//        char[] charP = p.toCharArray();
//        char[] charTemp = new char[charS.length];
//
//        return match(charS, charP, 0, charTemp);
//
//    }
//
//    private boolean match(char[] charS, char[] charP, int i, char[] charTemp) {
//        if (i == charS.length) {
//            if (new String(charS).equals(new String(charTemp))) {
//                System.out.println(new String(charTemp));
//                return true;
//            }
//        }
//        if (charP[i] == '*') {
//            for (int j = 0; j < charS.length - i + 1; j++) {
//                System.arraycopy(charS, i, charTemp, i, j);
//            }
//        } else if (charP[i] == '?') {
//            charTemp[i] = charS[i];
//
//        } else {
//            if (charP[i] == charS[i]) {
//
//                charTemp[i] = charP[i];
//            } else {
//                return false;
//            }
//        }
//        match(charS, charP, i + 1, charTemp);
//        return false;
//    }

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

    // 从 存在 重复 集合 M 中 抽取  1 一个
    @Test
    public void pernuteUniqueTest() {

        List<List<Integer>> result = permuteUnique(new int[]{1, 1, 2});
        System.out.println(result);
    }


    public List<List<Integer>> permuteUnique(int[] nums) {

        if (nums.length == 0) {
            return Collections.emptyList();
        }
        // 预处理 nums
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        List<List<Integer>> list = new ArrayList<>();
        permuteUniqueRe(list, 0, nums, new int[nums.length], map);
        return list;

    }

    private void permuteUniqueRe(List<List<Integer>> list, int i, int[] nums, int[] tempList, Map<Integer, Integer> map) {
        if (i == nums.length) {
            // 好奇 这里 如果 不是 new List()  而是  使用 参数 传递 过来 的 list ， 输出的 list  只有 一个值  ，
            // 而 如果 new LIst()  那么 输出 的 值 包含 全部  排列 类型
            List<Integer> list1 = new ArrayList<>();
            for (int j = 0; j < tempList.length; j++) {
                list1.add(tempList[j]);
            }
            list.add(list1);
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            // 这边 是 指定 位置 nums[i] 不包含   nums[j]
            if (!listContainsUnique(i, j, nums, tempList, map)) {
                // 这里 必须 是 替换 ，而不能 是 新增
                tempList[i] = nums[j];
                permuteUniqueRe(list, i + 1, nums, tempList, map);
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
     * @param map
     * @return
     */
    private boolean listContainsUnique(int i, int j, int[] nums, int[] tempList, Map<Integer, Integer> map) {

        if (tempList.length == 0) {
            return false;
        }
        int count = 0;

        for (int k = 0; k < i; k++) {
            if (tempList[k] == nums[j]) {
                count++;
            }
            if (tempList[k] == nums[j] && count >= map.get(nums[j])) {
                return true;
            }
            if (count >= map.get(nums[j])) {
                return true;
            }
        }
        return false;
    }


    @Test
    public void permuteUnique2Test() {

        List<List<Integer>> result = permuteUnique2(new int[]{1, 1, 2});
        System.out.println(result);
    }

    public List<List<Integer>> permuteUnique2(int[] candidates) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        //System.out.println(candidates);
        backtrack(candidates, res, 0, new ArrayList<Integer>());
        return res;
    }

    /**
     * @param candidates
     * @param res
     * @param i
     * @param tmp_list
     */
    private void backtrack(int[] candidates, List<List<Integer>> res, int i, ArrayList<Integer> tmp_list) {
        if (candidates.length < 0) return;
        if (i == candidates.length) {
            res.add(new ArrayList<>(tmp_list));
            return;
        }
        for (int start = 0; start < candidates.length; start++) {

            if (check(candidates, start, i, tmp_list)) {
                tmp_list.add(candidates[start]);
                backtrack(candidates, res, i + 1, tmp_list);
                tmp_list.remove(tmp_list.size() - 1);
            }
        }
    }

    private boolean check(int[] candidates, int start, int i, ArrayList<Integer> tmp_list) {

        return true;
    }

    @Test
    public void permuteUnique3Test() {

        List<List<Integer>> result = permuteUnique3(new int[]{1, 1, 2});

        System.out.println(result);
    }


    public List<List<Integer>> permuteUnique3(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 修改 1：排序（这里用升序），为了剪枝方便
        Arrays.sort(nums);

        boolean[] used = new boolean[len];       // 使用 备忘录
        // 使用 Deque 是 Java 官方 Stack 类的建议
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(nums, len, 0, used, path, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, boolean[] used, Deque<Integer> stack, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!used[i]) {

                // 修改 2：在 used[i - 1] 刚刚被撤销的时候剪枝，说明接下来会被选择，搜索一定会重复，故"剪枝"
                if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
                    continue;
                }

                used[i] = true;
                stack.addLast(nums[i]);
                dfs(nums, len, depth + 1, used, stack, res);

                // 回溯，撤销选择
                stack.removeLast();
                used[i] = false;
            }
        }
    }


    @Test
    public void permuteUnique4Test() {

        List<List<Integer>> result = permuteUnique4(new int[]{1, 1, 2});

        System.out.println(result);
    }

    public List<List<Integer>> permuteUnique4(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 修改 1：排序（升序或者降序都可以），为了剪枝方便
        Arrays.sort(nums);

        boolean[] used = new boolean[len];
        // 使用 Deque 是 Java 官方 Stack 类的建议
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(nums, len, 0, used, path, res);
        return res;
    }

    private void dfs2(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 这个 pre 值必须是整个数组中没有出现的，因此下边界 - 1 或者是上边界 + 1，均可
        // 如果数组中有 int 类型的最大值或者最小值，可能会存在问题，好在这题里没有极端数据
        int pre = nums[0] - 1;
        for (int i = 0; i < len; ++i) {
            if (!used[i] && pre != nums[i]) {
                path.addLast(nums[i]);
                used[i] = true;
                dfs2(nums, len, depth + 1, used, path, res);
                used[i] = false;
                path.removeLast();
                pre = nums[i];
            }
        }
    }

    @Test
    public void getPermutationTest() {
        String result = getPermutation(3, 3);

    }

    public String getPermutation(int n, int k) {
        int[] shu = new int[n];
        for (int i = 0; i < n; i++) {
            shu[i] = i;
        }
        int[] temp = new int[n];
        f3(shu, 2, 0, 0, temp, 0, k);
        return toString();
    }

    private void f3(int[] shu, int targ, int has, int cur, int[] stack, int j, int k) {
        if (j == k) {

            return;
        }
        if (has == targ) {
            j++;
            return;
        }
        for (int i = cur; i < shu.length; i++) {
            if (checkValue(shu[i], stack)) {
                stack[has] = shu[i];
                f3(shu, targ, has + 1, i, stack, j, k);
            }
        }

    }

    private boolean checkValue(int values, int[] stack) {
        for (int j = 0; j < stack.length; j++) {
            if (stack[j] == values) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void combineTest() {

        List<List<Integer>> result = combine(5, 2);
        System.out.println(result);
    }

    public List<List<Integer>> combine(int n, int k) {
        int[] shu = new int[n];
        for (int i = 0; i < n; i++) {
            shu[i] = i + 1;
        }
        List<List<Integer>> list = new LinkedList<>();
        f3(shu, k, 0, 0, list, new LinkedList<>());
        return list;
    }

    /**
     * @param shu   组合 数组
     * @param targ  要选择多少个次
     * @param has   当前 已有 数
     * @param cur   当前选择的是第几次
     * @param list  组合数据集合
     * @param stack 选取 数据 暂存 集合
     */
    private void f3(int[] shu, int targ, int has, int cur, List<List<Integer>> list, LinkedList<Integer> stack) {
        if (has == targ) {
            list.add(new ArrayList<Integer>(stack));
            return;
        }
        for (int i = cur; i < shu.length; i++) {
            if (!stack.contains(shu[i])) {
                stack.add(shu[i]);
                f3(shu, targ, has + 1, i, list, stack);
                stack.removeLast();
            }
        }
    }


    // 字典序 處理法
    public List<List<Integer>> combine2(int n, int k) {
        // init first combination
        LinkedList<Integer> nums = new LinkedList<Integer>();
        for (int i = 1; i < k + 1; ++i)
            nums.add(i);
        nums.add(n + 1);

        List<List<Integer>> output = new ArrayList<List<Integer>>();
        int j = 0;
        while (j < k) {
            // add current combination
            output.add(new LinkedList(nums.subList(0, k)));
            // increase first nums[j] by one
            // if nums[j] + 1 != nums[j + 1]
            j = 0;
            while ((j < k) && (nums.get(j + 1) == nums.get(j) + 1))
                nums.set(j, j++ + 1);
            nums.set(j, nums.get(j) + 1);
        }
        return output;
    }


    //78  子集
    @Test
    public void subsetsTest() {

        List<List<Integer>> result = subsets(new int[]{1, 2, 3});
        System.out.println(result);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < nums.length + 1; i++) {
            f3(nums, i, 0, 0, resultList, new LinkedList<Integer>());
        }
        return resultList;
    }


    @Test
    public void subsetsWithDupTest() {

        List<List<Integer>> result = subsetsWithDup(new int[]{4, 4, 4, 1, 4});
        System.out.println(result);
    }

    //90. 子集 II
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length + 1; i++) {
            f4(nums, i, 0, 0, resultList, new LinkedList<>());
        }
        return resultList;
    }


    private void f4(int[] shu, int targ, int has, int cur, List<List<Integer>> list, Deque<Integer> stack) {
        if (has == targ) {
            list.add(new ArrayList<Integer>(stack));
            return;
        }
        for (int i = cur; i < shu.length; i++) {
//            if (!stack.contains(shu[i])) {
            // 通过 剪枝 去重
//                if (i > 0 && shu[i] == shu[i - 1]&&has==i-1) {
//                    continue;
//                }
            if (i > cur && shu[i] == shu[i - 1]) continue;
            stack.add(shu[i]);
            f4(shu, targ, has + 1, i + 1, list, stack);
            stack.removeLast();
//            }
        }

    }

    @Test
    public void restoreIpAddressesTest() {
//        字符串 不包含 0 是 没问题的
        List<String> result = restoreIpAddresses("010010");
        System.out.println(result);

    }

    //93. 复原IP地址
    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) {
            return Collections.emptyList();
        }
        List<String> resultList = new ArrayList<>();
        f5(s, 4, 0, 0, resultList, new int[4]);
        return resultList;
    }


    private void f5(String s, int targ, int has, int cur, List<String> list, int[] values) {
        if (has == targ) {
            list.add(IntStream.of(values).boxed().map(x -> String.valueOf(x)).collect(Collectors.joining(".")));
            return;
        }
        for (int i = cur; i < s.length() + 1; i++) {
            if (has == 3) {
//                if (Long.valueOf(s.substring(i)) > 255) {
//                    continue;
//                }
                if (i != s.length()) {
                    continue;
                }
            }
            if (s.substring(cur, i).equals("")) continue;
            if (Long.valueOf(s.substring(cur, i)) > 255) continue;
//            if (s.substring(cur,i).equals("00")) continue;
//            if (s.substring(cur,i).equals("01")) continue;
//            if (s.substring(cur,i).equals("011")) continue;
            if (s.substring(cur, i).length() != String.valueOf(Long.valueOf(s.substring(cur, i))).length()) continue;

//            if (Long.valueOf(s.substring(cur, i))<=(int)Math.pow(10,s.length()/4-1)-2) continue;

            values[has] = Integer.valueOf(s.substring(cur, i));
            f5(s, targ, has + 1, i, list, values);
        }

    }


    int n;
    String s;
    LinkedList<String> segments = new LinkedList<String>();
    ArrayList<String> output = new ArrayList<String>();

    public boolean valid(String segment) {
    /*
    Check if the current segment is valid :
    1. less or equal to 255
    2. the first character could be '0'
    only if the segment is equal to '0'
    */
        int m = segment.length();
        if (m > 3)
            return false;
        return (segment.charAt(0) != '0') ? (Integer.valueOf(segment) <= 255) : (m == 1);
    }

    public void update_output(int curr_pos) {
    /*
    Append the current list of segments
    to the list of solutions
    */
        String segment = s.substring(curr_pos + 1, n);
        if (valid(segment)) {
            segments.add(segment);
            output.add(String.join(".", segments));
            segments.removeLast();
        }
    }

    public void backtrack(int prev_pos, int dots) {
    /*
    prev_pos : the position of the previously placed dot
    dots : number of dots to place
    */
        // The current dot curr_pos could be placed
        // in a range from prev_pos + 1 to prev_pos + 4.
        // The dot couldn't be placed
        // after the last character in the string.
        int max_pos = Math.min(n - 1, prev_pos + 4);
        for (int curr_pos = prev_pos + 1; curr_pos < max_pos; curr_pos++) {
            String segment = s.substring(prev_pos + 1, curr_pos + 1);
            if (valid(segment)) {
                segments.add(segment);  // place dot
                if (dots - 1 == 0)      // if all 3 dots are placed
                    update_output(curr_pos);  // add the solution to output
                else
                    backtrack(curr_pos, dots - 1);  // continue to place dots
                segments.removeLast();  // remove the last placed dot
            }
        }
    }

    public List<String> restoreIpAddresses2(String s) {
        n = s.length();
        this.s = s;
        backtrack(-1, 3);
        return output;
    }


    //97. 交错字符串
//    将 s2 插入 s1 中
//        将 s2与s1拼接，选择一个 合适的排列 ，判断是否与 目标字符串相同,这样 s1,s2 字符串本身的顺序就乱了
    @Test
    public void isTneterleaveTest() {
        boolean result = isInterleave("ac", "b", "acb");

        System.out.println(result);
    }

    public boolean isInterleave(String s1, String s2, String s3) {

        char[] value = new char[s1.length() + s2.length()];
        LinkedList<String> linkedList = new LinkedList<>();
        List<String> list = new ArrayList<>();
        isInterleaveRe2(s1, s2, 0, 0, s3, linkedList, list);

        return list.size() > 0;
    }


    private void isInterleaveRe2(String s1, String s2, int i, int cur, String s3, LinkedList<String> values, List<String> list) {
        if (i == s1.length() + s2.length() && values.stream().collect(Collectors.joining()).equals(s3)) {
            list.add(values.stream().collect(Collectors.joining()));
            return;
        }
        // 像这种 将 s1,s2 组合 并且 s1,s2 内部顺序 不乱的 写法 ，使用 回溯 for 循环 没法实现，只能使用 回溯，每次选择 s1 字串+1或者s2 字串+1
//        for (int j = cur; j < s1.length(); j++) {
//            String tempStr = values.stream().collect(Collectors.joining());
//            if (!StringUtils.isEmpty(tempStr) && !s3.startsWith(tempStr))
//             {
//                continue;
//            }
//            values.add(s1.substring(j,j+1));
//            isInterleaveRe2(s1,s2, i + 1, cur + 1, s3, values, list);
//            values.removeLast();
//        }
//
//
//        for (int j = cur; j <s2.length(); j++) {
//            String tempStr = values.stream().collect(Collectors.joining());
//            if (!StringUtils.isEmpty(tempStr) && !s3.startsWith(tempStr))
//            {
//                continue;
//            }
//            values.add(s2.substring(j,j+1));
//            isInterleaveRe2(s1,s2, i + 1, cur + 1, s3, values, list);
//            values.removeLast();
//        }
    }


//    我们首先将 s1 中的第一个字符作为开始字符，然后将 s1 字符串剩余部分和 s2 字符串所有可能情况添加在这个字符后面，
//    每次用完所有字符后检查字符串与 s3 是否一致。
//    类似的，我们可以选择 s2 第一个字符作为开始字符，
//    然后将 s2 剩余字符串和 s1 字符串回溯地添加到该字符后面，看是否能形成 s3 。


    public boolean is_Interleave(String s1, int i, String s2, int j, String res, String s3) {
        if (res.equals(s3) && i == s1.length() && j == s2.length())
            return true;
        boolean ans = false;
        if (i < s1.length())
            ans |= is_Interleave(s1, i + 1, s2, j, res + s1.charAt(i), s3);
        if (j < s2.length())
            ans |= is_Interleave(s1, i, s2, j + 1, res + s2.charAt(j), s3);
        return ans;

    }

    @Test
    public void isTneterleave2Test() {
        boolean result = isInterleave2("ac", "b", "abc");
        System.out.println(result);
    }

    public boolean isInterleave2(String s1, String s2, String s3) {
        return is_Interleave(s1, 0, s2, 0, "", s3);
    }


    public boolean isInterleave3(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
//        dp[i][j] 表示用 s1s1 的前 (i+1)(i+1) 和 s2s2 的前 (j+1)(j+1) 个字符，总共 (i+j+2)(i+j+2) 个字符，是否交错构成 s3s3 的前缀。
        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }


    @Test
    public void findTargetSumWaysTest(){

        int result = findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
        System.out.println(result);
    }
    //494  目标和
    public int findTargetSumWays(int[] nums, int s) {
        char[] list = new char[nums.length];
        char[] chars= {'-', '+'};
        List<char[]> result= new ArrayList<>();
        findTargetSumWaysRe(nums, 0, 0, s, list,chars,result);
        return result.size();
    }

    private void findTargetSumWaysRe(int[] nums, int i, int j, int s, char[] list, char[] chars, List<char[]> result) {

        if (i == nums.length) {
            int sum =0;
            for (int k = 0; k < nums.length; k++) {
                if( list[k] == '-'){
                    sum += -nums[k];
                }else {
                    sum += nums[k];
                }
            }
            if (sum == s) {
                result.add(list);
            }
            return;
        }
        for (int k = 0; k < chars.length; k++) {
            list[i] = chars[k];
            findTargetSumWaysRe(nums, i + 1, j, s, list, chars, result);
        }
    }
@Test
public void chooseTest(){
        choose(new int[]{1,2},0, 3,new LinkedList());
}

    public void choose(int[] nums, int i, int s, LinkedList linkedList) {
        if (i == s) {
            System.out.println(linkedList);
            return;
        }
        for (int j = 0; j < nums.length; j++) {

            linkedList.add(nums[j]);
            choose(nums, i + 1, s, linkedList);
            linkedList.removeLast();
        }
    }
}
