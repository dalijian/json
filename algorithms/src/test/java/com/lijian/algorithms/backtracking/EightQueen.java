package com.lijian.algorithms.backtracking;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.abs;

// 利用 回溯 算法 解决 八皇后问题
public class EightQueen {

    Logger logger = LoggerFactory.getLogger(EightQueen.class);


    int[] result = new int[8];//全局或成员变量,下标表示行,值表示queen存储在哪一列

    @Test
    public void testQueen() {
        cal8queens(0);

    }

    public void cal8queens(int row) { // 调用方式：cal8queens(0);
        if (row == 8) { // 8个棋子都放置好了，打印结果
            printQueens(result);
            return; // 8行棋子都放好了，已经没法再往下递归了，所以就return
        }
        for (int column = 0; column < 8; ++column) { // 每一行都有8中放法
            if (isOk(row, column)) { // 有些放法不满足要求
                result[row] = column; // 第row行的棋子放到了column列
                cal8queens(row + 1); // 考察下一行
            }
        }
    }

    private boolean isOk(int row, int column) {//判断row行column列放置是否合适
        int leftup = column - 1, rightup = column + 1;
        for (int i = row - 1; i >= 0; --i) { // 逐行往上考察每一行
            if (result[i] == column) return false; // 第i行的column列有棋子吗？
            if (leftup >= 0) { // 考察左上对角线：第i行leftup列有棋子吗？
                if (result[i] == leftup) return false;
            }
            if (rightup < 8) { // 考察右上对角线：第i行rightup列有棋子吗？
                if (result[i] == rightup) return false;
            }
            --leftup;
            ++rightup;
        }
        return true;
    }

    private void printQueens(int[] result) { // 打印出一个二维矩阵
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }


    @Test
    public void testF() {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        f(0, 0, a, 10, 100);
    }

    //    1:0 背包 问题
    public int maxW = Integer.MIN_VALUE; //存储背包中物品总重量的最大值

    // cw表示当前已经装进去的物品的重量和；i表示考察到哪个物品了；
// w背包重量；items表示每个物品的重量；n表示物品个数
// 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数：
// f(0, 0, a, 10, 100)
    public void f(int i, int cw, int[] items, int n, int w) {
        if (cw == w || i == n) { // cw==w表示装满了;i==n表示已经考察完所有的物品
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i + 1, cw, items, n, w);
        if (cw + items[i] <= w) {// 已经超过可以背包承受的重量的时候，就不要再装了
            f(i + 1, cw + items[i], items, n, w);
        }
    }


//    回溯法指导思想——走不通，就掉头。设计过程：确定问题的解空间；确定结点的扩展规则；搜索。
//    问题的解空间：(x1,x2,x3,x4,x5,x6,x7,x8)，1≤xi≤8(i=1，2，3，4……，8)，共88个状态；
//    约束条件：八个(1,x1),(2,x2) ,(3,x3),(4,x4) ,(5,x5), (6,x6) , (7,x7), (8,x8)不在同一行、同一列和同一对角线上。
//    1. 同一行 ，由于 人工 分行 所以不可能 在 同一行
//    2. 同一列 即 xi  不相等
//    3. 同一对角线 如果 在 同一 对角线 那么 以 两个 皇后  未 顶点 组成的 四边形 是 正方形 ，即 queen(x1,y1),queen(x2,y2) abs(x1-x1)=abs(y1-y2)


