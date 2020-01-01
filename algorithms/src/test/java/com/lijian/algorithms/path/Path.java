package com.lijian.algorithms.path;

import com.lijian.algorithms.graph.AbstractGraph;
import com.lijian.algorithms.graph.Edge;
import com.lijian.algorithms.graph.Graph;
import com.lijian.algorithms.graph.Vertex;
import com.lijian.algorithms.link.Iterator;
import com.lijian.algorithms.link.LinkedList;
import com.lijian.algorithms.link.LinkedListDLNode;

public class Path  extends AbstractGraph {
    private int distance; //起点与终点的距离
    private Vertex start; //起点信息
    private Vertex end; //终点信息
    private LinkedList pathInfo; //起点到终点途经的顶点序列

    //构造方法
    public Path() {
        this(Integer.MAX_VALUE, null, null);
    }

    public Path(int distance, Vertex start, Vertex end) {
        this.distance = distance;
        this.start = start;
        this.end = end;
        pathInfo = new LinkedListDLNode();
    }

    //判断起点与终点之间是否存在路径
    public boolean hasPath() {
        return distance != Integer.MAX_VALUE && start != null && end != null;
    }

    //求路径长度
    public int pathLength() {
        if (!hasPath()) return -1;
        else if (start == end) return 0;
        else return pathInfo.getSize() + 1;
    }

    //get&set methods
    public void setDistance(int dis) {
        distance = dis;
    }

    public void setStart(Vertex v) {
        start = v;
    }

    public void setEnd(Vertex v) {
        end = v;
    }

    public int getDistance() {
        return distance;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public Iterator getPathInfo() {
        return pathInfo.elements();
    }

    //清空路经信息
    public void clearPathInfo() {
        pathInfo = new LinkedListDLNode();
    }

    //添加路径信息
    public void addPathInfo(Object info) {
        pathInfo.insertLast(info);
    }


































    public Iterator shortestPath(Vertex v) {
        LinkedList sPath = new LinkedListDLNode(); //所有的最短路径序列
        resetVexStatus(); //重置图中各顶点的状态信息
//初始化，将 v 到各顶点的最短距离初始化为由 v 直接可达的距离
        Iterator it = getVertex(); //（调用图操作③）
        for (it.first(); !it.isDone(); it.next()) {
            Vertex u = (Vertex) it.currentItem();
            int weight = Integer.MAX_VALUE;
            Edge e = edgeFromTo(v, u); //（调用图操作⑦）
            if (e != null) weight = e.getWeight();
            if (u == v) weight = 0;
            Path p = new Path(weight, v, u);
            setPath(u, p);
        }
        v.setToVisited(); //顶点 v 进入集合 S
        sPath.insertLast(getPath(v)); //求得的最短路径进入链接表
        for (int t = 1; t < getVexNum(); t++) { //进行|V|-1 次循环找到|V|-1 条最短路径 ， getVexNum()  返回的vertex的数目
            Vertex k = selectMin(it); //找 V-S 中 distance 最小的点 k
            k.setToVisited(); //顶点 k 加入 S
            sPath.insertLast(getPath(k)); //求得的最短路径进入链接表
            int distK = getDistance(k); //修正 V-S 中顶点当前最短路径
            Iterator adjIt = adjVertexs(k); //取出 k 的所有邻接点
            for (adjIt.first(); !adjIt.isDone(); adjIt.next()) {
                Vertex adjV = (Vertex) adjIt.currentItem(); //k 的邻接点 adjV
                Edge e = edgeFromTo(k, adjV); //（调用图操作⑦）
//发现更短的路径
                if ((long) distK + (long) e.getWeight() < (long) getDistance(adjV)) {
                    setDistance(adjV, distK + e.getWeight());
                    amendPathInfo(k, adjV); //以 k 的路径信息修改 adjV 的路径信息
                }
            }//for
        }//for(int t=1...
        return sPath.elements();
    }

    //在顶点集合中选择路径距离最小的
    protected Vertex selectMin(Iterator it) {
        Vertex min = null;
        for (it.first(); !it.isDone(); it.next()) {
            Vertex v = (Vertex) it.currentItem();
            if (!v.isVisited()) {
                min = v;
                break;
            }
        }
        for (; !it.isDone(); it.next()) {
            Vertex v = (Vertex) it.currentItem();
            if (!v.isVisited() && getDistance(v) < getDistance(min))
                min = v;
        }
        return min;
    }
}