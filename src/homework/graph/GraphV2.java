package homework.graph;

import java.util.Arrays;
import java.util.LinkedList;

public class GraphV2 {
    private int v;//顶点个数
    private LinkedList<Edge> adg[];//邻接表
    private Vertex[] vertexes;
    public GraphV2(int v){
        this.v = v;
        vertexes = new Vertex[this.v];
        this.adg = new LinkedList[v];
        for(int i=0;i<v;i++){
            adg[i] = new LinkedList<>();
        }
    }
    public void addEdge(int s,int t,int w){
        this.adg[s].add(new Edge(s,t,w));
    }

    public void addVertex(int id,int x,int y){
        vertexes[id] = new Vertex(id,x,y);
    }

    /**
     * 使用A星算法，计算从s到t的近似最短路径
     * 时间复杂度 :
     * @param s
     * @param t
     */
    public void astar(int s,int t){
        int[] predecessor = new int[v];
        Arrays.fill(predecessor,-1);
        vertexes[s].dist = 0;
        vertexes[s].f = 0;
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
                    nextVertex.f = nextVertex.dist+hManhattan(nextVertex,vertexes[t]);
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

    private void printPath(int[] pre,int s,int t) {
        if(s!=t && pre[t]!=-1){
            printPath(pre,s,pre[t]);
        }
        System.out.print(t+"\t");
    }

    private int hManhattan(Vertex v1, Vertex v2){
        return Math.abs(v1.x - v2.x)+Math.abs(v1.y-v2.y);
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
                if(data.f>nodes[parent].f){
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
                if(data.f < nodes[leftIdx].f){
                    maxPos = leftIdx;
                }
                if(rightIdx< size  && nodes[rightIdx].f > nodes[maxPos].f){
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
        public int f;//f(i)=g(i)+h(i)
        public int x,y;//顶点在地图中的坐标(x,y)
        public Vertex(int id,int x,int y){
            this.id = id;
            this.dist = Integer.MAX_VALUE;
            this.x = x;
            this.y = y;
            this.f = Integer.MAX_VALUE;
        }
    }
}
