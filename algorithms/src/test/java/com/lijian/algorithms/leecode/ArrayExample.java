package com.lijian.algorithms.leecode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayExample {

    @Test
    public void plusOneTest() {

        int[] result = plusOne2(new int[]{1, 2, 9});
        IntStream.of(result).boxed().forEach(x -> System.out.println(x));
    }

    //66 加一
    public int[] plusOne(int[] digits) {
        if (digits.length == 0) {
            return new int[0];

        }
        int i = digits.length - 1;
        if (digits[i] != 9) {
            digits[i] = digits[i] + 1;
            return digits;
        }
        if (digits[i] == 9) {
            digits[i] = 0;
            if (i - 1 < 0) {
                int[] temp = new int[digits.length + 1];
                System.arraycopy(digits, 0, temp, 1, digits.length);
                temp[0] = 1;
                temp[1] = 0;
                return temp;
            } else {
                digits[i - 1] = digits[i - 1] + 1;
            }

            i--;
        }

        while (digits[i] == 10) {
            digits[i] = 0;
            if (i - 1 < 0) {
                int[] temp = new int[digits.length + 1];
                System.arraycopy(digits, 0, temp, 1, digits.length);
                temp[0] = 1;
                temp[1] = 0;
                return temp;
            }
            digits[i - 1] = digits[i - 1] + 1;
            i--;
        }

        return digits;
    }


    public int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }


    @Test
    public void setZerosTest() {

        setZeroes(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}
        });
    }

    // 73. 矩阵 置零
    public void setZeroes(int[][] matrix) {
        int[][] temp = new int[matrix.length][matrix[0].length];

        System.arraycopy(matrix, 0, temp, 0, matrix.length);
        // 为什么 修改 matrix , temp  也会 发生 变化
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                if (temp[i][j] == 0) {
                    for (int k = 0; k < matrix.length; k++) {
                        matrix[i][k] = 0;
                    }
                    for (int k = 0; k < matrix[0].length; k++) {
                        matrix[k][j] = 0;
                    }
                    break;
                }
            }
        }
    }

    @Test
    public void sortColorsTest() {

        sortColors(new int[]{1, 2, 0, 2, 1, 1, 0});
    }

    /*
    Edsger W. Dijkstra
  荷兰三色旗问题解
  */
    public void sortColors(int[] nums) {
        // 对于所有 idx < i : nums[idx < i] = 0
        // j是当前考虑元素的下标
        int p0 = 0, curr = 0;
        // 对于所有 idx > k : nums[idx > k] = 2
        int p2 = nums.length - 1;
        int tmp;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                // 交换第 p0个和第curr个元素
                // i++，j++
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            } else if (nums[curr] == 2) {
                // 交换第k个和第curr个元素
                // p2--
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = tmp;
            } else curr++;
        }
    }

    @Test
    public void generateTest() {

        List<List<Integer>> result = generate(5);
        System.out.println(result);
    }

    //118. 杨辉三角
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0; i < numRows; i++) {

            list.add(new ArrayList<>());
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    list.get(i).add(j, 1);
                } else if (j == i) {
                    list.get(i).add(j, 1);
                } else {
                    list.get(i).add(j, list.get(i - 1).get(j - 1) + list.get(i - 1).get(j));
                }
            }
        }
        return list;

    }

    @Test
    public void getRowTest() {

        List<Integer> result = getRow(5);
        System.out.println(result);
    }

    public List<Integer> getRow(int rowIndex) {
        return generate(rowIndex + 1).get(rowIndex);
    }


    @Test
    public void solveTest() {

        solve(new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}});
    }

//130. 被围绕的区域

    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 从边缘o开始搜索
                boolean isEdge = i == 0 || j == 0 || i == m - 1 || j == n - 1;
                if (isEdge && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#') {
            // board[i][j] == '#' 说明已经搜索过了.
            return;
        }
        board[i][j] = '#';
        dfs(board, i - 1, j); // 上
        dfs(board, i + 1, j); // 下
        dfs(board, i, j - 1); // 左
        dfs(board, i, j + 1); // 右
    }




        public class Pos{
            int i;
            int j;
            Pos(int i, int j) {
                this.i = i;
                this.j = j;
            }
        }
        public void solve2(char[][] board) {
            if (board == null || board.length == 0) return;
            int m = board.length;
            int n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 从边缘第一个是o的开始搜索
                    boolean isEdge = i == 0 || j == 0 || i == m - 1 || j == n - 1;
                    if (isEdge && board[i][j] == 'O') {
                        dfs2(board, i, j);
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                    if (board[i][j] == '#') {
                        board[i][j] = 'O';
                    }
                }
            }
        }

        public void dfs2(char[][] board, int i, int j) {
            Stack<Pos> stack = new Stack<>();
            stack.push(new Pos(i, j));
            board[i][j] = '#';
            while (!stack.isEmpty()) {
                // 取出当前stack 顶, 不弹出.
                Pos current = stack.peek();
                // 上
                if (current.i - 1 >= 0
                        && board[current.i - 1][current.j] == 'O') {
                    stack.push(new Pos(current.i - 1, current.j));
                    board[current.i - 1][current.j] = '#';
                    continue;
                }
                // 下
                if (current.i + 1 <= board.length - 1
                        && board[current.i + 1][current.j] == 'O') {
                    stack.push(new Pos(current.i + 1, current.j));
                    board[current.i + 1][current.j] = '#';
                    continue;
                }
                // 左
                if (current.j - 1 >= 0
                        && board[current.i][current.j - 1] == 'O') {
                    stack.push(new Pos(current.i, current.j - 1));
                    board[current.i][current.j - 1] = '#';
                    continue;
                }
                // 右
                if (current.j + 1 <= board[0].length - 1
                        && board[current.i][current.j + 1] == 'O') {
                    stack.push(new Pos(current.i, current.j + 1));
                    board[current.i][current.j + 1] = '#';
                    continue;
                }
                // 如果上下左右都搜索不到,本次搜索结束，弹出stack
                stack.pop();
            }
        }
    class UnionFind {
        int[] parents;

        public UnionFind(int totalNodes) {
            parents = new int[totalNodes];
            for (int i = 0; i < totalNodes; i++) {
                parents[i] = i;
            }
        }
        // 合并连通区域是通过find来操作的, 即看这两个节点是不是在一个连通区域内.
        void union(int node1, int node2) {
            int root1 = find(node1);
            int root2 = find(node2);
            if (root1 != root2) {
                parents[root2] = root1;
            }
        }

        int find(int node) {
            while (parents[node] != node) {
                // 当前节点的父节点 指向父节点的父节点.
                // 保证一个连通区域最终的parents只有一个.
                parents[node] = parents[parents[node]];
                node = parents[node];
            }

            return node;
        }

        boolean isConnected(int node1, int node2) {
            return find(node1) == find(node2);
        }
    }


    @Test
    public void testMemory() {
        String[] kk = new String[1024 * 1024*512 ];
        System.out.println(kk.length);

    }

}


