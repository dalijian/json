package com.lijian.algorithms.graph;

import com.lijian.algorithms.link.Iterator;
import com.lijian.algorithms.link.LinkedList;
import com.lijian.algorithms.link.LinkedListDLNode;
import com.lijian.algorithms.path.Path;
import com.lijian.algorithms.stack.Stack;
import com.lijian.algorithms.stack.StackSLinked;

import java.util.HashSet;

// 有向图， 支持  toplogicalSort() (拓扑排序)  ,  criticalPath()(关键路径)
public class DirectGraph extends AbstractGraph {


    private LinkedList edges;

    //取或设置顶点 v 的当前最短距离
    protected int getDistance(Vertex v){ return ((Path)v.getAppObj()).getDistance();}
    protected void setDistance(Vertex v, int dis){ ((Path)v.getAppObj()).setDistance(dis);}
    //取或设置顶点 v 的当前最短路径
    protected Path getPath(Vertex v){ return (Path)v.getAppObj();}
    protected void setPath(Vertex v, Path p){ v.setAppObj(p);}

    private void resetVexStatus(){
        
    }

    public Iterator shortestPath(Vertex v) {
        LinkedList sPath = new LinkedListDLNode(); //所有的最短路径序列
        resetVexStatus(); //重置图中各顶点的状态信息
//初始化，将 v 到各顶点的最短距离初始化为由 v 直接可达的距离
        Iterator it = getVertex(); //（调用图操作③）
        for(it.first(); !it.isDone(); it.next()){
            Vertex u = (Vertex)it.currentItem();
            int weight = Integer.MAX_VALUE;
            Edge e = edgeFromTo(v,u); //（调用图操作⑦）
            if (e!=null) weight = e.getWeight();
            if(u==v) weight = 0;
            Path p = new Path(weight,v,u);
            setPath(u, p);
        }
        v.setToVisited(); //顶点 v 进入集合 S
        sPath.insertLast(getPath(v)); //求得的最短路径进入链接表
        for (int t=1;t<getVexNum();t++){ //进行|V|-1 次循环找到|V|-1 条最短路径
            Vertex k = selectMin(it); //找 V-S 中 distance 最小的点 k
            k.setToVisited(); //顶点 k 加入 S
            sPath.insertLast(getPath(k)); //求得的最短路径进入链接表
            int distK = getDistance(k); //修正 V-S 中顶点当前最短路径
            Iterator adjIt = adjVertexs(k); //取出 k 的所有邻接点
            for(adjIt.first(); !adjIt.isDone(); adjIt.next()){
                Vertex adjV = (Vertex)adjIt.currentItem(); //k 的邻接点 adjV
                Edge e = edgeFromTo(k,adjV); //（调用图操作⑦）
                                            //发现更短的路径
                if ((long)distK+(long)e.getWeight()<(long)getDistance(adjV)){
                    setDistance(adjV, distK+e.getWeight());
                    amendPathInfo(k,adjV); //以 k 的路径信息修改 adjV 的路径信息
                }
            }//for
        }//for(int t=1...
        return sPath.elements();
    }

    private void amendPathInfo(Vertex k, Vertex adjV) {
    }

    //在顶点集合中选择路径距离最小的
    protected Vertex selectMin(Iterator it){
        Vertex min = null;
        for(it.first(); !it.isDone(); it.next()){
            Vertex v = (Vertex)it.currentItem();
            // 如果 v 没有 被 访问
            if(!v.isVisited()){ min = v; break;}
        }
        for(; !it.isDone(); it.next()){
            Vertex v = (Vertex)it.currentItem();
            // 如果 v 没有 被 访问 比较 v 的 distance 与 min 的 distance
            if(!v.isVisited()&&getDistance(v)<getDistance(min))
                min = v;
        }
        return min;
    }


    // 拓扑 排序

    //取或设置顶点 v 的当前入度
    private int getTopInDe(Vertex v){
        return ((Integer)v.getAppObj()).intValue();
    }
    private void setTopInDe(Vertex v, int indegree){
        v.setAppObj(Integer.valueOf(indegree));
    }

    public Iterator toplogicalSort(){
        LinkedList topSeq = new LinkedListDLNode(); //拓扑序列
        Stack s = new StackSLinked();               // 这里 使用 stack  ， 有没有 利用 stack 的 特性 LIFO??
        Iterator it = getVertex();
        for(it.first(); !it.isDone(); it.next()){ //初始化顶点集应用信息
            Vertex v = (Vertex)it.currentItem();
            v.setAppObj(Integer.valueOf(v.getInDeg()));
            if (v.getInDeg()==0) s.push(v);
        }
        while (!s.isEmpty()){
            Vertex v = (Vertex)s.pop();
            topSeq.insertLast(v); //生成拓扑序列
            Iterator adjIt = adjVertexs(v); //对于 v 的每个邻接点入度减 1
            for(adjIt.first(); !adjIt.isDone(); adjIt.next()){
                Vertex adjV = (Vertex)adjIt.currentItem();
                int in = getTopInDe(adjV)-1;
                setTopInDe(adjV, in);
                if (in==0) s.push(adjV); //入度为 0 的顶点入栈
            }//for adjIt
        }//while
        if (topSeq.getSize()<getVexNum()) return null;
        else return topSeq.elements();
    }







