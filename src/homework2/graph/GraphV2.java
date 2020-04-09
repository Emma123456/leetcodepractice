package homework2.graph;

import java.util.*;

public class GraphV2 {
    //顶点数量
    private int v;
    //链接表
    private LinkedList<Edge> adjacency[];
    //顶点
    private Vertext[] vertexts;

    /**
     * 构造顶点数为v的图
     * @param v
     */
    public GraphV2(int v) {
        this.v = v;
        adjacency = new LinkedList[v];
        for(int i=0;i<v;i++){
            adjacency[i] =  new LinkedList<>();
        }
        vertexts = new Vertext[v];
    }

    /**
     * 添加顶点
     * @param id
     * @param x
     * @param y
     */
    public void addVertex(int id, int x, int y){
        vertexts[id] = new Vertext(id,x,y);
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

    /**
     * 计算从sid到tid的最短路径。返回值是经过路径的节点id。A星算法。
     * @param sid
     * @param tid
     * @return
     */
    public List<Integer> astar(int sid ,int tid){
        vertexts[sid].dist = 0;
        vertexts[sid].f = 0;
        //predecessor[i]=得到i的前驱节点
        int[] predecessor = new int[v];
        predecessor[sid] = -1;
        PriorityQueue<Vertext> queue = new PriorityQueue<>(this.v,
                new Comparator<Vertext>(){
                    public int compare(Vertext o1, Vertext o2){
                        //变化2
                        return o1.f - o2.f;
                    }
                });
        //队列中可能存在重复节点,会存在多余操作
        queue.offer(vertexts[sid]);
        while(! queue.isEmpty()){
            Vertext node = queue.poll();
            if(adjacency[node.id]!=null){
                for(Edge edge : adjacency[node.id]){
                    int to = edge.tid;
                    if(vertexts[node.id].dist + edge.weight < vertexts[to].dist){
                        vertexts[to].dist = vertexts[node.id].dist + edge.weight;
                        //变化3
                        vertexts[to].f = vertexts[to].dist + hManhattan(node.id, to);
                        queue.add(vertexts[to]);
                        predecessor[to] = node.id;
                    }
                    //变化1
                    if(to == tid){
                        queue.clear();
                        break;
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
     * 计算两个顶点的曼哈顿距离
     * @param id1
     * @param id2
     * @return
     */
    private int hManhattan(int id1,int id2){
        return Math.abs(vertexts[id1].x - vertexts[id2].x) + Math.abs(vertexts[id2].y - vertexts[id2].y);
    }

    private class Vertext{
        //编号
        private int id;
        //距离
        private int dist = Integer.MAX_VALUE;
        //顶点在地图中的横坐标
        private int x;
        //顶点在地图中的纵坐标
        private int y;
        //f = g(i) + h(i)
        private int f;
        public Vertext(int id,int x,int y) {
            this.id = id;
            this.x = x;
            this.y = y;
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
