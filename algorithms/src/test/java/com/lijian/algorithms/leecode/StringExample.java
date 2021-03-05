package com.lijian.algorithms.leecode;


import org.junit.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringExample {

    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int ca = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum % 2);
            ca = sum / 2;
        }
        ans.append(ca == 1 ? ca : "");
        return ans.reverse().toString();
    }

    @Test
    public void isPalindromeTest() {
        System.out.println((int) 'a');
        System.out.println((int) 'Z');
        System.out.println((int) 'z');
        System.out.println((int) 'A');
        System.out.println((int) '0');
        System.out.println((int) '9');
        System.out.println((int) '1');
//    boolean result = isPalindrome("A man, a plan, a canal: Panama");
//    System.out.println(result);
    }

    //125. 验证回文串
    public boolean isPalindrome(String str) {
        str = str.toLowerCase();
        char[] array = str.toCharArray();
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            if (array[i] < 48 || array[i] > 49 || array[i] < 97 || array[i] > 122) {
                i++;
                continue;
            }


            if (array[j] == ' ') {
                j++;
                continue;
            }
            if (array[i] != array[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    //131. 分割回文串
//        回溯 算法
    //    有多少 种情况
    @Test
    public void partitionTest() {

        List<List<String>> result = partition("aab");
        System.out.println(result);
    }

    public List<List<String>> partition(String s) {
        List<List<String>> lists = new LinkedList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        int length = s.length();
        for (int len = 1; len <= length; len++) {
            for (int i = 0; i <= s.length() - len; i++) {
                int j = i + len - 1;
                dp[i][j] = s.charAt(i) == s.charAt(j) && (len < 3 || dp[i + 1][j - 1]);
            }
        }
        partitionRe(s, 0, new LinkedList(), lists, dp);
        return lists;
    }

    private void partitionRe(String s, int i, LinkedList<String> values, List<List<String>> lists, boolean[][] dp) {

        if (i == s.length()) {
            lists.add(new LinkedList<>(values));
            return;
        }
        for (int j = i + 1; j <= s.length(); j++) {
            if (dp[i][j - 1]) {// 使用 备忘录
                values.add(s.substring(i, j));
                partitionRe(s, j, values, lists, dp);
                values.removeLast();
            }
        }
    }

    public boolean isPalindrome2(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 1) {
            return true;
        }
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != array[array.length - i - 1]) {
                return false;
            }
        }
        return true;
    }


//132. 分割回文串 II


    public int minCut(String s) {
        int len = s.length();
        if (len < 2) {
            return 0;
        }

        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = i;
        }

        for (int i = 1; i < len; i++) {
            if (checkPalindrome(s, 0, i)) {
                dp[i] = 0;

                // 如果 dp[i] 本身 是 回文数， 则跳过 下面 枚举
                continue;
            }
            // 当 j == i 成立的时候，s[i] 就一个字符，一定是回文
            // 因此，枚举到 i - 1 即可
            for (int j = 0; j < i; j++) {
                if (checkPalindrome(s, j + 1, i)) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[len - 1];
    }

    private boolean checkPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    @Test
    public void wordBreakTest() {
        boolean result = wordBreak("catsanddog", Stream.of(new String[]{"cat", "cats", "and", "sand", "dog"}).collect(Collectors.toList()));
        System.out.println(result);
    }

    //139. 单词拆分
    public boolean wordBreak(String s, List<String> wordDict) {
        List<String> list = new LinkedList<>();
        workBreakRe(s, 0, new LinkedList(), wordDict, list);

        System.out.println(list);
        return list.size() > 0;
    }

    private void workBreakRe(String s, int i, Deque<String> value, List<String> wordDict, List<String> list) {

        if (s.length() == 0) {
            list.add(new String(value.stream().collect(Collectors.joining(" "))));
            return;
        }

        for (int j = 0; j < wordDict.size(); j++) {

            if (s.matches("^" + wordDict.get(j) + ".*")) {
                value.add(wordDict.get(j));
                // 在 递归 过程中 String 类型的 参数  不会 影响 上层 递归
                workBreakRe(s.replaceFirst(wordDict.get(j), ""), i, value, wordDict, list);
                value.removeLast();
            }

        }
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        return word_Break(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }

    public boolean word_Break(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }

    public boolean wordBreak3(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    @Test
    public void wordBreak2Test() {
        List<String>  result = wordBreak("catsanddog", Stream.of(new String[]{"cat", "cats", "and", "sand", "dog"}).collect(Collectors.toSet()));
        System.out.println(result);
    }
    //    140. 单词拆分 II
    public List<String> wordBreak(String s, Set<String> wordDict) {
        return word_Break(s, wordDict, 0);
    }

    HashMap<Integer, List<String>> map = new HashMap<>();

    public List<String> word_Break(String s, Set<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = word_Break(s, wordDict, end);
                for (String l : list) {
                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        map.put(start, res);
        return res;
    }


}
