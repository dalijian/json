package com.lijian.algorithms.leecode;

//动态 规划

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class DynamicProgram {

    @Test
    public void longestValidParenthesesTest() {
        int result = longestValidParentheses("()(()");
        System.out.println(result);
    }

    // 通过 stack 把 之前的 记录 消除 掉， 相当于 使用 指针 j, j 向前 移动
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        // 初始化
        stack.push(-1);
        //System.out.println(stack);
        // 记录 最大长度
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }


    /**
     * (() 对应   dp[i]=dp[i-2]+2  ,s[i-1]='(';
     * ()(()) 对应   db[i]=dp[i-1]+2+dp[i-db[i-1]-2], s[i-1]=')'&&s[i-db[i-1]-1]='(';
     *                      dp[i-db[i-1]-2]->(),dp[i-1]+2->(())
     *
     * @param s
     * @return
     */

    public int longestValidParentheses2(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length()];
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] + 2 : 2);
                } else if (s.charAt(i - 1) == ')' && i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }


    @Test
    public void isMatchTest() {
        boolean flag = isMatch("acdcb", "a*c?b");
    }

    public boolean isMatch(String s, String p) {
        boolean[][] value = new boolean[p.length() + 1][s.length() + 1];
        value[0][0] = true;
        for (int i = 1; i <= s.length(); i++) {
            value[0][i] = false;
        }
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') {
                value[i][0] = value[i - 1][0];
                for (int j = 1; j <= s.length(); j++) { // "*"  不是 全匹配 吗 ， 干嘛 还要 判断  ？？
                    // 主要 处理 的 场景 是 s:asdf,p:ad*, 由于 p[1]!=s[1] 所以 即使 p[2]=*  也不能 匹配, 所以 要 考虑 上次 匹配 结果
                    value[i][j] = (value[i][j - 1] || value[i - 1][j]);
                }
            } else if (p.charAt(i - 1) == '?') {
                value[i][0] = false;
                for (int j = 1; j <= s.length(); j++) {
                    value[i][j] = value[i - 1][j - 1];
                }
            } else {
                value[i][0] = false;
                for (int j = 1; j <= s.length(); j++) {
                    value[i][j] = s.charAt(j - 1) == p.charAt(i - 1) && value[i - 1][j - 1];
                }
            }

        }
        return value[p.length()][s.length()];

    }


    @Test
    public void isMatchTest2() {
        boolean flag = isMatch2("acdcb", "a*c?b");

        System.out.println(flag);
    }

    public boolean isMatch2(String s, String p) {

        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];

        char[] pchar = p.toCharArray();
        char[] schar = s.toCharArray();


//        initial
        dp[0][0] = true;

        for (int i = 1; i < p.length(); i++) {

            if (pchar[i - 1] == '*') {
                for (int j = 1; j < s.length(); j++) {
                    dp[i][j] = (dp[i][j - 1] || dp[i - 1][j]);
                }
            } else if (pchar[i - 1] == '?') {
                for (int j = 1; j < s.length(); j++) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            } else {
                for (int j = 1; j < s.length(); j++) {
                    dp[i][j] = dp[i - 1][j - 1] && pchar[i - 1] == schar[j - 1];
                }
            }
        }
        return dp[p.length()][s.length()];
    }
