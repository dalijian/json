package com.lijian.algorithms.practise;

import com.lijian.algorithms.Strategy;
import com.lijian.algorithms.link.Node;
import com.lijian.algorithms.tree.BinTreeNode;

public class BinarySearchTreeDemo {

    protected BinTreeNode root;

    Strategy strategy;

    private BinTreeNode startBN;

    public Node search(Object ele) {
        return binTSearchRe(root, ele);
    }

    private Node binTSearchRe(BinTreeNode root, Object ele) {
        if (root == null) {
            return null;
        }
        switch (strategy.compare(ele, root.getData())) {
            case 0: return root;
            case 1:
                binTSearchRe(root.getRChild(), ele);
            case -1:
                binTSearchRe(root.getLChild(), ele);
        }
        return null;
    }

//    拿到 该节点的最左 节点
    public Node min(BinTreeNode node) {
        if (node != null) {
            while (node.hasLChild()) {
                node =node.getLChild();
            }
        }
        return node;
    }

    public Node max(BinTreeNode node) {
        if (node != null) {
            while (node.hasRChild()) {
                node=node.getRChild();
            }
        }
        return node;
    }

    /***
     * 拿到 中序遍历的后序结点  比node 大的结点
     * @param node
     * @return
     */
    public BinTreeNode getSuccessor(BinTreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.hasRChild()) {
            return (BinTreeNode) min(node.getRChild());
        }
        while (node.isRChild()) {
            node = node.getParent();
        }
        return node.getParent();
    }

    public BinTreeNode getPredecessor(BinTreeNode v) {
        if (v == null) {
            return null;
        }
        if (v.hasLChild()) {
            return (BinTreeNode) max(v.getLChild());
        }
        return v.getParent();
    }

    public void insert(Object ele) {
//        p指向插入结点的 父节点， 用来 绑定 结点的 父子关系
        BinTreeNode p= null;
        BinTreeNode current =root;
// 从 根节点 开始 找
        while (current != null) {
            p=current;
            if (strategy.compare(ele, current.getData()) < 0) {
                current =current.getLChild();

            }else{
                current =current.getRChild();
            }
        }
        startBN  = p;
        if (p ==null) {
            root = new BinTreeNode(ele);
        } else if (strategy.compare(ele, p.getData()) < 0) {
            p.setLChild(new BinTreeNode(ele));

        }else {
            p.setRChild(new BinTreeNode(ele));
        }
    }
}
