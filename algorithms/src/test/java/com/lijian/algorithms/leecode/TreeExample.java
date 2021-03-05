package com.lijian.algorithms.leecode;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class TreeExample {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    @Test
    public void inorderTraversalTest() {
//    1,null,2,3
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(2);
        treeNode.right.left = new TreeNode(3);
        List<Integer> result = inorderTraversal(treeNode);
        System.out.println(result);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        inOrderTraverse(root, list);
        return list;
    }

    // 树的 中序 遍历 先左子树  后 根节点   再 右子树
    private void inOrderTraverse(TreeNode root, List list) {
        if (root == null) {
            return;
        }

        inOrderTraverse(root.left, list);

        list.add(root.val);

        inOrderTraverse(root.right, list);


    }

    private void inOrderTraverseRe(TreeNode root, List list) {
        if (root == null) {
            return;
        }
        Stack stack = new Stack();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                TreeNode p = (TreeNode) stack.pop();
                list.add(p);
                root = p.right;
            }
        }
    }

    public List<TreeNode> generateTrees2(int n) {

        List<TreeNode> list = new ArrayList<>();
        TreeNode treeNode = new TreeNode(1);
        generateTreesRe(list, n, 0, treeNode);
        return list;
    }

    private void generateTreesRe(List<TreeNode> list, int n, int i, TreeNode treeNode) {


        if (i == n) {
            list.add(treeNode);
            return;
        }

        for (int j = 0; j < 3; j++) {

            switch (j) {
                case 0:
            }

        }
    }


    public LinkedList<TreeNode> generate_trees(int start, int end) {
        LinkedList<TreeNode> all_trees = new LinkedList<TreeNode>();
        if (start > end) {                                              // 终止条件，左子树选择的节点+ 右子树选择的节点 正好 等于 全部节点
            all_trees.add(null);
            return all_trees;
        }

        // pick up a root
        for (int i = start; i <= end; i++) {
            // all possible left subtrees if i is choosen to be a root
            LinkedList<TreeNode> left_trees = generate_trees(start, i - 1);  // 左子樹 可選 範圍 start~ i-1

            // all possible right subtrees if i is choosen to be a root
            LinkedList<TreeNode> right_trees = generate_trees(i + 1, end);      // 右子樹 可選 範圍 i+1 ~ end

            // connect left and right trees to the root i
            for (TreeNode l : left_trees) {
                for (TreeNode r : right_trees) {
                    TreeNode current_tree = new TreeNode(i);
                    current_tree.left = l;
                    current_tree.right = r;
                    all_trees.add(current_tree);
                }
            }
        }
        return all_trees;
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generate_trees(1, n);
    }

    //类似 于 归并 排序
    @Test
    public void generateTreesTest() {
        List<TreeNode> result = generateTrees(4);
        System.out.println(result);
    }

    //96. 不同的二叉搜索树
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

//98. 验证二叉搜索树

//    节点的左子树只包含小于当前节点的数。
//    节点的右子树只包含大于当前节点的数。
//    所有左子树和右子树自身必须也是二叉搜索树。


    @Test
    public void isValidBSTTest() {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(6);
        treeNode.right.left = new TreeNode(4);
        treeNode.right.right = new TreeNode(7);
        boolean result = isValidBST3(treeNode);
        System.out.println(result);
    }

