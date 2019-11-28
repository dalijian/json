package com.lijian.algorithms.search;

import com.lijian.algorithms.Strategy;
import com.lijian.algorithms.link.Node;
import com.lijian.algorithms.tree.BinTreeNode;

import java.text.Collator;


//二叉查找树
public class BinarySearchTree {
    //根节点
    protected BinTreeNode root;
    // 比较策略
    Strategy strategy;

    protected BinTreeNode startBN; //待平衡出发点 ＊ 用来 执行 左旋，或右旋

    public Node search(Object ele){
        return binTSearchRe (root, ele);
    }

    /***
     *  先序遍历 ，查询顺序 根结点-> 左节点-> 右结点
     *
     *  算法在递归的执行过程中
     * 只会沿着 case 语句的一条分支进行；查找成功时，实际上就是走了一条从根结点到某个结
     * 点的路径，路径上结点的个数为算法执行中进行关键字比较的次数；查找失败时，走了一条
     * 从根到某个空结点的路径，算法中进行关键字的比较次数依然是路径上结点个数；因此算法
     * 的时间复杂度为 Ο(h)， h 为二叉查找树的高度
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


    public Node min(BinTreeNode v) {
        if (v != null) {
            while (v.hasLChild()) {
                v=v.getLChild();
            }
        }
        return v;
    }

    public Node max(BinTreeNode v) {
        if (v != null) {
            while (v.hasRChild()) {
                v = v.getRChild();
            }
        }
        return v;
    }

    /***
     *时间复杂度 为 O（h) h为 树高
     * @param v 根节点 v
     * @return  返回v 在 中序遍历序列 中的后序结点
     */
    private BinTreeNode getSuccessor(BinTreeNode v) {
        if (v == null) {
            return null;
        }
        if (v.hasRChild()) {
            return (BinTreeNode) min(v.getRChild());
        }
//        如果v是 右子树节点，那么 v的 后继节点 就是 v的父节点的父节点
        while (v.isRChild()) {
            v = v.getParent();
        }
        return v.getParent();
    }

    /***
     *
     * @param v 待插入元素
     * @return 在二叉查找树中插入ele
     */
    public BinTreeNode getPredecessor(BinTreeNode v) {
        if (v == null) {
            return null;
        }
        if (v.hasLChild()) {
            return (BinTreeNode) max(v.getLChild());
        }
        while (v.isLChild()) {
            v = v.getParent();
        }
        return v.getParent();
    }

    /***
     *
     * @param ele 插入元素
     *            时间复杂度 为 o(h)
     */

    public void insert(Object ele){
//        p 指向 current 的父节点
        BinTreeNode p = null;
        BinTreeNode current = root;
        while (current!=null){ // 找到待插入位置,current 为空时 ，时 插入位置
            p = current;
            if (strategy.compare(ele,current.getData())<0)
                current = current.getLChild();
            else
                current = current.getRChild();
        }
        startBN = p; //待平衡出发点 ＊
        if (p==null)
            root = new BinTreeNode(ele); //树为空
        else if (strategy.compare(ele,p.getData())<0)
            p.setLChild(new BinTreeNode(ele));
        else
            p.setRChild(new BinTreeNode(ele));
        }


    /***
     *
     * @param ele 待删除 元素 ele
     * @return 待删除 元素 ele
     */
    public Object remove(Object ele){
        BinTreeNode v = (BinTreeNode)binTSearchRe(root,ele);
        if (v==null) return null; //查找失败
        BinTreeNode del = null; //待删结点
        BinTreeNode subT = null; //待删结点的子树
        if (!v.hasLChild()||!v.hasRChild()) //确定待删结点   v 是 叶子 节点， 没有 左子树，或者没有  右子树
            del = v;
        else{
//            v 有 左子树，和 右子树
            del = getPredecessor(v);  //  将 待删除 节点 设置 成  v 的 前继 节点
            Object old = v.getData();
            v.setData(del.getData()); // 将 前继 节点的 值 设置成 v 的值
            del.setData(old);
        }
        startBN = del.getParent(); //待平衡出发点 ＊
//此时待删结点只有左子树或右子树
        if (del.hasLChild())
            subT = del.getLChild();
        else
            subT = del.getRChild();
        if (del==root) { //若待删结点为根
            if (subT!=null) subT.sever();
            root = subT;
        } else
        if (subT!=null){
//del 为非叶子结点
            if (del.isLChild()) del.getParent().setLChild(subT);
            else del.getParent().setRChild(subT);
        }
        else//del 为叶子结点
            del.sever();
        return del.getData();
    }

//    旋转 结点 z,y,x
    protected BinTreeNode rotate(BinTreeNode z){
        BinTreeNode y = higherSubT(z); //取 y 为 z 更高的孩子
        BinTreeNode x = higherSubT(y); //取 x 为 y 更高的孩子
        boolean isLeft = z.isLChild(); //记录： z 是否左孩子
        BinTreeNode p = z.getParent(); //p 为 z 的父亲
        BinTreeNode a, b, c; //自左向右，三个节点
        BinTreeNode t0, t1, t2, t3; //自左向右，四棵子树
// 以下分四种情况重命名
        if (y.isLChild()) { //若 y 是左孩子，则
            c = z; t3 = z.getRChild();
            if (x.isLChild()) { //若 x 是左孩子(左左失衡)
                b = y; t2 = y.getRChild();
                a = x; t1 = x.getRChild(); t0 = x.getLChild();
            } else { //若 x 是右孩子(左右失衡)
                a = y; t0 = y.getLChild();
                b = x; t1 = x.getLChild(); t2 = x.getRChild();
            }
        } else { //若 y 是右孩子，则
           a = z; t0 = z.getLChild();
            if (x.isRChild()) { //若 x 是右孩子(右右失衡)
                b = y; t1 = y.getLChild();
                c = x; t2 = x.getLChild(); t3 = x.getRChild();
            } else { //若 x 是左孩子(右左失衡)
                c = y; t3 = y.getRChild();
                b = x; t1 = x.getLChild(); t2 = x.getRChild();
            }
        }
//摘下三个节点
        z.sever();
        y.sever();
        x.sever();
//摘下四棵子树
        if (t0!=null) t0.sever();
        if (t1!=null) t1.sever();
        if (t2!=null) t2.sever();
        if (t3!=null) t3.sever();
//重新链接
        a.setLChild(t0); a.setRChild(t1);
        c.setLChild(t2); c.setRChild(t3);
        b.setLChild(a); b.setRChild(c);
//子树重新接入原树
        if (p!=null)
            if (isLeft) p.setLChild(b);
            else p.setRChild(b);
        return b;//返回新的子树根
    }
    //返回结点 v 较高的子树
    private BinTreeNode higherSubT(BinTreeNode v){
        if (v==null) return null;
        int lH = (v.hasLChild()) ? v.getLChild().getHeight():-1;
        int rH = (v.hasRChild()) ? v.getRChild().getHeight():-1;
        if (lH>rH) return v.getLChild();
        if (lH<rH) return v.getRChild();
        if (v.isLChild()) return v.getLChild();
        else return v.getRChild();
    }
}