    // 暴力循环法
    @Test
    public void force() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {
                        for (int m = 0; m < 8; m++) {
                            for (int n = 0; n < 8; n++) {
                                for (int o = 0; o < 8; o++) {
                                    for (int p = 0; p < 8; p++) {
                                        Map<Integer, Long> map = Stream.of(i, j, k, l, m, n, o, p).collect(Collectors.toMap(x -> x, x -> 0L, (k1, k2) -> k1));
                                        if (map.size() == 8) {
                                            Integer[] array = map.keySet().toArray(new Integer[0]);
                                            for (int q = 0; q < array.length; q++) {
                                                for (int r = q + 1; r < array.length; r++) {
                                                    if (abs(array[q] - array[r]) != abs(q - r)) {


                                                        logger.info("i:{}," +
                                                                "j:{}," +
                                                                "k:{}," +
                                                                "l:{}," +
                                                                "m:{}," +
                                                                "n:{}," +
                                                                "o:{}," +
                                                                "p:{}", i, j, k, l, m, n, o, p);
                                                        System.out.println("*************");


                                                    }

                                                }
                                            }


                                        }
                                    }

                                }

                            }

                        }

                    }

                }

            }

        }
    }

    public boolean check(int a[], int n) {

        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (Math.abs(a[i] - a[j]) == abs(i - j) || a[i] == a[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean check_2(int a[], int n) {//多次被调用，只需一重循环
        for (int i = 1; i <= n - 1; i++) {
            if ((abs(a[i] - a[n]) == n - i) || (a[i] == a[n]))
                return false;
        }
        return true;
    }

//    //   n 皇后 循环 写法
//    void backdate(int n) {
//        int count = 0;
//        int[] a = new int[100];
//
//        int k = 1;
//        a[1] = 0;
//        while (k > 0) {
//            a[k] = a[k] + 1;//对应for循环的1~n
//            while ((a[k] <= n) && (!check_2(a, k)))//搜索第k个皇后位置
//            {
//                a[k] = a[k] + 1;
//            }
//
//            if (a[k] <= n)//找到了合理的位置
//            {
//                if (k == n) {//找到一组解
//                    for (int i = 1; i <= 8; i++) {
//                        cout << a[i];
//                    }
//                    cout << endl;
//                    count++;
//                } else {
//                    k = k + 1;//继续为第k+1个皇后找到位置，对应下一级for循环
//                    a[k] = 0;//下一个皇后一定要从头开始搜索
//                }
//            } else {
//                k = k - 1;//回溯,对应执行外内层for循环回到更上层
//            }
//        }
//        cout << count << endl;
//    }
//
//    void main() {
//        backdate(8);
//    }


//    @Test
//    public void eightQueenRecur() {
//
//
//        n = 8, count = 0;
//        backtrack(1);
//        cout << count << endl;
//    }
//
//    void backtrack(int k) {
//        if (k > n)//找到解
//        {
//            for (int i = 1; i <= 8; i++) {
//                cout << a[i];
//            }
//            cout << endl;
//            count++;
//        } else {
//            for (int i = 1; i <= n; i++) {
//                a[k] = i;
//                if (check_2(a, k) == 1) {
//                    backtrack(k + 1);
//                }
//            }
//        }
//
//    }


    @Test
    public void queen8Test() {

        int[] queenList = new int[8];

        addQueen(0, queenList);

        for (int i = 0; i < queenList.length; i++) {
            System.out.println(queenList[i]);
        }
        System.out.println("count:"+count);
    }
    private int count   =0;

    public void addQueen(int i, int[] queenList) {

        if (i == 8) { // 8个棋子都放置好了，打印结果
            printQueens(queenList);
            return; // 8行棋子都放好了，已经没法再往下递归了，所以就return
        }

        for (int j = 0; j < 8; j++) {
            count++;
            if (checkQueenExist(i, j, queenList)) {
                queenList[i] = j;
                addQueen(i + 1, queenList);
            }

        }


    }

    private boolean checkQueenExist(int i, int j, int[] queenList) {
        if (i == 0) {
            return true;
        }
        for (int k = 0; k < i; k++) {   // 默认 i 是 递增的 ，即 行 是按 顺序 排放的
            if (Math.abs(k - i) == Math.abs(queenList[k] - j) ||
                    Math.abs(k - i) == 0 ||
                    Math.abs(queenList[k] - j) == 0) {
                return false;
            }
        }
        return true;
    }

     // 循环 嵌套 递归   不等于 循环 + 循环
    @Test
    public void queen8Test2(){

        List<int []> queenList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkQueenExist(i, j,queenList)) {
                    queenList.add(new int[]{i, j});
                    break;
                }
            }
        }
        for (int i = 0; i < queenList.size(); i++) {
            System.out.println(queenList.get(i));
        }
    }

    private boolean checkQueenExist(int i, int j, List<int[]> queenList) {
        if (queenList.isEmpty()) {
            return true;
        }
        for (int k = 0; k < queenList.size(); k++) {
            int[] temp = queenList.get(k);
            if (Math.abs(temp[0] - i) == Math.abs(temp[1] - j)||
                    Math.abs(temp[0] - i)==0||
                    Math.abs(temp[1] - j)==0) {
                return false;
            }
        }
        return true;


    }
}

