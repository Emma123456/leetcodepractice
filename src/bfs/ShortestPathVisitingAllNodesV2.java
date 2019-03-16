package bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathVisitingAllNodesV2 {
    public static void main(String[] args){
        int[][] graph = new int[4][];
        graph[0] = new int[]{1,2,3};
        graph[1] = new int[]{0};
        graph[2] = new int[]{0};
        graph[3] = new int[]{0};

        int r = new ShortestPathVisitingAllNodesV2().shortestPathLengthBFS(graph);
        System.out.println(r);
    }
    public int shortestPathLengthBFS(int[][] graph) {
        int N = graph.length;
        Queue<State> queue = new LinkedList();
        int[][] dist = new int[1<<N][N];
        for (int[] row: dist) Arrays.fill(row, N*N);

        for(int startNode=0;startNode<N;startNode++){
            dist[1<<startNode][startNode] = 0;
            queue.offer(new State(1<<startNode,startNode));
        }

        //BFS遍历
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                State node = queue.poll();
                int d = dist[node.cover][node.head];
                if(node.cover == (1<<N)-1) return d;

                for(int child : graph[node.head]){
                    int newCover = node.cover | 1 << child;
                    if(dist[newCover][child]>d+1){
                        dist[newCover][child] =  d +1;
                        queue.offer(new State(newCover,child));
                    }
                }
            }
        }

        throw null;
    }


    private int minDis = Integer.MAX_VALUE;
    public int shortestPathLengthDFS(int[][] graph) {
        int N = graph.length;
        int[][] dist = new int[1<<N][N];
        for (int[] row: dist) Arrays.fill(row, N*N);

        int startNode = 0;
        dist[1<<startNode][startNode] = 0;
        dfs(new State(1<<startNode,startNode),graph,N,dist);

        return minDis;
    }

    private void dfs(State state,int[][] graph,int N,int[][] dist) {
        if(state.cover == (1<<N)-1){
            minDis = Math.min(minDis,dist[state.cover][state.head]);
        }else{
            for(int child : graph[state.head]){
                int newCover = state.cover | 1 << child;
                if(dist[newCover][child]>dist[state.cover][state.head]+1){
                    dist[newCover][child] = dist[state.cover][state.head]+1;
                    dfs(new State(newCover,child),graph,N,dist);
                }
            }
        }
    }


    class State {
        int cover, head;
        State(int c, int h) {
            cover = c;
            head = h;
        }
    }
}