@Test
public void isMatch3Test(){

    isMatch3("aa", "*");
}

    public boolean isMatch3(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] memory = new boolean[sLen + 1][pLen + 1];
        memory[0][0] = true;
        for (int i = 0; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (p.charAt(j - 1) == '*') {
                    if (memory[i][j - 2] || (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) ||
                            p.charAt(j - 2) == '.') && memory[i - 1][j])) {
                        memory[i][j] = true;
                    } else {
                        memory[i][j] = false;
                    }
                } else {
                    if (i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
                            && memory[i - 1][j - 1]) {
                        memory[i][j] = true;
                    } else {
                        memory[i][j] = false;
                    }
                }
            }
        }
        return memory[sLen][pLen];
    }


    @Test
    public void isMatch4Test(){

        boolean result = isMatch4("a", "a*");
        System.out.println(result);
    }

    public boolean isMatch4(String str, String pStr) {

        if (str.length() == 0 && pStr.equals("*")) {
            return true;
        }
        int[][] dp = new int[pStr.length() + 1][str.length() + 1];
        dp[0][0]=1;
        int n = str.length();
        int m = pStr.length();
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if (pStr.charAt(i-1) == '*') {
                    if (dp[i - 1][j-1] == 1) {
                        dp[i][j-1]=1;
                    }
                    if (dp[i][j - 1] == 1) {
                        dp[i][j] = dp[i][j - 1];
                    }
                    if (dp[i - 1][j] == 1) {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
                if (pStr.charAt(i-1) == '?') {
                    if (dp[i - 1][j - 1] == 1) {
                        dp[i][j] = 1;
                    }
                }
                if (dp[i-1][j-1]==1&&pStr.charAt(i-1) == str.charAt(j-1)) {
                    dp[i][j]=1;

                }
            }
        }
            return dp[m][n]==1?true:false;
    }

    @Test
    public void maxSubArrayTest() {

        int result = maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(result);

    }
    //53. 最长 子序列
    public int maxSubArray(int[] nums) {

        int n = nums.length, maxSum = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i - 1] > 0) nums[i] += nums[i - 1];
            maxSum = Math.max(nums[i], maxSum);
        }
        return maxSum;
    }


    @Test
    public void uniquePathsTest() {
        int result = uniquePaths(3, 3);
        System.out.println(result);


    }

    /**
     * // 棋盘 网格 移动 问题
     *
     * @param m 棋盘 行数
     * @param n 棋盘 列数
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        dp[0][0] = 1;
        List<Integer> list = new LinkedList<>();
        distDP(dp, 0, 0, list, m - 1, n - 1);
        return list.size();
    }



    /**
     *  //  動態 規劃  打印 选择 的 路径
     * @param dp  状态 转义 矩阵
     * @param m     当前 所在  矩阵 行数
     * @param n     当前 所在  矩阵 列数
     * @param count 递归 传参  返回 结果 参数
     * @param m1 矩阵 行数
     * @param n1 矩阵 列数
     */
    private void distDP(int[][] dp, int m, int n, List<Integer> count, int m1, int n1) {

        if (m == m1 && n == n1) {
            dp[m][n] = 1;
            printDP(dp);
            count.add(0);
            return;
        }
        if (m == m1 && n < n1) {
            dp[m][n] = 1;
            distDP(dp, m, n + 1, count, m1, n1);
        } else if (m < m1 && n == n1) {
            dp[m][n] = 1;
            distDP(dp, m + 1, n, count, m1, n1);
        }
        // 如何 自由选择 向下 或 向 右 走
        // 向 下 走 后 再将 向右走 设置 成 0，
        else {
            if (m + 1 <= m1) {
                dp[m + 1][n] = 1;
                dp[m][n + 1] = 0;
                distDP(dp, m + 1, n, count, m1, n1);
            }
            if (n + 1 <= n1) {
                dp[m][n + 1] = 1;
                dp[m + 1][n] = 0;
                distDP(dp, m, n + 1, count, m1, n1);
            }
        }
    }

    private void printDP(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j]);
                System.out.print(",");

            }
            System.out.println();
        }
        System.out.println("*********");
    }

    @Test
    public void uniquePathsTest2() {

        int result = uniquePaths2(12, 13);
        System.out.println(result);

    }

    //详细解释参考 官方题解
//递推公式 ： a[x][y] = a[x -1][y] + a[x][y - 1]
// 初始条件：a[m][] = 1; a[][n] = 1;

    // 到达 dp[i][j] 的 路径 等于  到达 dp[i][j-1]的路径数 + 到达 dp[i-1][j]的 路径 数
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 1;
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];

    }




    public int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        for (int j = 0; j < n; ++j) { // 初始化states的第一行数据
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        sum = 0;
        for (int i = 0; i < n; ++i) { // 初始化states的第一列数据
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                states[i][j] =
                        matrix[i][j] + Math.min(states[i][j - 1], states[i - 1][j]);
            }
        }
        return states[n - 1][n - 1];
    }

