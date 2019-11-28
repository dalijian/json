package com.lijian.algorithms.graph;

import com.lijian.algorithms.link.Iterator;
import com.lijian.algorithms.link.Node;



public interface Graph {
    public static final int UndirectedGraph = 0;//无向图
    public static final int DirectedGraph = 1;//有向图

    //返回图的类型
    public int getType();

    //返回图的顶点数
    public int getVexNum();

    //返回图的边数
    public int getEdgeNum();

    //返回图的所有顶点
    public Iterator getVertex();

    //返回图的所有边
    public Iterator getEdge();

    //删除一个顶点 v
    public void remove(Vertex v);

    //删除一条边 e
    public void remove(Edge e);

    //添加一个顶点 v
    public Node insert(Vertex v);

    //添加一条边 e
    public Node insert(Edge e);

    //判断顶点 u、 v 是否邻接，即是否有边从 u 到 v
    public boolean areAdjacent(Vertex u, Vertex v);

    //返回从 u 指向 v 的边，不存在则返回 null
    public Edge edgeFromTo(Vertex u, Vertex v);

    //返回从 u 出发可以直接到达的邻接顶点
    public Iterator adjVertexs(Vertex u);

    //对图进行深度优先遍历
    public Iterator DFSTraverse(Vertex v);

    //对图进行广度优先遍历
    public Iterator BFSTraverse(Vertex v);

    //求顶点 v 到其他顶点的最短路径
    public Iterator shortestPath(Vertex v);

    //求无向图的最小生成树,如果是有向图不支持此操作
    public void generateMST() throws UnsupportedOperation;

    //求有向图的拓扑序列,无向图不支持此操作
    public Iterator toplogicalSort() throws UnsupportedOperation;

    //求有向无环图的关键路径,无向图不支持此操作
    public void criticalPath() throws UnsupportedOperation;
}