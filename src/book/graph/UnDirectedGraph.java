package book.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图
 */
public class UnDirectedGraph {

    private int v;//顶点个数
    private LinkedList<Integer> adj[];//邻接表
    public UnDirectedGraph(int v){
        this.v = v;
        this.adj = new LinkedList[v];
        for(int i=0;i<v;i++){
            this.adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s,int t){
        this.adj[s].add(t);
        this.adj[t].add(s);
    }

    /**
     * 广度优先搜索从s节点到t节点:打印从s到t的节点路径
     * @param s
     * @param t
     */
    public void bfs(int s, int t) {
        if(s==t){
            System.out.println(s);
            return;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);

        boolean[] visited = new boolean[this.v];
        visited[s] = true;

        int[] pre = new int[v];
        Arrays.fill(pre,-1);

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i =0;i<size;i++){//每一层
                int w = queue.poll();
                for(int j =0;j<this.adj[w].size();j++){
                    int q = this.adj[w].get(j);
                    if(!visited[q]){
                        pre[q] = w;
                        if(q==t){
                            printPath(pre,s,t);
                            return;
                        }
                        visited[q]=true;
                        queue.offer(q);
                    }
                }
            }
        }
    }

    private void printPath(int[] pre,int s,int t) {
        if(s!=t && pre[t]!=-1){
            printPath(pre,s,pre[t]);
        }
        System.out.print(t+"\t");
    }

    private boolean found = false;
    public void dfs(int s, int t) {
        boolean[] visited = new boolean[this.v];
        int[] pre = new int[v];
        Arrays.fill(pre,-1);
        dfs(s,t,visited,pre);
        printPath(pre,s,t);

    }

    private void dfs(int w, int t, boolean[] visited, int[] pre) {
        if(w==t){
            found = true;
            return;
        }
        visited[w] = true;
        if(!found){
            for(int j =0;j<this.adj[w].size() && !found;j++){
                int q = this.adj[w].get(j);
                if(!visited[q]) {
                    pre[q] = w;
                    visited[q]=true;
                    dfs(q,t,visited,pre);
                }
            }

        }

    }
}
