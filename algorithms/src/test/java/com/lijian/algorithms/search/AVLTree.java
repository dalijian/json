package com.lijian.algorithms.search;

import com.lijian.algorithms.tree.BinTreeNode;

public class AVLTree extends BinarySearchTree {

    @Override
    public void insert(Object ele) {
        super.insert(ele);
        root = reBalance(startBN);

    }
    private BinTreeNode reBalance(BinTreeNode v){
        if (v==null) return root;
        BinTreeNode c = v;
        while (v!=null) { //从 v 开始，向上逐一检查 z 的祖先
            if (!isBalance(v)) v = rotate(v); //若 v 失衡，则旋转使之重新平衡
            c = v;
            v = v.getParent(); //继续检查其父亲
        }//while
        return c;
    }
    //判断一个结点是否失衡
    private boolean isBalance(BinTreeNode v){
        if (v==null) return true;
        int lH = (v.hasLChild()) ? v.getLChild().getHeight():-1;
        int rH = (v.hasRChild()) ? v.getRChild().getHeight():-1;
        return (Math.abs(lH - rH)<=1);
    }

    @Override
    public Object remove(Object ele) {
        Object obj= super.remove(ele);
        root = reBalance(startBN);
        return obj;
    }
}
