package com.lijian.algorithms.dynamicprogramming;

import org.junit.Test;

public class DP {


    @Test
    public void f_re_Test() {
        f_re(0, 0);
        System.out.println(maxW);
    }

    // 回溯算法实现。注意：我把输入的变量都定义成了成员变量。
//private int maxW = Integer.MIN_VALUE; // 结果放到 maxW 中
//private int[] weight = {2，2，4，6，3};  // 物品重量
//private int n = 5; // 物品个数
//private int w = 9; // 背包承受的最大重量
    public void f_re(int i, int cw) { // 调用 f(0, 0)
        if (cw == w || i == n) { // cw==w 表示装满了，i==n 表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        f_re(i + 1, cw); // 选择不装第 i 个物品
        if (cw + weight[i] <= w) {
            f_re(i + 1, cw + weight[i]); // 选择装第 i 个物品
        }
    }


    // 使用 回溯 算法 + 备忘录 用来 减少 相同状态 的 重复计算
    private int maxW = Integer.MIN_VALUE; // 结果放到maxW中
    private int[] weight = {2, 2, 4, 6, 3};  // 物品重量
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量
    private boolean[][] mem = new boolean[5][10]; // 备忘录，默认值false

    public void f(int i, int cw) { // 调用f(0, 0)
        if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        if (mem[i][cw]) return; // 重复状态
        mem[i][cw] = true; // 记录(i, cw)这个状态
        f(i + 1, cw); // 选择不装第i个物品
        if (cw + weight[i] <= w) {
            f(i + 1, cw + weight[i]); // 选择装第i个物品
        }
    }


    @Test
    public void knapsackTest() {

        int result = knapsack(weight, weight.length, 9);

        System.out.println(result);


    }

    /**
     * weight:物品重量，n:物品个数，w:背包可承载重量 , n 个 物品 只能 按 顺序 决定 是否 放入, 不是 每次 决定 放入 哪个 ，不是 组合
     *
     * @param weight
     * @param n
     * @param w
     * @return
     */
    public int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1]; // 默认值false
        states[0][0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划状态转移 ， 控制 n 个 物品 放入
            //  两条 动态 规划 路线      ,查 动态规划状态 转移 表  ， 依据 上次 转移 状态 决定 本次 转移 结果
            for (int j = 0; j <= w; ++j) {
                // 不把第i个物品放入背包 // 由于 我们 不知道 第 i个 物品 的 重量 ， 所以 要使用 循环  判断 第 i个 物品的 重量
                if (states[i - 1][j] == true) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= w - weight[i]; ++j) {//把第i个物品放入背包
                if (states[i - 1][j] == true) states[i][j + weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果,  状态转移表 最后 一行，最后一行 表示 当 物品 全部 放入 之后 的 总重量
            if (states[n - 1][i] == true) return i;
        }
        return 0;
    }


    @Test
    public void knapsack2Test() {

        int result = knapsack2(weight, weight.length, 9);

        System.out.println(result);


    }

    /**
     * 将 问题 转成 了 组合 模式， 外层 for 循环 控制 要 从 集合 中 选择 的 个数 ，内存 for 循环 控制 放入 哪一个 元素 。
     * @param items
     * @param n
     * @param w
     * @return
     */
    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1]; // 默认值false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w - items[i]; j >= 0; --j) {//把第i个物品放入背包
                if (states[j] == true) states[j + items[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) return i;
        }
        return 0;
    }


    public static int knapsack3(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w + 1];
        for (int i = 0; i < n; ++i) { // 初始化states
            for (int j = 0; j < w + 1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weight[0] <= w) {
            states[0][weight[0]] = value[0];
        }
        for (int i = 1; i < n; ++i) { //动态规划，状态转移
            for (int j = 0; j <= w; ++j) { // 不选择第i个物品
                if (states[i - 1][j] >= 0) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= w - weight[i]; ++j) { // 选择第i个物品
                if (states[i - 1][j] >= 0) {
                    int v = states[i - 1][j] + value[i];
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }
        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n - 1][j] > maxvalue) maxvalue = states[n - 1][j];
        }
        return maxvalue;
    }


    @Test
    public void minDistBTTest() {
        int[][] w = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
        int dist = 0;
        minDistBT(0, 0, dist, w, 3);
        System.out.println(dist);
    }

    private int minDist = Integer.MAX_VALUE; // 全局变量或者成员变量

    /**
     *  回溯 算法 倒退
     * @param i
     * @param j
     * @param dist
     * @param w
     * @param n
     */
    // 调用方式：minDistBacktracing(0, 0, 0, w, n);
    public void minDistBT(int i, int j, int dist, int[][] w, int n) {
        // 到达了n-1, n-1这个位置了，这里看着有点奇怪哈，你自己举个例子看下
        if (i == n && j == n) {
            if (dist < minDist) minDist = dist;
            return;
        }
        if (i < n) { // 往下走，更新i=i+1, j=j
            minDistBT(i + 1, j, dist + w[i][j], w, n);
        }
        if (j < n) { // 往右走，更新i=i, j=j+1
            minDistBT(i, j + 1, dist + w[i][j], w, n);
        }
    }

    /**
     * 状态 转移 矩阵
     * @param matrix
     * @param n
     * @return
     */
    //最短 路径
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

    private int[][] matrix =
            {{1, 3, 5, 9
            }, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
    //    private int n=4;
    private int[][] mem2 = new int[4][4];

    public int minDist(int i, int j) { // 调用minDist(n-1, n-1);
        if (i == 0 && j == 0) return matrix[0][0];
        if (mem2[i][j] > 0) return mem2[i][j];
        int minLeft = Integer.MAX_VALUE;
        if (j - 1 >= 0) {
            minLeft = minDist(i, j - 1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            minUp = minDist(i - 1, j);
        }

        int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
        mem2[i][j] = currMinDist;
        return currMinDist;
    }


    @Test
    public void lwstBT() {

        lwstBT(0, 0, 0);
    }


    private char[] a = "mitcmu".toCharArray();
    private char[] b = "mtacnu".toCharArray();
    private int n2 = 6;
    private int m2 = 6;
    private int minDist2 = Integer.MAX_VALUE; // 存储结果

    // 调用方式 lwstBT(0, 0, 0);
    public void lwstBT(int i, int j, int edist) {
        if (i == n2 || j == m2) {
            if (i < n2) edist += (n2 - i);
            if (j < m2) edist += (m2 - j);
            if (edist < minDist2) minDist2 = edist;
            return;
        }
        if (a[i] == b[j]) { // 两个字符匹配
            lwstBT(i + 1, j + 1, edist);
        } else { // 两个字符不匹配
            lwstBT(i + 1, j, edist + 1); // 删除a[i]或者b[j]前添加一个字符
            lwstBT(i, j + 1, edist + 1); // 删除b[j]或者a[i]前添加一个字符
            lwstBT(i + 1, j + 1, edist + 1); // 将a[i]和b[j]替换为相同字符
        }
    }


    @Test
    public void minTest() {

        int result = min(0, 0, 0);
        System.out.println(result);
    }


    public int lwstDP(char[] a, int n, char[] b, int m) {
        int[][] minDist = new int[n][m];
        for (int j = 0; j < m; ++j) { // 初始化第0行:a[0..0]与b[0..j]的编辑距离
            if (a[0] == b[j]) minDist[0][j] = j;
            else if (j != 0) minDist[0][j] = minDist[0][j - 1] + 1;
            else minDist[0][j] = 1;
        }
        for (int i = 0; i < n; ++i) { // 初始化第0列:a[0..i]与b[0..0]的编辑距离
            if (a[i] == b[0]) minDist[i][0] = i;
            else if (i != 0) minDist[i][0] = minDist[i - 1][0] + 1;
            else minDist[i][0] = 1;
        }
        for (int i = 1; i < n; ++i) { // 按行填表
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]) minDist[i][j] = min(
                        minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1]);
                else minDist[i][j] = min(
                        minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1] + 1);
            }
        }
        return minDist[n - 1][m - 1];
    }

    private int min(int x, int y, int z) {
        int minv = Integer.MAX_VALUE;
        if (x < minv) minv = x;
        if (y < minv) minv = y;
        if (z < minv) minv = z;
        return minv;
    }


}