//    public boolean isValidBST(TreeNode root) {
//
//        if (root == null) {
//            return true;
//        }
//        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
//        treeNodeQueue.offer(root);
//        while (!treeNodeQueue.isEmpty()) {
//
//            TreeNode treeNode = treeNodeQueue.poll();
//
//            if (treeNode.right != null && treeNode.right.val > treeNode.val) {
//
//                treeNodeQueue.offer(treeNode.right);
//            } else if (treeNode.right != null && (treeNode.right.val <= treeNode.val||treeNode.right.val<root.val)) {
//                return false;
//            }
//
//            if (treeNode.left != null && treeNode.left.val < treeNode.val) {
//                treeNodeQueue.offer(treeNode.left);
//            } else if (treeNode.left != null && (treeNode.left.val >=treeNode.val||treeNode.left.val>root.val)) {
//                return false;
//            }
//        }
//
//        return true;
//    }


    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (!helper(node.right, val, upper)) return false;
        if (!helper(node.left, lower, val)) return false;
        return true;
    }

    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }


    // 迭代算法
    LinkedList<TreeNode> stack = new LinkedList();
    LinkedList<Integer> uppers = new LinkedList(),
            lowers = new LinkedList();

    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }

    public boolean isValidBST2(TreeNode root) {
        Integer lower = null, upper = null, val;
        update(root, lower, upper);
        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();

            if (root == null) continue;
            val = root.val;
            if (lower != null && val <= lower) return false;
            if (upper != null && val >= upper) return false;
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }


    public boolean isValidBST3(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }


    //100. 相同的树

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // p and q are both null
        if (p == null && q == null) return true;
        // one of p and q is null
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.right, q.right) &&
                isSameTree(p.left, q.left);
    }

    //102. 二叉树的层序遍历
    @Test
    public void levelOrderTest() {
//[1,2,3,4,null,null,5]
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(4);
        treeNode.right = new TreeNode(3);
        treeNode.right.right = new TreeNode(5);
        List<List<Integer>> result = levelOrder(treeNode);
        System.out.println(result);
    }


    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> list = new ArrayList<>();

        levelOrderTraverse(root, list);
//      list =  levelOrder22(root);
        return list;
    }

    /***
     * 安 层 遍历   使用 queue 队列
     */

    private void levelOrderTraverse(TreeNode root, List<List<Integer>> list) {

        if (root == null) {
            return;
        }
        Queue q = new LinkedList();
        // 根节点 入队
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> levelTree = new ArrayList<>();
            int currentLevelSize = q.size();
            for (int i = 1; i <= currentLevelSize; i++) {
                // 取出 队首 节点 p 并 访问
                // 如何 确保 同一层 放在 一个 list 中
                TreeNode p = (TreeNode) q.poll();
                levelTree.add(p.val);
                if (p.left != null) {
                    // 将 p 的 非空 左右 孩子 依次 入 队
                    q.offer(p.left);
                }
                if (p.right != null) {
                    q.offer(p.right);
                }
            }
            list.add(levelTree);
        }
    }


    public List<List<Integer>> levelOrder22(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }
        return ret;
    }

    // 使用 数组 存放 树 ，计算 出 每层 数据
    // 每层 有 Math.pow(2,i) 个 数
    @Test
    public void levelOrderTest2() {
        //[1,2,3,4,null,null,5]
        List<List<Integer>> content = new ArrayList<>();
        int[] array = new int[]{1, 2, 3, 4, 6, 7, 5};
        for (int i = 0; Math.pow(2, i) <= array.length; i++) {
            {
                List<Integer> list = new ArrayList<>();
                for (int j = new Double(Math.pow(2, i)).intValue(); j < new Double(Math.pow(2, i + 1)).intValue(); j++) {
                    list.add(array[j - 1]);
                }
                content.add(list);
            }
        }
        System.out.println(content);
    }

    public void recoverTree(TreeNode root) {

    }
