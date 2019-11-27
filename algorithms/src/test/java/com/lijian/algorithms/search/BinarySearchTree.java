package com.lijian.algorithms.tree;

import com.lijian.algorithms.Strategy;
import com.lijian.algorithms.link.Node;

import java.text.Collator;


//二叉查找树
public class BinarySearchTree {
    //根节点
    private BinTreeNode root;
    // 比较策略
    Strategy strategy;

    public Node search(Object ele){
        return binTSearchRe (root, ele);
    }

    /***
     *
     * @param rt 对应根节点
     * @param ele 查找元素
     * @return
     */
    private Node binTSearchRe(BinTreeNode rt, Object ele){
        if (rt==null) return null;


        switch(strategy.compare(ele,rt.getData())){
            case 0: return rt; //等于
            case -1: return binTSearchRe(rt.getLChild(),ele); //小于
            default: return binTSearchRe(rt.getRChild(),ele); //大于
        }
    }
}
