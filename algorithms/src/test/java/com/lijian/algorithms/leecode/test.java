package com.lijian.algorithms.leecode;

import org.junit.Test;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class test {

    @Test
    public void test1() {

        File file = new File("");

        file.length();


    }

    @Test
    public void testMap() {

        Map<Integer, String> map = new HashMap<>();

        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
        System.out.println(map);
    }

    @Test
    public void binarySearch() {

        int[] array = {1, 1, 1, 4, 4, 9, 10, 34, 45, 65, 78, 99};

        search(array, 0, array.length - 1, 65);
    }

    // if (){} else if (){}  与 if() {} if(){}   有什么 区别
    //
    private void search(int[] array, int left, int right, int target) {
        if (left <= right) {
            int middle = (left + right) / 2;
            if (array[middle] > target) {
                search(array, left, middle - 1, target);
            } else if (array[middle] < target) {
                search(array, middle + 1, right, target);
            } else {
                System.out.println(middle);
            }
        }
    }


    @Test
    public void binarySearchFirst() {

        int[] array = {1, 1, 1, 4, 4, 9, 10, 34, 45, 65, 78, 99};

//        searchFirst(array, 0, array.length - 1, 1);
        System.out.println("**********************");
        searchFirst2(array, 0, array.length - 1, 1);

    }

    // 这种 写法 不行
    // 查询 第一个 值 等于 给定 值 的 元素
    private void searchFirst(int[] array, int left, int right, int target) {
        int middle = (left + right) / 2;
        if (array[middle] > target) {
            searchFirst(array, left, middle - 1, target);
        } else if (array[middle] < target) {
            searchFirst(array, middle + 1, right, target);
        } else {
            // 当  array[middle]== target 但是 不满足  if 条件 时 没有 再次 递归 下去 ，而是 退出   function
            // 解决 方法 是  将 判断 放在  循环 前面
            // 而且 在 递归 判断 中 要将 向 左 递归 放在 最后
            if (middle == 0 || array[middle - 1] != target) {
                System.out.println(middle);
                return;
            }
        }
    }

    // 查询 第一个 值 等于 给定 值 的 元素
    private void searchFirst2(int[] array, int left, int right, int target) {
        int middle = (left + right) / 2;
        if (array[middle] == target) {
            if (middle == 0 || array[middle - 1] != target) {
                System.out.println(middle);
                return;
            }
        }
        if (array[middle] < target) {
            searchFirst2(array, middle + 1, right, target);
        } else {
            searchFirst2(array, left, middle - 1, target);
        }
    }


    @Test
    public void binarySearchLast() {

        int[] array = {1, 1, 1, 4, 4, 9, 10, 34, 45, 65, 78, 99, 99, 99};

        searchLast(array, 0, array.length - 1, 99);
    }

    // 查询 最后一个 值 等于 给定 值 的 元素
    private void searchLast(int[] array, int left, int right, int target) {
        int middle = (left + right) / 2;
        if (array[middle] == target) {
            if (middle == right || array[middle + 1] != target) {
                System.out.println(middle);
                return;
            }
        }
        if (array[middle] > target) {
            searchLast(array, left, middle - 1, target);
        } else {
            searchLast(array, middle + 1, right, target);
        }
    }


    @Test
    public void ifTest() {
        int a = 10, b = 11;

        if (a > b) {
            System.out.println("a>b");
        } else if (a < b) {
            System.out.println("a<b");
        } else {
            System.out.println("a=b");

        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    @Test
    public void inorderTraversal() {
        TreeNode node = new TreeNode(10);
        node.left = new TreeNode(5);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(6);
        node.right = new TreeNode(12);
        node.right.left = new TreeNode(11);
        node.right.right = new TreeNode(13);
        List<Integer> list = new ArrayList<>();

//        recursion(list, node);
        recursion2(list, node);
        System.out.println(list);

    }
// 中序遍历
    public void recursion(List<Integer> list, TreeNode root) {
        if (root.left == null && root.right == null) {
            list.add(root.val);
        }
        if (root.left != null) {
            recursion(list, root.left);
        }
        if (root.left != null || root.right != null) {
            list.add(root.val);
        }
        if (root.right != null) {
            recursion(list, root.right);
        }
    }
// 中序遍历 简化版
    public void recursion2(List<Integer> list, TreeNode root) {
        if (root == null) {
            return ;
        }
        recursion2(list, root.left);
        list.add(root.val);
        recursion2(list, root.right);
    }

    @Test
    public void preorderTraversalTest(){

        TreeNode node = new TreeNode(10);
        node.left = new TreeNode(5);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(6);
        node.right = new TreeNode(12);
        node.right.left = new TreeNode(11);
        node.right.right = new TreeNode(13);
        List<Integer> list = preorderTraversal(node);
        System.out.println( list);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list =new ArrayList<>();
        recursionPre(list, root);
        return list;
    }

    public void recursionPre(List<Integer> list, TreeNode root) {
        if (root == null) {
            return ;
        }
        list.add(root.val);
        recursionPre(list, root.left);

        recursionPre(list, root.right);
    }
    @Test
    public void traverseByFloatTest(){
        TreeNode node = new TreeNode(10);
        node.left = new TreeNode(5);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(6);
        node.right = new TreeNode(12);
        node.right.left = new TreeNode(11);
        node.right.right = new TreeNode(13);
        List<Integer> list = new ArrayList<>();

        traverseByFloat(list, node);
        System.out.println(list);

    }
    // 按层遍历
    public void traverseByFloat(List<Integer> list, TreeNode root) {

        //  按 层 遍历 找不到 递归 关系式
        // 只能 按 照 queue 实现


        Queue<TreeNode> queue = new LinkedList<>();

        ((LinkedList<TreeNode>) queue).add(root);

        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                ((LinkedList<TreeNode>) queue).add(node.left);
            }
            if (node.right != null) {
                ((LinkedList<TreeNode>) queue).add(node.right);
            }

        }





    }
}
