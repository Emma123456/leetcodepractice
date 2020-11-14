package homework.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 有向有权图
 */
public class DirectedGraph {
    private int v;//顶点数量
    private LinkedList<Edge> adj[];
    public DirectedGraph(int v){
        this.v = v;
        adj = new LinkedList[v];
        for(int i=0;i<v;i++){
            adj[i] = new LinkedList<Edge>();
        }
    }

    public void addEdge(int sid,int tid){
        this.addEdge(sid,tid,1);
    }

    public void addEdge(int sid,int tid,int weight){
        Edge edge = new Edge(sid,tid,weight);
        adj[sid].add(edge);
    }

    /**
     * 计算从s到t的最短路径
     * bfs
     * @param s
     * @param t
     */
    public void dijkstrala(int s,int t){
        Vertex[] vertexes = new Vertex[v];
        for(int i=0;i<this.v;i++){
            vertexes[i] = new Vertex(i,Integer.MAX_VALUE);
        }
        vertexes[s].distance = 0;
        boolean[] inqueue = new boolean[this.v];
        inqueue[s] = true;
        int[] predecessor = new int[this.v];
        Arrays.fill(predecessor,-1);
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.offer(vertexes[s]);

        while(!queue.isEmpty()){
            Vertex vertex = queue.poll();
            if(vertex.id == t){
                break;
            }
            for(int j = 0;j<adj[vertex.id].size();j++){
                Edge edge = adj[vertex.id].get(j);
                Vertex to = vertexes[edge.tid];
                if(vertex.distance + edge.weight < to.distance){
                    predecessor[edge.tid] = vertex.id;
                    to.distance = vertex.distance + edge.weight;
                    if(inqueue[to.id]){
                        queue.remove(to);
                        queue.offer(to);
                    }else{
                        queue.offer(to);
                        inqueue[to.id] = true;
                    }

                }
            }

        }
        printPath(predecessor,t);
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
        public Vertex(int id,int distance){
            this.id = id;
            this.distance = distance;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.distance - o.distance;
        }
    }
}