    // 关键 路径 算法

    //取顶点 v 的最早开始时间与最迟开始时间
    private int getVE(Vertex v){
        return ((Vtime)v.getAppObj()).getVE();
    }
    private int getVL(Vertex v){
        return ((Vtime)v.getAppObj()).getVL();
    }
    //设置顶点 v 的最早开始时间与最迟开始时间
    private void setVE(Vertex v, int ve){
        ((Vtime)v.getAppObj()).setVE(ve);
    }
    private void setVL(Vertex v, int vl){
       ((Vtime)v.getAppObj()).setVL(vl);
    }

    public void resetEdgeType(){

    }

    // 拿到 关键 路径
    public void criticalPath(){
        Iterator it = toplogicalSort();
        resetEdgeType(); //重置图中各边的类型信息
        if (it==null) return;
        LinkedList reTopSeq = new LinkedListDLNode(); //逆拓扑序列
        for(it.first(); !it.isDone(); it.next()){ //初始化各点 ve 与 vl，并生成逆拓扑序列
            Vertex v = (Vertex)it.currentItem();
            Vtime time = new Vtime(0,Integer.MAX_VALUE); //ve=0,vl=∞
            v.setAppObj(time);
            reTopSeq.insertFirst(v);
        }
        for(it.first(); !it.isDone(); it.next()){ //正向拓扑序列求各点 ve
            Vertex v = (Vertex)it.currentItem();
            Iterator adjIt = adjVertexs(v);
            for(adjIt.first(); !adjIt.isDone(); adjIt.next()){
                Vertex adjV = (Vertex)adjIt.currentItem();
                Edge e = edgeFromTo(v,adjV);
                if (getVE(v)+e.getWeight()>getVE(adjV)) //更新最早开始时间
                    setVE(adjV, getVE(v)+e.getWeight());
            }
            }
        Vertex dest = (Vertex)reTopSeq.first().getData();
        setVL(dest, getVE(dest)); //设置汇点 vl=ve
        Iterator reIt = reTopSeq.elements();
        for(reIt.first(); !reIt.isDone(); reIt.next()){ //逆向拓扑序列求各点 vl
            Vertex v = (Vertex)reIt.currentItem();
            Iterator adjIt = adjVertexs(v);
            for(adjIt.first(); !adjIt.isDone(); adjIt.next()){
                Vertex adjV = (Vertex)adjIt.currentItem();
                Edge e = edgeFromTo(v,adjV);
                if (getVL(v)>getVL(adjV)-e.getWeight()) //更新最迟开始时间
                    setVL(v, getVL(adjV)-e.getWeight());
            }
        }
        
        Iterator edIt = edges.elements();
        for(edIt.first(); !edIt.isDone(); edIt.next()){ //求关键活动
            Edge e = (Edge)edIt.currentItem();
            Vertex u = e.getFirstVex();
            Vertex v = e.getSecondVex();
            if (getVE(u)==getVL(v)-e.getWeight()) e.setToCritical();
        }
    }





//    DFS 深度 优先 遍历
//    public void topoSortByDFS() {
//        // 先构建逆邻接表，边s->t表示，s依赖于t，t先于s
//        LinkedList<Integer> inverseAdj[] = new LinkedList[v];
//        for (int i = 0; i < v; ++i) { // 申请空间
//            inverseAdj[i] = new LinkedList<>();
//        }
//        for (int i = 0; i < v; ++i) { // 通过邻接表生成逆邻接表
//            for (int j = 0; j < adj[i].size(); ++j) {
//                int w = adj[i].get(j); // i->w
//                inverseAdj[w].add(i); // w->i
//            }
//        }
//        boolean[] visited = new boolean[v];
//        for (int i = 0; i < v; ++i) { // 深度优先遍历图
//            if (visited[i] == false) {
//                visited[i] = true;
//                dfs(i, inverseAdj, visited);
//            }
//        }
//    }

//    private void dfs(int vertex, LinkedList<Integer> inverseAdj[], boolean[] visited) {
//        for (int i = 0; i < inverseAdj[vertex].size(); ++i) {
//            int w = inverseAdj[vertex].get(i);
//            if (visited[w] == true) continue;
//            visited[w] = true;
//            dfs(w, inverseAdj, visited);
//        } // 先把vertex这个顶点可达的所有顶点都打印出来之后，再打印它自己
//        System.out.print("->" + vertex);
//    }






    // 处理 查找 联系人 确定 是否 存在 环 引用   存在 环 引用 返回 void, 不存在 返回 referrer_id
//    HashSet<Integer> hashTable = new HashSet<>(); // 保存已经访问过的actorId
//    long findRootReferrerId(long actorId) {
//        if (hashTable.contains(actorId)) { // 存在环
//            return;
//        }
//        hashTable.add(actorId);
//        Long referrerId =
//                select referrer_id from [table] where actor_id = actorId;
//        if (referrerId == null) return actorId;
//        return findRootReferrerId(referrerId);
//    }
}
