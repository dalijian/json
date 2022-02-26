package com.lijian.algorithms.tree;

import com.lijian.algorithms.Strategy;
import com.lijian.algorithms.link.Iterator;
import com.lijian.algorithms.link.LinkedList;
import com.lijian.algorithms.link.LinkedListDLNode;
import com.lijian.algorithms.link.Node;
import com.lijian.algorithms.queue.Queue;
import com.lijian.algorithms.queue.QueueArray;
import com.lijian.algorithms.stack.Stack;
import com.lijian.algorithms.stack.StackSLinked;


public class BinTreeNode implements Node {
    private BinTreeNode root;
    private Object data; //数据域
    private BinTreeNode parent; //父结点
    private BinTreeNode lChild; //左孩子
    private BinTreeNode rChild; //右孩子
    private int height; //以该结点为根的子树的高度, 只有 自身 节点时 高度 为 0
    private int size; //该结点子孙数（包括结点本身）
    private Strategy strategy;

    public BinTreeNode() {

        this(null);
    }

    public BinTreeNode(Object e) {
        data = e;
        height = 0;
        size = 1;
        parent = lChild = rChild = null;
    }

    /******Node 接口方法******/
    public Object getData() {
        return data;
    }

    public void setData(Object obj) {
        data = obj;
    }

    /******辅助方法,判断当前结点位置情况******/
//判断是否有父亲
    public boolean hasParent() {
        return parent != null;
    }

    //判断是否有左孩子
    public boolean hasLChild() {
        return lChild != null;
    }

    //判断是否有右孩子
    public boolean hasRChild() {
        return rChild != null;
    }

    //判断是否为叶子结点
    public boolean isLeaf() {
        return !hasLChild() && !hasRChild();
    }

    //判断是否为某结点的左孩子
    public boolean isLChild() {
        return (hasParent() && this == parent.lChild);
    }

    //判断是否为某结点的右孩子
    public boolean isRChild() {
        return (hasParent() && this == parent.rChild);
    }

    /******与 height 相关的方法******/
//取结点的高度,即以该结点为根的树的高度
    public int getHeight() {
        return height;
    }

    //更新当前结点及其祖先的高度
    public void updateHeight() {
        int newH = 0;//新高度初始化为 0,高度等于左右子树高度加 1 中的大者
        if (hasLChild()) newH = Math.max(newH, 1 + getLChild().getHeight());
        if (hasRChild()) newH = Math.max(newH, 1 + getRChild().getHeight());
        if (newH == height) return; //高度没有发生变化则直接返回
        height = newH; //否则更新高度
        if (hasParent()) getParent().updateHeight(); //递归更新祖先的高度
    }

    /******与 size 相关的方法******/
//取以该结点为根的树的结点数
    public int getSize() {
        return size;
    }

    //更新当前结点及其祖先的子孙数
    public void updateSize() {
        size = 1; //初始化为 1,结点本身
        if (hasLChild()) size += getLChild().getSize(); //加上左子树规模
        if (hasRChild()) size += getRChild().getSize(); //加上右子树规模
        if (hasParent()) getParent().updateSize(); //递归更新祖先的规模
    }

    /******与 parent 相关的方法******/
//取父结点
    public BinTreeNode getParent() {
        return parent;
    }

    //断开与父亲的关系
    public void sever() {
        if (!hasParent()) return;
        if (isLChild()) parent.lChild = null;
        else parent.rChild = null;
        parent.updateHeight(); //更新父结点及其祖先高度
        parent.updateSize(); //更新父结点及其祖先规模
        parent = null;
    }

    /******与 lChild 相关的方法******/
//取左孩子
    public BinTreeNode getLChild() {
        return lChild;
    }

    //设置当前结点的左孩子,返回原左孩子
    public BinTreeNode setLChild(BinTreeNode lc) {
        BinTreeNode oldLC = this.lChild;
        if (hasLChild()) {
            lChild.sever();
        } //断开当前左孩子与结点的关系
        if (lc != null) {
            lc.sever(); //断开 lc 与其父结点的关系
            this.lChild = lc; //确定父子关系
            lc.parent = this;
            this.updateHeight(); //更新当前结点及其祖先高度
            this.updateSize(); //更新当前结点及其祖先规模
        }
        return oldLC; //返回原左孩子
    }

