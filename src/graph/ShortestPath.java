package graph;

import java.util.*;

public class ShortestPath {
    /**
     *
     * bellman=ford算法计算从startNode到各个节点的最短距离。如果有负权环则返回null。
     * 之所以需要N-1遍，是因为边的顺序是无序的
     * 时间复杂度O(VE)
     * @param weight
     *           graph[i][j]表示从i到j有边
     * @param startNode
     *
     * @param N
     *          节点数量
     * @return
     */
    public int[] bellmanFord(int[][] weight,int startNode,int N){
        Graph graph = new Graph();
        graph.v = N;
        graph.e = weight.length;
        for(int[] a : weight){
            Edge edge = new Edge();
            edge.src = a[0];
            edge.dest = a[1];
            edge.weight = a[2];
            graph.edgeList.add(edge);
        }

        int[] dist = new int[N];//i到startNode的最短距离
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[startNode] = 0;


        for(int i=1;i<N;i++){
            boolean update = false;
            for(int j=0;j<graph.e;j++){
                Edge edge = graph.edgeList.get(j);
                if(dist[edge.src]!=Integer.MAX_VALUE && dist[edge.dest]> dist[edge.src]+edge.weight){
                    dist[edge.dest] = dist[edge.src]+edge.weight;
                    update = true;
                }
            }
            if(!update){
                break;
            }
        }

        boolean isBack = false;
        for(int i=0;i<graph.e;i++){
            Edge edge = graph.edgeList.get(i);
            if (dist[edge.src]!=Integer.MAX_VALUE && dist[edge.src] + edge.weight<dist[edge.dest]){
                isBack = true;
            }
        }
        return isBack?null:dist;
    }

    public static void main(String[] args){
        int[][] weight = new int[9][];
        weight[2] = new int[]{0,1,-1};
        weight[1] = new int[]{0,2,4};
        weight[0] = new int[]{1,2,3};
        weight[3] = new int[]{1,3,2};
        weight[4] = new int[]{1,4,2};
        weight[5] = new int[]{3,2,5};
        weight[6] = new int[]{3,1,1};
        weight[7] = new int[]{4,3,-3};
        weight[8] = new int[]{5,6,2};
        int[] dist = new ShortestPath().bellmanFord(weight,0,7);
        if(dist!=null){
            for(int i=0;i<dist.length;i++){
                System.out.print(dist[i]+"\t");
            }
        }else {
            System.out.println("有回路");
        }
    }
}

class Graph{
    int v;
    int e;
    List<Edge> edgeList = new ArrayList<>();
}
class Edge{
    int src;
    int dest;
    int weight;

}