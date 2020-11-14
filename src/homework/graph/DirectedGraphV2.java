package homework.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 有向有权图
 */
public class DirectedGraphV2 {
    private int v;//顶点数量
    private LinkedList<Edge> adj[];
    private Vertex[] vertexes;
    public DirectedGraphV2(int v){
        this.v = v;
        adj = new LinkedList[v];
        for(int i=0;i<v;i++){
            adj[i] = new LinkedList<Edge>();
        }
        vertexes = new Vertex[this.v];
    }

    public void addEdge(int sid,int tid){
        this.addEdge(sid,tid,1);
    }

    public void addEdge(int sid,int tid,int weight){
        Edge edge = new Edge(sid,tid,weight);
        adj[sid].add(edge);
    }

    public void addVertex(int id,int x,int y){
        vertexes[id] = new Vertex(id,x,y);
    }

    /**
     * 计算从s到t的最短路径
     * bfs
     * @param s
     * @param t
     */
    public void astar(int s,int t){
        vertexes[s].distance = 0;
        boolean[] inqueue = new boolean[this.v];
        inqueue[s] = true;
        int[] predecessor = new int[this.v];
        Arrays.fill(predecessor,-1);
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.offer(vertexes[s]);

        while(!queue.isEmpty()){
            Vertex vertex = queue.poll();
            for(int j = 0;j<adj[vertex.id].size();j++){
                Edge edge = adj[vertex.id].get(j);
                Vertex to = vertexes[edge.tid];
                if(vertex.distance + edge.weight < to.distance){
                    predecessor[edge.tid] = vertex.id;
                    to.distance = vertex.distance + edge.weight;
                    to.f = to.distance + hManhattan(to,vertex);
                    if(inqueue[to.id]){
                        queue.remove(to);
                        queue.offer(to);
                    }else{
                        queue.offer(to);
                        inqueue[to.id] = true;
                    }

                }
                if(to.id == t){
                    break;
                }
            }

        }
        printPath(predecessor,t);
    }

    /**
     * 计算两个顶点的曼哈顿距离
     * @param v1
     * @param v2
     * @return
     */
    private int hManhattan(Vertex v1, Vertex v2) {
        return Math.abs(v1.x - v2.x) + Math.abs(v1.y-v2.y);
    }

    /**
     * 如果执行A需要B，则有一条从B指向A的边
     * 把所有节点遍历一遍
     */
    public void topSortByKahn(){
        int[] inDegree = new int[this.v];
        for(int i=0;i<this.v;i++){
            for(Edge edge : this.adj[i]){
                inDegree[edge.tid]++;
            }
        }
        Queue<Integer> queue = new LinkedList<Integer>() ;
        for(int i=0;i<this.v;i++){
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int vertex = queue.poll();
            System.out.print(vertex+"->");
            for(Edge edge : this.adj[vertex]){
                inDegree[edge.tid]--;
                if(inDegree[edge.tid]==0){
                    queue.offer(edge.tid);
                }
            }
        }
    }

    private void printPath(int[] pre,int nowNode){
        if(pre[nowNode]!=-1){
            printPath(pre,pre[nowNode]);
        }
        System.out.print(nowNode+" ");
    }

    class Edge{
        private int sid;
        private int tid;
        private int weight;
        public Edge(int sid,int tid,int weight){
            this.sid = sid;
            this.tid = tid;
            this.weight = weight;
        }
    }

    class Vertex implements Comparable<Vertex>{
        private int id;
        private int distance;//从起始点到当前点的距离
        private int x,y;//坐标
        private int f;//排序的值
        public Vertex(int id,int x,int y){
            this.id = id;
            this.x = x;
            this.y = y;
            this.f = Integer.MAX_VALUE;
            this.distance = Integer.MAX_VALUE;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.f - o.f;
        }
    }
}
