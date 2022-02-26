package com.lijian.algorithms.graph;

import com.lijian.algorithms.link.Iterator;
import com.lijian.algorithms.link.LinkedList;
import com.lijian.algorithms.link.LinkedListDLNode;
import com.lijian.algorithms.link.Node;
import com.lijian.algorithms.queue.Queue;
import com.lijian.algorithms.queue.QueueSLinked;
import com.lijian.algorithms.stack.Stack;
import com.lijian.algorithms.stack.StackSLinked;



public class AbstractGraph implements Graph {


    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getVexNum() {
        return 0;
    }

    @Override
    public int getEdgeNum() {
        return 0;
    }

    @Override
    public Iterator getVertex() {
        return null;
    }

    @Override
    public Iterator getEdge() {
        return null;
    }

    @Override
    public void remove(Vertex v) {

    }

    @Override
    public void remove(Edge e) {
    }

    @Override
    public Node insert(Vertex v) {
        return null;
    }

    @Override
    public Node insert(Edge e) {
        return null;
    }

    @Override
    public boolean areAdjacent(Vertex u, Vertex v) {
        return false;
    }

    @Override
    public Edge edgeFromTo(Vertex u, Vertex v) {
        return null;
    }

    @Override
    public Iterator adjVertexs(Vertex u) {
        return null;
    }

//    @Override
//    public Iterator DFSTraverse(Vertex v) {
//        return null;
//    }
//
//    @Override
//    public Iterator BFSTraverse(Vertex v) {
//        return null;
//    }

    @Override
    public Iterator shortestPath(Vertex v) {
        return null;
    }

    @Override
    public void generateMST() throws UnsupportedOperation {

    }

    @Override
    public Iterator toplogicalSort() throws UnsupportedOperation {
        return null;
    }

    @Override
    public void criticalPath() throws UnsupportedOperation {

    }



    /**
     * 深度优先算法 --利用 回溯
     * @param v
     * @return
     */
    public Iterator DFSTraverse(Vertex v) {
        LinkedList traverseSeq = new LinkedListDLNode();//遍历结果
//        resetVexStatus(); //重置顶点状态
        DFSRecursion(v, traverseSeq); //从 v 点出发深度优先搜索
        Iterator it = getVertex(); //从图未曾访问的其他顶点重新搜索（调用图操作③）
        for (it.first(); !it.isDone(); it.next()) {
            Vertex u = (Vertex) it.currentItem();
            if (!u.isVisited()) DFSRecursion(u, traverseSeq);
        }
        return traverseSeq.elements();
    }

    //从顶点 v 出发深度优先搜索的递归算法
    private void DFSRecursion(Vertex v, LinkedList list) {
        v.setToVisited(); //设置顶点 v 为已访问
        list.insertLast(v); //访问顶点 v
        Iterator it = adjVertexs(v); //取得顶点 v 的所有邻接点（调用图操作⑧）
        for (it.first(); !it.isDone(); it.next()) {
            Vertex u = (Vertex) it.currentItem();
            if (!u.isVisited()) DFSRecursion(u, list);
        }
    }
    // 利用 栈结构  后入 先出 ， 与 递归调用，存储 栈帧结构  相同，那么 递归 调用 都可以 使用 栈 结构 实现 ？？
    //1. 首先 将 初始顶点 v 入栈
    //2. 当堆栈 不为空 ，重复
        //1. 栈顶元素出栈，若未访问
        //2. 则访问之并设置访问标志，将其未曾访问的邻接点入栈

    //从顶点 v 出发深度优先搜索的非递归算法
    private void DFS(Vertex v, LinkedList list){
        Stack s = new StackSLinked();
        s.push(v);
        while (!s.isEmpty()){
            Vertex u = (Vertex)s.pop(); //取栈顶元素
            if (!u.isVisited()){ //如果没有访问过
                u.setToVisited(); //访问之
                list.insertLast(u);
                Iterator it = adjVertexs(u); //未访问的邻接点入栈（调用图操作⑧）
                for(it.first(); !it.isDone(); it.next()){                   // 这边 并没有 依次 访问 邻接点， 而是
                    Vertex adj = (Vertex)it.currentItem();
                    if (!adj.isVisited()) s.push(adj);
                }//for
            }//if
        }//while
    }


    /***
     * 相当于 遍历树 按层排序 ， 队列 的 作用 是用来 判断  结点 是否 已经 访问过
     * 1. 首先 访问 初始 顶点 v 并 入 队
     * 2. 当 队列不为空时，重复以下处理
     *      1. 队首元素出队，访问其所有未曾访问的邻接点，并它们入队
     * 3. 如果图中还有未曾访问的邻接点，选择一个重复以上过程
     * @param v 结点
     *
     */
    public Iterator BFSTraverse(Vertex v) {
        LinkedList traverseSeq = new LinkedListDLNode();//遍历结果
//        resetVexStatus(); //重置顶点状态
        BFS(v, traverseSeq); //从 v 点出发广度优先搜索
        Iterator it = getVertex(); //从图中未访问的顶点重新搜索（调用图操作③）
        for (it.first(); !it.isDone(); it.next()) {
            Vertex u = (Vertex) it.currentItem();
             if (!u.isVisited()) BFS(u, traverseSeq);
        }
        return traverseSeq.elements();
    }


    private void BFS(Vertex v, LinkedList list) {
        Queue q = new QueueSLinked();
        v.setToVisited(); //访问顶点 v
        list.insertLast(v);
        q.enqueue(v); //顶点 v 入队
        while (!q.isEmpty()) {
            Vertex u = (Vertex) q.dequeue(); //队首元素出队
            Iterator it = adjVertexs(u); //访问其未曾访问的邻接点，并入队
            for (it.first(); !it.isDone(); it.next()) {
                Vertex adj = (Vertex) it.currentItem();
                if (!adj.isVisited()) {
                    adj.setToVisited();
                    list.insertLast(adj);
                    q.enqueue(adj);
                }//if
            }//for
        }//while
    }
}