//63 不同 路徑

    @Test
    public void uniquePathsWithObstaclesTest() {

//        1,0
        int result = uniquePathsWithObstacles(new int[][]{{1, 0}});
        System.out.println(result);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && obstacleGrid[i][j] != 1 || j == 0 && obstacleGrid[i][j] != 1) {
                    dp[i][j] = 1;
                } else if (i == 0 && obstacleGrid[i][j] == 1 || j == 0 && obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {

        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;

        // If the starting cell has an obstacle, then simply return as there would be
        // no paths to the destination.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // Number of ways of reaching the starting cell = 1.
        obstacleGrid[0][0] = 1;

        // Filling the values for the first column
        for (int i = 1; i < R; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }

        // Filling the values for the first row
        for (int i = 1; i < C; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }

        // Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }

        // Return value stored in rightmost bottommost cell. That is the destination.
        return obstacleGrid[R - 1][C - 1];
    }


    public int minPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] states = new int[n][m];
        int sum = 0;
        for (int j = 0; j < m; ++j) { // 初始化states的第一行数据
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        sum = 0;
        for (int i = 0; i < n; ++i) { // 初始化states的第一列数据
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                states[i][j] =
                        matrix[i][j] + Math.min(states[i][j - 1], states[i - 1][j]);
            }
        }
        return states[n - 1][m - 1];
    }

    @Test
    public void climbStairsTest() {

        int result = climbStairs(4);
        System.out.println(result);
    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        if (n == 1) {
            return 1;
        }
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    @Test
    public void minDistanceTest() {

        String word1 = "horse";
        String word2 = "ros";
        minDistance(word1, word2);
    }

    //    Levenshtein distance 莱文斯坦距离
    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        // 第一行
        for (int j = 1; j <= n2; j++) dp[0][j] = dp[0][j - 1] + 1;
        // 第一列
        for (int i = 1; i <= n1; i++) dp[i][0] = dp[i - 1][0] + 1;

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
            }
        }
        return dp[n1][n2];
    }

    @Test
    public void minDistance2Test() {

        minDistance("horse", "ros");
    }

    //    莱文斯坦 距离 回溯
    public int minDistance2(String word1, String word2) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(1, Comparator.comparing(x1 -> x1));

        lwstBT(0, 0, 0, priorityQueue, word1.toCharArray(), word2.toCharArray(), word1.length(), word2.length());
        return priorityQueue.poll();
    }

//    莱文斯坦 距离 回溯

    public void lwstBT(int i, int j, int edist, PriorityQueue<Integer> priorityQueue, char[] a, char[] b, int n, int m) {
        Integer minDist = Integer.MIN_VALUE;
        if (i == n || j == m) {
            if (i < n) edist += (n - i);
            if (j < m) edist += (m - j);
            if (edist < minDist) minDist = edist;
            System.out.println(edist);
            priorityQueue.offer(edist);
            return;
        }
        if (a[i] == b[j]) { // 两个字符匹配
            lwstBT(i + 1, j + 1, edist, priorityQueue, a, b, n, m);
        } else { // 两个字符不匹配
            lwstBT(i + 1, j, edist + 1, priorityQueue, a, b, n, m); // 删除a[i]或者b[j]前添加一个字符
            lwstBT(i, j + 1, edist + 1, priorityQueue, a, b, n, m); // 删除b[j]或者a[i]前添加一个字符
            lwstBT(i + 1, j + 1, edist + 1, priorityQueue, a, b, n, m); // 将a[i]和b[j]替换为相同字符
        }
    }

    @Test
    public void maximalRectangleTest() {


        int result = maximalRectangle(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}});

        System.out.println(result);
    }
//85. 最大矩形
    public int maximalRectangle(char[][] matrix) {

        if (matrix.length == 0) return 0;
        int maxarea = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {

                    // compute the maximum width and update dp with it
                    dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;

                    int width = dp[i][j];

                    // compute the maximum area rectangle with a lower right corner at [i, j]
                    for (int k = i; k >= 0; k--) {
                        width = Math.min(width, dp[k][j]);
                        maxarea = Math.max(maxarea, width * (i - k + 1));
                    }
                }
            }
        }
        return maxarea;
    }