//101. 对称二叉树


    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }


    // 按层 遍历 改版
    public boolean isSymmetric3(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }


    @Test
    public void maxDepthTest() {

        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);
        int result = maxDepth(treeNode);
        System.out.println(result);
    }

    //104. 二叉树的最大深度
    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    @Test
    public void zigzagLevelOrderTest() {

        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);
        List<List<Integer>> result = zigzagLevelOrder(treeNode);

    }

    //103. 二叉树的锯齿形层次遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        levelOrderTraverse2(root, list);
        System.out.println(list);
        return list;
    }

    //    即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行
    private void levelOrderTraverse2(TreeNode root, List linkedList) {
        if (root == null) {
            return;
        }
        int num = 0;
        Queue q = new LinkedList();
        // 根节点 入队
        q.offer(root);
        while (!q.isEmpty()) {
            // 取出 队首 节点 p 并 访问
            TreeNode p = (TreeNode) q.poll();
            linkedList.add(p.val);
            num++;
            if (num % 2 == 0) {
                if (p.right != null) {
                    q.offer(p.right);
                }
                if (p.left != null) {
                    // 将 p 的 非空 左右 孩子 依次 入 队
                    q.offer(p.left);
                }
            }
            if (num % 2 == 1) {
                if (p.right != null) {
                    q.offer(p.right);
                }
                if (p.left != null) {
                    // 将 p 的 非空 左右 孩子 依次 入 队
                    q.offer(p.left);
                }

            }

        }
    }

    @Test
    public void buildTreeTest() {

        TreeNode result = buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(result);
    }

    // start from first preorder element
    int pre_idx = 0;
    int[] preorder;
    int[] inorder;
    Map<Integer, Integer> idx_map = new LinkedHashMap<>();

    public TreeNode helper(int in_left, int in_right) {
        // if there is no elements to construct subtrees
        if (in_left == in_right)
            return null;

        // pick up pre_idx element as a root
        int root_val = preorder[pre_idx];
        TreeNode root = new TreeNode(root_val);

        // root splits inorder list
        // into left and right subtrees
        int index = idx_map.get(root_val);

        // recursion
        pre_idx++;
        // build left subtree
        root.left = helper(in_left, index); // 处理 左 子树
        // build right subtree
        root.right = helper(index + 1, in_right); // 处理 右 子树
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;

        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder)
            idx_map.put(val, idx++);
        return helper(0, inorder.length);
    }


    //106. 从中序与后序遍历序列构造二叉树
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        this.preorder = postorder;
        this.inorder = inorder;
        pre_idx = postorder.length - 1;
        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder)
            idx_map.put(val, idx++);
        return helper2(0, inorder.length - 1);
    }


    public TreeNode helper2(int in_left, int in_right) {
        // if there is no elements to construct subtrees
        if (in_left == in_right)
            return null;

        // pick up pre_idx element as a root
        int root_val = preorder[pre_idx];
        TreeNode root = new TreeNode(root_val);

        // root splits inorder list
        // into left and right subtrees
        int index = idx_map.get(root_val);

        // recursion
        pre_idx--;
        // build right subtree
        root.right = helper(index + 1, in_right); // 处理 右 子树
        // build left subtree
        root.left = helper(in_left, index - 1); // 处理 左 子树

        return root;
    }

    @Test
    public void levelOrderBottomTest() {

        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);

        List<List<Integer>> result = levelOrder2(treeNode);
        System.out.println(result);

    }

    //107. 二叉树的层次遍历 II
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if (root == null) return levels;

        List<List<Integer>> list = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        ((LinkedList<TreeNode>) queue).push(root);
        List<Integer> list2 = new ArrayList<>();
        list2.add(root.val);
        list.add(list2);
        while (!queue.isEmpty()) {
            List list1 = new LinkedList<>();
            for (int i = 0; i < queue.size(); i++) {
                TreeNode node2 = queue.poll();

                list1.add(node2.val);
                if (node2.left != null) {
                    ((LinkedList<TreeNode>) queue).push(node2.left);
                }
                if (node2.right != null) {
                    ((LinkedList<TreeNode>) queue).push(node2.right);
                }
            }
            if (!list1.isEmpty()) {
                ((LinkedList<List<Integer>>) list).addFirst(list1);
            }
        }
        return list;
    }

    // 安层遍历
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> levels = new LinkedList<>();
        if (root == null) return levels;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            // start the current level
            ((LinkedList<List<Integer>>) levels).addFirst(new ArrayList<Integer>());

            // number of elements in the current level
            int level_length = queue.size();
            for (int i = 0; i < level_length; ++i) {
                TreeNode node = queue.remove();

                // fulfill the current level
                levels.get(0).add(node.val);

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            // go to next level
            level++;
        }
        return levels;
    }


    public void helper(TreeNode node, int level, List<List<Integer>> levels) {
        // start the current level
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());

        // fulfil the current level
        levels.get(level).add(node.val);

        // process child nodes for the next level
        if (node.left != null)
            helper(node.left, level + 1, levels);
        if (node.right != null)
            helper(node.right, level + 1, levels);
    }


    @Test
    public void levelOrder21Test() {


    }


    public List<List<Integer>> levelOrder21(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        if (root == null) return list;

        helper(root, 0, list);
        return list;
    }

