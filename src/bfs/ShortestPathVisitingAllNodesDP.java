package bfs;

import java.util.Arrays;

public class ShortestPathVisitingAllNodesDP {
    private int[][] dis = new int[15][15];
    private int[][] dp =new int[1<<13][13];
    public int shortestPathLength(int[][] graph) {
        int N = graph.length;
        for (int[] row: dis) Arrays.fill(row, N*N);
        for (int i=0; i<N; i++) {
            for (int j=0; j<graph[i].length; j++) {
                int u = i, v = graph[i][j];
                dis[u][v] = 1;
            }
        }
        floyd(N);
        return dp(N);
    }

    /**
     * Floy算法：任意两点之间的最短距离
     * @param n
     */
    public void floyd(int n) {
        for(int k=0; k<n; k++)
            for(int i=0; i<n; i++)
                for(int j=0; j<n; j++)
                    dis[i][j] = Math.min(dis[i][j], dis[i][k]+dis[k][j]);
    }

    private int dp(int n) {
        for (int[] row: dp) Arrays.fill(row, n*n);
        for (int i=0; i<n; i++)
            dp[1<<i][i] = 0;
        for (int group=1; group<(1<<n); group++)
            for (int u=0; u<n; u++)
                for (int v=0; v<n; v++) {
                    int src = 1 << u, dst = 1 << v;
                    //group包含src，但是不包含dst
                    if ((group & src)!=0 && (group & dst)==0 )
                        dp[group|dst][v] = Math.min(dp[group][u] + dis[u][v], dp[group|dst][v]);
                }
        int minDis = 0x3f3f3f3f;
        for (int i=0; i<n; i++)
            minDis = Math.min(dp[(1<<n)-1][i], minDis);
        return minDis;
    }


    public static void main(String[] args){
        int[][] graph = new int[4][];
        graph[0] = new int[]{1,2,3};
        graph[1] = new int[]{0};
        graph[2] = new int[]{0};
        graph[3] = new int[]{0};

        int r = new ShortestPathVisitingAllNodesDP().shortestPathLength(graph);
        System.out.println(r);
    }
}