@Test
public void isScrambleTest(){


    boolean result = isScramble("great",
            "rgeat");
}
    public boolean isScramble(String s1, String s2) {

        isScrambleBinary(s1.toCharArray(),0,s1.length()-1);

        return false;
    }

    private void isScrambleBinary(char[] s1, int left, int right) {

        if (left < right) {
            System.out.println("" + new String(s1).substring(left, right+1));
            int model = ((right+left))/2;

            isScrambleBinary(s1, left, model-1);
            isScrambleBinary(s1, model, right);

        }


    }


    @Test
    public void robTest() {
        int[] nums = new int[]{183, 219, 57, 193, 94, 233, 202, 154, 65, 240, 97, 234, 100, 249, 186, 66, 90, 238, 168, 128, 177, 235, 50, 81, 185, 165, 217, 207, 88, 80, 112, 78, 135, 62, 228, 247, 211};
        rob(nums);
    }

    private int rob(int[] nums) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(1, Comparator.comparing(x -> -x));

        robRe(nums, 0, 0, priorityQueue);
        return priorityQueue.poll();
    }

    /**
     * 回溯 算法
     *
     * @param nums
     * @param j             nums 下标
     * @param sum           和
     * @param priorityQueue 存放 最大 和
     */
    public void robRe(int[] nums, int j, int sum, PriorityQueue<Integer> priorityQueue) {
        if (j >= nums.length) {
            System.out.println(sum);
            priorityQueue.offer(sum);
            return;
        }
        for (int i = j; i < nums.length; i++) {
            robRe(nums, i + 2, sum + nums[i], priorityQueue);
        }
    }


    @Test
    public void robDPTest() {

        int result = robDP(new int[]{1, 2, 3, 1});

        System.out.println(result);
    }


    // 由于 不能 去 相邻的数 ，所以 只要 比较 dp[i-2] 与 dp[i-3] 如果  不能去 间隔 2个元素 ，那么 要 比较  dp[i-2],dp[i-3],dp[i-4], 间隔 n 个 依次dp[i-2] .... dp[i-n],dp[i-n+1],dp[i-n+2]
    private int robDP(int[] nums) {

        int[] dp = new int[nums.length];
//        初始化 dp
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length > 1) {
            dp[0] = nums[0];
            dp[1] = nums[1];
        }
        for (int i = 2; i < nums.length; i++) {
            if (i - 3 >= 0) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 3] + nums[i]);
            } else {
                dp[i] = Math.max(dp[i - 2] + nums[i], 0);
            }
        }
        return dp[nums.length - 1] > dp[nums.length - 2] ? dp[nums.length - 1] : dp[nums.length - 2];
    }


    @Test
    public void maxProfitTest() {

        maxProfit(2, new int[]{1, 2});
    }

    /**
     * @param k      你最多可以完成 k 笔交易
     * @param prices 它的第 i 个元素是一支给定的股票在第 i 天的价格
     * @return 收益 最大值
     */
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        if (prices.length == 1) {
            return 0;
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(1, Comparator.comparing(x -> -x));
        Queue<Integer> queue = new LinkedList<>();
        maxProfitDB(k, prices, priorityQueue, 0, queue);
        System.out.println(priorityQueue.remove());
        return 0;
    }

    private void maxProfitDB(int k, int[] prices, PriorityQueue<Integer> priorityQueue, int i, Queue<Integer> sum) {
        if (i == k) {
            if (!sum.isEmpty()) {
                System.out.println(sum.peek());
                priorityQueue.offer(sum.poll());
            }
            return;
        }
        for (int j = i; j < prices.length; j++) {
            if (!sum.isEmpty()) {
                sum.offer(prices[j] - sum.poll());
            } else if (sum.isEmpty() && j == prices.length - 1) {
                continue;
            } else {
                sum.offer(prices[j]);
            }
            maxProfitDB(k, prices, priorityQueue, i + 1, sum);
        }
    }

    /**
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     *
     * @param prices
     * @return
     */

    @Test
    public void maxProfitTest1() {
        maxProfit(new int[]{7, 1, 5, 3, 6, 4});
    }

    public int maxProfit(int[] prices) {
        maxProfitDBSimple(prices, 0, new int[2]);
        return 0;
    }

    private void maxProfitDBSimple(int[] prices, int i, int[] value) {

        if (i == 2) {
            System.out.println(value[1] - value[0]);
            return;
        }
        for (int k = i; k < prices.length; k++) {
            value[i] = prices[k];
            maxProfitDBSimple(prices, i + 1, value);
        }
    }

    @Test
    public void maxProfit2Test() {

        maxProfit2(new int[]{

                2, 4, 1
        });
    }

    private int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                // 解释：
                //   dp[i][0]
                // = max(dp[-1][0], dp[-1][1] + prices[i])
                // = max(0, -infinity + prices[i]) = 0
                dp[i][1] = -prices[i];
                //解释：
                //   dp[i][1]
                // = max(dp[-1][1], dp[-1][0] - prices[i])
                // = max(-infinity, 0 - prices[i])
                // = -prices[i]
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];


    }


    @Test
    public void maxProfitTest3() {

        int result = maxProfit_k_1(new int[]{2, 4, 1});

        System.out.println(result);

    }

    // k == 1
    int maxProfit_k_1(int[] prices) {
        int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int[] dp = {0, Integer.MIN_VALUE};
        for (int i = 0; i < n; i++) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            dp[1] = Math.max(dp[1], -prices[i]);
        }
        return dp[0];
    }


    @Test

    public void calculateMinimumHpTest() {
        int result = calculateMinimumHP(new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}});
        System.out.println(result);

    }


