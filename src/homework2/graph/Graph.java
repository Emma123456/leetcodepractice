package homework2.graph;

import java.util.*;

public class Graph {
    //顶点数量
    private int v;
    //链接表
    private LinkedList<Edge> adjacency[];


    /**
     * 构造顶点数为v的图
     * @param v
     */
    public Graph(int v) {
        this.v = v;
        adjacency = new LinkedList[v];
        for(int i=0;i<v;i++){
            adjacency[i] =  new LinkedList<>();
        }
    }

    /**
     * 添加一条边
     * @param sid
     *          源节点id
     * @param tid
     *          目的节点id
     * @param weight
     *          权重
     */
    public void addEdge(int sid, int tid,int weight){
        Edge edge = new Edge(sid,tid,weight);
        adjacency[sid].add(edge);
    }


    public void addEdge(int sid, int tid){
        this.addEdge(sid,tid,1);
    }

    /**
     * 计算从sid到tid的最短路径。返回值是经过路径的节点id
     * @param sid
     * @param tid
     * @return
     */
    public List<Integer> dijkstrala(int sid ,int tid){
        Vertext[] vertexts = new Vertext[v];
        for(int i=0; i < v;i++){
            vertexts[i] = new Vertext(i);
        }
        vertexts[sid].dist = 0;

        //predecessor[i]=得到i的前驱节点
        int[] predecessor = new int[v];
        predecessor[sid] = -1;
        PriorityQueue<Vertext> queue = new PriorityQueue<>(this.v,
                new Comparator<Vertext>(){
                    public int compare(Vertext o1, Vertext o2){
                        return o1.dist - o2.dist;
                    }
                });
        //队列中可能存在重复节点,会存在多余操作
        queue.offer(vertexts[sid]);
        while(! queue.isEmpty()){
            Vertext node = queue.poll();
            if(node.id == tid) break;
            if(adjacency[node.id]!=null){
                for(Edge edge : adjacency[node.id]){
                    int to = edge.tid;
                    if(vertexts[node.id].dist + edge.weight < vertexts[to].dist){
                        vertexts[to].dist = vertexts[node.id].dist + edge.weight;
                        queue.add(vertexts[to]);
                        predecessor[to] = node.id;
                    }
                }
            }
        }
        List<Integer> path = new ArrayList<Integer>();
        if(vertexts[tid].dist < Integer.MAX_VALUE){
            visitPredecessor(predecessor,tid,path);
            Collections.reverse(path);
        }
        return path;
    }


    private void visitPredecessor(int[] predecessor, int tid,List<Integer> path) {
        path.add(tid);
        if(predecessor[tid] != -1){
            visitPredecessor(predecessor,predecessor[tid],path);
        }
    }

    /**
     * 拓扑排序
     * @return
     */
    public List<Integer> topSortByKahn(){
        int[] inDegree = new int[v];
        for(int i = 0; i< adjacency.length; i++){
            for(Edge edge : adjacency[i]){
                inDegree[edge.tid] ++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<inDegree.length;i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }

        List<Integer> path = new ArrayList<>();
        while(! queue.isEmpty()){
            int node = queue.poll();
            path.add(node);
            for(Edge edge : adjacency[node]){
                inDegree[edge.tid]--;
                if(inDegree[edge.tid] == 0){
                    queue.offer(edge.tid);
                }
            }
        }
        return path;
    }

    public List<Integer> topSortByDFS(){
        LinkedList<Integer>[] inverseAdg = new LinkedList[this.v];
        for(int i = 0; i< adjacency.length; i++){
            inverseAdg[i] = new LinkedList<>();
        }
        for(int i = 0; i< adjacency.length; i++){
            for(Edge edge : adjacency[i]){
                inverseAdg[edge.tid].add(edge.sid);
            }
        }
        boolean[] visited = new boolean[v];
        List<Integer> path = new ArrayList<>();
        for(int i=0;i<this.v;i++){
            if(visited[i] == false){
                dfs(i,inverseAdg,visited,path);
            }
        }
        return path;
    }

    private void dfs(int sid, LinkedList<Integer>[] inverseAdg, boolean[] visited,List<Integer> path) {
        visited[sid] = true;
        for(int tid   : inverseAdg[sid]){
            if(visited[tid] == false){
                dfs(tid,inverseAdg,visited,path);
            }
        }
        path.add(sid);
    }

    private class Vertext{
        //编号
        private int id;
        //距离
        private int dist = Integer.MAX_VALUE;

        public Vertext(int id) {
            this.id = id;
        }
    }
    private class Edge{
        private int sid;
        private int tid;
        private int weight;

        public Edge(int sid, int tid, int weight) {
            this.sid = sid;
            this.tid = tid;
            this.weight = weight;
        }
    }
}