    /******与 rChild 相关的方法******/
//取右孩子
    public BinTreeNode getRChild() {
        return rChild;
    }

    //设置当前结点的右孩子,返回原右孩子
    public BinTreeNode setRChild(BinTreeNode rc) {
        BinTreeNode oldRC = this.rChild;
        if (hasRChild()) {
            rChild.sever();
        } //断开当前右孩子与结点的关系
        if (rc != null) {
            rc.sever(); //断开 lc 与其父结点的关系
            this.rChild = rc; //确定父子关系
            rc.parent = this;
            this.updateHeight(); //更新当前结点及其祖先高度
            this.updateSize(); //更新当前结点及其祖先规模
        }
        return oldRC; //返回原右孩子
    }

//    public Iterator preOrder(){
//
//        LinkedList list = new LinkedListDLNode();
//
//        preOrderRecursion(this.root, list);
//        return list.elements();
//    }
//
//    private void preOrderRecursion(BinTreeNode rt, LinkedList list) {
//        if (rt == null) {
//            return;
//        }
//        list.insertLast(rt);
//        preOrderRecursion(rt.getLChild(), list);
//        preOrderRecursion(rt.getRChild(), list);
//    }


    /**
     * 先序遍历
     * middle->left -> right
     *
     * @return
     */
    public Iterator preOrder() {


        LinkedList list = new LinkedListDLNode();

        preOrderTraverse(this.root, list);

        return list.elements();
    }

    /**
     * 先序 遍历
     * middle -> left -> right
     *
     * @param root
     * @param list
     */
    private void preOrderTraverseRe(BinTreeNode root, LinkedList list) {
        if (root == null) {
            return;
        }
        list.insertLast(root);
        inOrderTraverse(root.getLChild(), list);

        inOrderTraverse(root.getRChild(), list);


    }

    /***
     *  利用 stack 可以 代替  recursion
     * @param root
     * @param list
     */
    private void preOrderTraverse(BinTreeNode root, LinkedList list) {
        if (root == null) {
            return;
        }
        BinTreeNode p = root;
        Stack stack = new StackSLinked();
        while (p != null) {
            while (p != null) {
                // 插入 根节点
                list.insertLast(p);

                BinTreeNode RNode = p.getRChild();
                //记录 右 节点
                if (root.hasRChild()) {
                    stack.push(RNode);
                }
                p = p.getLChild();
            }
            if (!stack.isEmpty()) {
                p = (BinTreeNode) stack.pop();
            }
        }
    }


    public Iterator inOrder() {

        LinkedList list = new LinkedListDLNode();

        inOrderTraverse(this.root, list);

        return list.elements();


    }


