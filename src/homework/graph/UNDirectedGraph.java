package homework.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图
 */
public class UNDirectedGraph {
    //顶点个数
    private int v;

    private LinkedList<Integer>[] adj;

    public UNDirectedGraph(int v){
        this.v = v;
        this.adj = new LinkedList[v];
        for(int i=0;i<v;i++){
            this.adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s,int t){
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 广度优先搜索：搜索从s到t的最短路径，并且打印
     * @param s
     * @param t
     */
    public void bfs(int s,int t){

        boolean[] visited = new boolean[v];
        int[] pre = new int[v];//pre[i]表示到达i节点的前一个节点
        Arrays.fill(pre,-1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        boolean find = false;
        while(!queue.isEmpty()){
            int n = queue.poll();
            if(n==t){
                find = true;
                break;
            }
            if(adj[n]!=null){
                for(Integer to : adj[n]){
                    if(visited[to]){
                        continue;
                    }
                    pre[to] = n;
                    visited[to] = true;
                    queue.offer(to);
                }
            }
        }

        if(find){
            printPath(pre,s,t);
        }else{
            System.out.println("未找到");
        }
    }

    private void printPath(int[] pre, int s, int t) {
        if(s!=t){
            printPath(pre,s,pre[t]);
        }
        System.out.print(t+"->");
    }


    public static void main(String[] args){
        UNDirectedGraph ugraph = new UNDirectedGraph(8);
        ugraph.addEdge(0,1);
        ugraph.addEdge(0,3);
        ugraph.addEdge(1,2);
        ugraph.addEdge(1,4);
        ugraph.addEdge(3,4);
        ugraph.addEdge(2,5);
        ugraph.addEdge(4,5);
        ugraph.addEdge(4,6);
        ugraph.addEdge(5,7);
        ugraph.addEdge(6,7);

        ugraph.bfs(0,7);
    }
}
