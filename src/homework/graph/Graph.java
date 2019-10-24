package homework.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private int v;//顶点个数
    private LinkedList<Edge> adg[];//邻接表
    public Graph(int v){
        this.v = v;
        this.adg = new LinkedList[v];
        for(int i=0;i<v;i++){
            adg[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s,int t){
        this.addEdge(s,t,1);
    }
    public void addEdge(int s,int t,int w){
        this.adg[s].add(new Edge(s,t,w));
    }

    /**
     * 使用Dijstra算法计算从s到t的最短路径
     * 时间复杂度 :O（E*logV）
     * @param s
     * @param t
     */
    public void dijkstrala(int s,int t){
        int[] predecessor = new int[v];
        Arrays.fill(predecessor,-1);
        Vertex[] vertexes = new Vertex[v];
        for(int i=0;i<this.v;i++){
            vertexes[i] = new Vertex(i,Integer.MAX_VALUE);
        }
        vertexes[s].dist = 0;
        PriorityQueue queue = new PriorityQueue(this.v);
        queue.offer(vertexes[s]);
        boolean[] inqueues = new boolean[v];//标记哪些节点进入过队列
        inqueues[s] = true;

        while(!queue.isEmpty()){
            Vertex minVertex = queue.poll();//获取堆顶元素并且删除
            for(int i=0;i<adg[minVertex.id].size();i++){
                Edge edge = adg[minVertex.id].get(i);
                Vertex nextVertex = vertexes[edge.tid];
                if(minVertex.dist + edge.w < nextVertex.dist){
                    nextVertex.dist = minVertex.dist + edge.w;
                    predecessor[nextVertex.id] = minVertex.id;
                    if(inqueues[nextVertex.id]){
                        queue.update(nextVertex);
                    }else{
                        inqueues[edge.tid] = true;
                        queue.offer(nextVertex);
                    }

                }
            }
        }

        printPath(predecessor,s,t);
    }


    /**
     * 使用Kaha算法实现拓扑排序：能够输出按照依赖关系，顶点的执行顺序
     * 这个算法的本质是一个BFS
     */
    public void topSortByKahn(){
        //计算每个顶点的入度
        int[] degree = new int[this.v];
        for(int i=0;i<this.adg.length;i++){
            for(int j=0;j<adg[i].size();j++){
                degree[adg[i].get(j).tid]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<degree.length;i++){
            if(degree[i]==0){
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            int sid = queue.poll();
            System.out.print(sid+"->");
            for(Edge edge: adg[sid]){
                degree[edge.tid]--;
                if(degree[edge.tid]==0){
                    queue.offer(edge.tid);
                }
            }
        }
    }

    public void topSortByDFS(){
        //逆邻接矩阵
        LinkedList<Integer>[] inverseAdg = new LinkedList[this.v];
        boolean[] visited = new boolean[v];
        for(int i=0;i<v;i++){
            inverseAdg[i] = new LinkedList<>();
        }
        for(int i=0;i<v;i++){
            for(Edge edge : adg[i]){
                inverseAdg[edge.tid].add(i);
            }
        }
        System.out.println();
        for(int i=0;i<visited.length;i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(inverseAdg,visited,i);
            }
        }

    }

    private void dfs(LinkedList<Integer>[] inverseAdg, boolean[] visited, int sid) {

        for(Integer tid : inverseAdg[sid]){
            if(!visited[tid]){
                visited[tid] = true;
                dfs(inverseAdg,visited,tid);
            }

        }
        System.out.print(sid+"->");

    }

    private void printPath(int[] pre,int s,int t) {
        if(s!=t && pre[t]!=-1){
            printPath(pre,s,pre[t]);
        }
        System.out.print(t+"\t");
    }

    private class PriorityQueue{
        private Vertex[] nodes;
        private int count;
        private int size;
        public PriorityQueue(int count){
            this.nodes = new Vertex[count];
            this.count = count;
        }

        /**
         * 返回队首元素
         * @return
         */
        public Vertex poll(){
            Vertex obj = null;
            if(!isEmpty()){
                size--;
                obj = nodes[0];
                nodes[0] = nodes[size];
                nodes[size] = null;
                siftDown(nodes[0],0);
            }
            return  obj;
        }

        public void offer(Vertex vertex){
            size++;
            nodes[size-1] = vertex;
            siftUp(vertex,size-1);
        }

        /**
         * 更新节点的值，并且从下往上堆化，重新符合堆定义
         * @param vertex
         */
        public void update(Vertex vertex){
            for(int i=0;i<size;i++){
                if(nodes[i].id == vertex.id){
                    //dist变小
                    nodes[i] = vertex;
                    siftUp(vertex,i);
                }
            }
        }

        public boolean isEmpty(){
            return size==0;
        }


        /**
         * 沿着路径向上堆化
         * @param data
         * @param k
         */
        private void siftUp(Vertex data, int k) {
            while(k>0){
                int parent = (k-1)>>>1;
                if(data.dist>nodes[parent].dist){
                    nodes[k] = nodes[parent];

                }else{
                    break;
                }
                k = parent;
            }
            nodes[k] = data;
        }

        private void siftDown(Vertex data, int k) {
            int half = size>>>1;
            while(k<half){
                int leftIdx = (k<<1)+1;
                int rightIdx = leftIdx+1;
                int maxPos = k;
                if(data.dist < nodes[leftIdx].dist){
                    maxPos = leftIdx;
                }
                if(rightIdx< size  && nodes[rightIdx].dist > nodes[maxPos].dist){
                    maxPos = rightIdx;
                }
                if(maxPos==k){
                    break;
                }
                nodes[k] = nodes[maxPos];
                nodes[maxPos] = data;
                k=maxPos;
            }
            nodes[k] = data;
        }
    }
    private class Edge{
        public int sid;//起始顶点编号
        public int tid;//终止顶点编号
        public int w;//权重
        public Edge(int sid,int tid,int w){
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }
    private class Vertex{
        public int id;//顶点编号
        public int dist;//从起始顶点到当前顶点的距离

        public Vertex(int id,int dist){
            this.id = id;
            this.dist = dist;
        }
    }
}