//108. 将有序数组转换为二叉搜索树
    // 生产平衡二叉树

    @Test
    public void sortedArrayToBSTTeset() {

        TreeNode result = sortedArrayToBST2(new int[]{-10, -3, 0, 5, 9});
    }

    public TreeNode sortedArrayToBST2(int[] nums) {
        TreeNode result = mergeNode(0, nums.length - 1, nums);
        return result;
    }

    private TreeNode mergeNode(int left, int right, int[] nums) {
        if (left > right) {
            return null;
        }
        int middle = (left + right) / 2;
        TreeNode treeNode = new TreeNode(nums[middle]);
        treeNode.left = mergeNode(left, middle - 1, nums);
        treeNode.right = mergeNode(middle + 1, right, nums);
        return treeNode;

    }

//    int[] nums;
//
//    public TreeNode helper(int left, int right) {
//        if (left > right) return null;
//
//        // always choose left middle node as a root
//        int p = (left + right) / 2;
//
//        // inorder traversal: left -> node -> right
//        TreeNode root = new TreeNode(nums[p]);
//        root.left = helper(left, p - 1);
//        root.right = helper(p + 1, right);
//        return root;
//    }
//
//    public TreeNode sortedArrayToBST(int[] nums) {
//        this.nums = nums;
//        return helper(0, nums.length - 1);
//    }

    //109. 有序链表转换二叉搜索树

    @Test
    public void sortedListToBSTTest() {
//        -10,-3,0,5,9
        ListNode list = new ListNode(-10);
        list.next = new ListNode(-3);
        list.next.next = new ListNode(0);
        list.next.next.next = new ListNode(5);
        list.next.next.next.next = new ListNode(9);
        sortedListToBST(list);
    }

    public TreeNode sortedListToBST2(ListNode head) {

        return mergeNode2(head, head);
    }


    private TreeNode mergeNode2(ListNode left, ListNode head) {

        ListNode right = left;
        ListNode preNode = left;
        while (left != null || right != null) {
            if (right.next != null) {
                right = right.next.next;
            } else {
                break;
            }
            left = left.next;
            preNode = left;
        }
        if (preNode != null) {
            preNode.next = null;
        }

        TreeNode treeNode = new TreeNode(left.val);
        treeNode.left = mergeNode2(left, head);
        treeNode.right = mergeNode2(left.next, head);
        return treeNode;

    }


    private ListNode findMiddleElement(ListNode head) {

        // The pointer used to disconnect the left half from the mid node.
        ListNode prevPtr = null;
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        // Iterate until fastPr doesn't reach the end of the linked list.
        while (fastPtr != null && fastPtr.next != null) {
            prevPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        // Handling the case when slowPtr was equal to head.
        if (prevPtr != null) {
            prevPtr.next = null;
        }

        return slowPtr;
    }

    public TreeNode sortedListToBST(ListNode head) {

        // If the head doesn't exist, then the linked list is empty
        if (head == null) {
            return null;
        }

        // Find the middle element for the list.
        ListNode mid = this.findMiddleElement(head);

        // The mid becomes the root of the BST.
        TreeNode node = new TreeNode(mid.val);

        // Base case when there is just one element in the linked list
        if (head == mid) {
            return node;
        }

        // Recursively form balanced BSTs using the left and right halves of the original list.
        node.left = this.sortedListToBST(head);
        node.right = this.sortedListToBST(mid.next);
        return node;
    }


    //110. 平衡二叉树
    // 每个 节点 的 左右 子树 高度 相差 不超过 1
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return (Math.abs(height(root.right) - height(root.left)) < 1 && isBalanced(root.right) && isBalanced(root.left));

    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.right), height(node.left)) + 1;
    }

    //111. 二叉树的最小深度
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.min(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

//112. 路径总和
//    判断该树中是否存在根节点到叶子节点的路径，这条
// 路径上所有节点值相加等于目标和

    @Test
    public void hasPathSumTest() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
//        treeNode.right = new TreeNode(20);
//        treeNode.right.left = new TreeNode(15);
//        treeNode.right.right = new TreeNode(7);
        boolean result = hasPathSum(null, 1);
        System.out.println(result);
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return false;
        }
        computePath(root, 0, 0, sum, list);

        return list.contains(sum);
    }

    private void computePath(TreeNode root, int i, int total, int sum, List<Integer> list) {
        if (root.left == null && root.right == null) {
            list.add(total + root.val);
            return;
        }
        if (root.left != null)
            computePath(root.left, i, total + root.val, sum, list);
        if (root.right != null)
            computePath(root.right, i, total + root.val, sum, list);


    }


    @Test
    public void pathSumTest() {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(4);
        treeNode.left.left = new TreeNode(11);
        treeNode.left.left.left = new TreeNode(7);
        treeNode.left.left.right = new TreeNode(2);
        treeNode.right = new TreeNode(8);
        treeNode.right.left = new TreeNode(13);
        treeNode.right.right = new TreeNode(4);
        treeNode.right.right.right = new TreeNode(1);
        treeNode.right.right.left = new TreeNode(5);

        List<List<Integer>> result = pathSum(treeNode, 22);
        System.out.println(result);
    }

    //113. 路径总和 II   回溯算法
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        Deque list = new LinkedList<>();
        List<List<Integer>> list2 = new LinkedList<>();
        if (root == null) {
            return Collections.emptyList();
        }
        computePath2(root, 0, sum, list, list2);

        return list2;
    }

    private void computePath2(TreeNode root, int i, int sum, Deque<Integer> list, List<List<Integer>> list2) {
        if (root.left == null && root.right == null) {

            list.offer(root.val);
            if (list.stream().mapToInt(x -> x).sum() == sum) {
                list2.add(new LinkedList<>(list));
            }
            return;
        }
        list.offer(root.val);
        if (root.left != null) {
            computePath2(root.left, i, sum, list, list2);
            list.removeLast(); //  在 另一个分支 中 递归中 将 之前分支 操作 list 添加 的 值 剪掉
        }

        if (root.right != null) {
            computePath2(root.right, i, sum, list, list2);
            list.removeLast();

        }

    }

    //114. 二叉树展开为链表， 采用 先序 遍历
    public void flatten(TreeNode root) {


    }

    public void flatten2(TreeNode root) {
        while (root != null) {
            //左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                //将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }

    @Test
    public void buildTreeTest3() {

        TreeNode result = buildTree3(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(result);
    }

    private TreeNode buildTree3(int[] preOrder, int[] inOrder) {
        Map<Integer, Integer> inOrderMap = new LinkedHashMap<>();
        for (int i = 0; i < inOrder.length; i++) {

            inOrderMap.put(inOrder[i], i);
        }
        TreeNode node = getTree(0, inOrder.length, preOrder, inOrderMap);
        return node;
    }

    private TreeNode getTree(int left, int right, int[] preOrder, Map<Integer, Integer> inOrderMap) {
        if (left < right) {

            TreeNode root = new TreeNode(preOrder[pre_idx]);
            pre_idx++;
            root.left = getTree(left, inOrderMap.get(root.val), preOrder, inOrderMap);
            root.right = getTree(inOrderMap.get(root.val) + 1, right, preOrder, inOrderMap);

            return root;
        }
        return null;
    }


//    public TreeNode helper(int in_left, int in_right) {
//        // if there is no elements to construct subtrees
//        if (in_left == in_right)
//            return null;
//
//        // pick up pre_idx element as a root
//        int root_val = preorder[pre_idx];
//        TreeNode root = new TreeNode(root_val);
//
//        // root splits inorder list
//        // into left and right subtrees
//        int index = idx_map.get(root_val);
//
//        // recursion
//        pre_idx++;
//        // build left subtree
//        root.left = helper(in_left, index); // 处理 左 子树
//        // build right subtree
//        root.right = helper(index + 1, in_right); // 处理 右 子树
//        return root;
//    }
//
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        this.preorder = preorder;
//        this.inorder = inorder;
//
//        // build a hashmap value -> its index
//        int idx = 0;
//        for (Integer val : inorder)
//            idx_map.put(val, idx++);
//        return helper(0, inorder.length);
//    }

    @Test
    public void levelOrderBottomTest2() {

        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);

        List<List<Integer>> result = new LinkedList<>();
        treeNodeQueue(0, treeNode, result);
        System.out.println(result);

    }


    // 使用 list 隐式 传递 , 使用 level 隐式 传递
    public void treeNodeRe(int level, TreeNode node, List<List<Integer>> list) {
        // 在递归 过程中 防止 过多 创建 内嵌 list
        if (list.size() == level) {
            list.add(new LinkedList<>());
        }
        list.get(level).add(node.val);

        if (node.right != null) {
            treeNodeRe(level + 1, node.right, list);
        }
        if (node.left != null) {
            treeNodeRe(level + 1, node.left, list);
        }
    }

    //    public void treeNodeQueue(int level, TreeNode node, List<List<Integer>> list) {
//
//        Queue<TreeNode> queue = new LinkedList();
//        queue.offer(node);
//        while (!queue.isEmpty()) {
//            list.add(new LinkedList<>());
//           int queue_length= queue.size();
//            // number of elements in the current level
//            for (int i = 0; i < queue_length; ++i) {
//                node = queue.poll();
//                list.get(level).add(node.val);
//                if (node.right != null) {
//                    queue.offer(node.right);
//                }
//                if (node.left != null) {
//                    queue.offer(node.left);
//                }
//            }
//            level++;
//        }
//    }
    public List<List<Integer>> treeNodeQueue(int level, TreeNode root, List<List<Integer>> levels) {
        if (root == null) return levels;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // start the current level
            levels.add(new ArrayList<Integer>());

            // number of elements in the current level
            int level_length = queue.size();
            for (int i = 0; i < level_length; ++i) {
                TreeNode node = queue.remove();

                // fulfill the current level
                levels.get(level).add(node.val);

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            // go to next level
            level++;
        }
        return levels;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }


    @Test
    public void connectTest() {

        Node treeNode = new Node(1);
        treeNode.left = new Node(2);
        treeNode.right = new Node(3);
        treeNode.left.left = new Node(4);
        treeNode.left.right = new Node(5);
        treeNode.right.left = new Node(6);
        treeNode.right.right = new Node(7);

        connect(treeNode);
    }
//    116. 填充每个节点的下一个右侧节点指针

    // 安层 遍历
    public Node connect(Node root) {
        int level = 0;
        Queue<Node> queue = new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty()) {


            int queueLength = queue.size();

            for (int i = 0; i < queueLength; i++) {

                Node node = queue.poll();

                if (i < queueLength - 1) {
                    node.next = queue.peek();
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return root;
    }

    public Node connect2(Node root) {
        return root;
    }


    //124. 二叉树中的最大路径和
    public int maxPathSum(TreeNode root) {

        return 0;
    }

    //129. 求根到叶子节点数字之和
    public int sumNumbers(TreeNode root) {
        List<List<Integer>> list2 = new LinkedList<>();
        computePath3(root, 0, 0, new LinkedList<>(), list2);
        return list2.stream().map(x -> x.stream().map(y -> String.valueOf(y)).collect(Collectors.joining()))
                .mapToInt(x -> Integer.valueOf(x)).sum();
    }

    private void computePath3(TreeNode root, int i, int sum, Deque<Integer> list, List<List<Integer>> list2) {
        if (root.left == null && root.right == null) {
            list.offer(root.val);
            return;
        }
        list.offer(root.val);
        if (root.left != null) {
            computePath3(root.left, i, sum, list, list2);
            list.removeLast(); //  在 另一个分支 中 递归中 将 之前分支 操作 list 添加 的 值 剪掉
        }

        if (root.right != null) {
            computePath3(root.right, i, sum, list, list2);
            list.removeLast();

        }

    }


    //145. 二叉树的后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        postOrderTraverse(root, list);
        return list;
    }


    // 后序 遍历 的 非 递归
    private void postOrderTraverse(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        TreeNode p = root;

        Stack s = new Stack();


        while (p != null || !s.isEmpty()) {
            // 先 左 后 右 不断 深入
            while (p != null) {
                // 将 根 节点 入栈
                s.push(p);

                if (p.left != null) {
                    p = p.left;
                } else {
                    p = p.right;
                }
            }
            if (!s.isEmpty()) {
                //取出 栈顶 根 结点 访问
                p = (TreeNode) s.pop();
                list.add(p.val);
            }

// 满足 条件 时， 说明 栈顶 根 节点 右子树 已 访问， 应 出 栈 访问 之
            while (!s.isEmpty() && ((TreeNode) s.peek()).right == p) {
                p = (TreeNode) s.pop();
                list.add(p.val);
            }
// 转 向 栈顶 根 结点 的 右子树 继续 后序 遍历
            if (!s.isEmpty()) {
                p = ((TreeNode) s.peek()).right;

            } else {
                p = null;
            }

        }


    }
}