    /**
     * 中序 遍历
     * left ->middle->right
     *
     * @param root
     * @param list
     */
    private void inOrderTraverse(BinTreeNode root, LinkedList list) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.getLChild(), list);

        list.insertLast(root);

        inOrderTraverse(root.getRChild(), list);


    }

    // 中序 遍历 stack
    private void inOrderTraverseRe(BinTreeNode root, LinkedList list) {
        if (root == null) {
            return;
        }
        Stack stack = new StackSLinked();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {

                stack.push(root);

                root = root.getLChild();

            }
            if (!stack.isEmpty()) {
                BinTreeNode p = (BinTreeNode) stack.pop();

                list.insertLast(p);

                root = p.getRChild();

            }
        }


    }

    /**
     * 后序遍历 递归
     * right -> middle -> left
     *
     * @param root
     * @param list
     */
    private void postOrderTraverseRe(BinTreeNode root, LinkedList list) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.getLChild(), list);


        inOrderTraverse(root.getRChild(), list);

        list.insertLast(root);
    }

    // 后序 遍历 的 非 递归
    private void postOrderTraverse(BinTreeNode root, LinkedList list) {
        if (root == null) {
            return;
        }
        BinTreeNode p = root;

        Stack s = new StackSLinked();


        while (p != null || !s.isEmpty()) {
            // 先 左 后 右 不断 深入
            while (p != null) {
                // 将 根 节点 入栈
                s.push(p);

                if (p.hasLChild()) {
                    p = getLChild();
                } else {
                    p = p.getRChild();
                }
            }
            if (!s.isEmpty()) {
                //取出 栈顶 根 结点 访问
                p = (BinTreeNode) s.pop();
                list.insertLast(p);
            }

// 满足 条件 时， 说明 栈顶 根 节点 右子树 已 访问， 应 出 栈 访问 之
            while (!s.isEmpty() && ((BinTreeNode) s.peek()).getRChild() == p) {
                p = (BinTreeNode) s.pop();
                list.insertLast(p);
            }
// 转 向 栈顶 根 结点 的 右子树 继续 后序 遍历
            if (!s.isEmpty()) {
                p = ((BinTreeNode) s.peek()).getRChild();

            } else {
                p = null;
            }

        }


    }

    /**
     * 安 层 遍历   使用 queue 队列
     *
     * @return
     */
    public Iterator levelOrder() {

        LinkedList linkedList = new LinkedListDLNode();


        levelOrderTraverse(this.root, linkedList);


        return linkedList.elements();
    }

    private void levelOrderTraverse(BinTreeNode root, LinkedList linkedList) {

        if (root == null) {
            return;
        }
        Queue q = new QueueArray();
        // 根节点 入队
        q.enqueue(root);
        while (!q.isEmpty()) {
            // 取出 队首 节点 p 并 访问
            BinTreeNode p = (BinTreeNode) q.dequeue();

            linkedList.insertLast(p);
            if (p.hasLChild()) {
                // 将 p 的 非空 左右 孩子 依次 入 队
                q.enqueue(p.getLChild());
            }
            if (p.hasRChild()) {
                q.enqueue(p.getRChild());
            }
        }

    }

    /***
     *  在 树中 查找 元素 object 对应 的 结点
     * @param object
     * @return
     */
    public BinTreeNode find(Object object) {
        return searchE(root, object);
    }

    /***
     *  递归 查早 元素 e
     * @param root
     * @param object
     * @return
     */
    private BinTreeNode searchE(BinTreeNode root, Object object) {

        if (root == null) {
            return null;
        }
        if (strategy.equal(root.getData(), object)) {
            return root;
        }
        BinTreeNode v = searchE(root.getLChild(), object);
        if (v == null) {
            v = searchE(root.getRChild(), object);
        }
        return v;
    }


    public void testpreOrder(BinTreeNode root, LinkedList list) {
//        1.   stack 辅助
//        2.  先 序 遍历  新 结点 ，再 左 结点 ，再 右 结点
//        3. 将 右节点 入栈
        Stack stack = new StackSLinked();
        while (root != null) {
            // 向 左 结点 走到底
            while (root != null) {
                list.insertLast(root);
                if (root.hasRChild()) stack.push(root.getRChild());
                root = root.getLChild();
            }
            //  遍历 右节点
            if (!stack.isEmpty()) {
                root = (BinTreeNode) stack.pop();
            }
        }
    }

    public void testInOrder(BinTreeNode root, LinkedList list) {
        Stack stack = new StackSLinked();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.getLChild();
            }
            if (!stack.isEmpty()) {
                BinTreeNode p = (BinTreeNode) stack.pop();
                list.insertLast(p);
                root = p.getRChild();
            }
        }
    }
    // 汉诺塔

    public void hanio(int n, char x, char y, char z) {
        if (n == 1) move(x, n, z);
        else {
            hanio(n - 1, x, z, y);
            move(x, n, z);
            hanio(n - 1, y, x, z);
        }
    }

    private void move(char x, int n, char y) {
        System.out.println("Move " + n + " from " + x + " to " + y);
    }


}