// 动态规划，自底向上。 思路（代码详见题解）：因为骑士只能向下或向右走，当骑士在最后一行时，如果安全的通过最后一行，其生命值只和最后一行的值相关。
// 同理，当骑士不在最后一行时，其生命值只和其当前行的剩余位置值以及下一行的值相关。所以可以从公主的位置开始，自底向上，循环更新骑士的生命值。
// 骑士应选择扣生命值小的那一条路行走。

// 需要注意的是，当遇到生命值增加，且需要扣掉的生命值为正数时，应该将其归零，意思是骑士到这一格时只需1点生命值，即可遇到公主。

    // 最后将计算出的骑士在第一格的生命值取负加一，即为所需生命值
    public int calculateMinimumHP(int[][] dungeon) {

        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n][m];
        // 初始化 第一行
        dp[0][0] = dungeon[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = dungeon[0][i] + dp[0][i - 1];
        }
        // 初始化  第一列
        for (int i = 1; i < n; i++) {
            dp[i][0] += dungeon[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + dungeon[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }


    @Test
    public void length() {

        System.out.println("865352031983496".length());
    }

//91. 解码方法

    @Test
    public void numDecodingsTest(){

        int result = numDecodings("30");
        System.out.println(result);
    }
    public int numDecodings(String s) {

        if (s.length() == 0||(s.length() == 1&&Integer.parseInt(s)==0)||(s.charAt(0) == '0')) {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }


        int[] dp = new int[s.length()];
        if (s.length() >= 2 && Integer.parseInt(s.substring(0, 2))<=26&& Integer.parseInt(s.substring(0, 2))>10) {

            dp[1] = 2;
        }
        if (s.length() >= 2&& (Integer.parseInt(s.substring(0, 2))>26||Integer.parseInt(s.substring(0, 2))==10)) {

            dp[1] = 1;
        }
        dp[0]=1;
        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) == '0' && s.charAt(i - 1) == '0') {
                return 0;
            }

            if (s.charAt(i) <= '6' && s.charAt(i - 1) <= '2'&&s.charAt(i)!='0'&&s.charAt(i-1)!='0') {
                dp[i]=dp[i-1]+dp[i-2];
            }else if (s.charAt(i - 1) <= '2'&&s.charAt(i)=='0') {
                dp[i]=dp[i-2];
            }else if ( s.charAt(i - 1) > '2'&&s.charAt(i)=='0'){
                return 0;
            }else{
                dp[i]=dp[i-1];
            }
        }
        return dp[s.length()-1];
    }

}
