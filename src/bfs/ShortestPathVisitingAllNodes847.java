package bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * dp[i][j]=n 表示从节点i开始经过一系列节点（j的二进制）需要的步骤。
 * https://leetcode.com/problems/shortest-path-visiting-all-nodes/discuss/135686/Java-DP-Solution
 */
public class ShortestPathVisitingAllNodes847 {
    public int shortestPathLength(int[][] graph) {
        int N = graph.length;
        Queue<State> queue = new LinkedList();
        int[][] dp = new int[N][1<<N];
        for (int x = 0; x < N; ++x) {
            Arrays.fill(dp[x],N*N);
            queue.offer(new State (x,1<<x));
            dp[x][1 << x] = 0;
        }

        while (!queue.isEmpty()) {
            State state = queue.poll();
            System.out.println(state.source+"\t"+state.mask);
            for(int next : graph[state.source]){
                int nextMask = state.mask | (1<<next) ;

                if(dp[next][nextMask]>dp[state.source][state.mask]+1){
                    dp[next][nextMask] = dp[state.source][state.mask]+1;
                    queue.offer(new State(next,nextMask));
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            res = Math.min(res,dp[i][(1<<N)-1]);
        }
        return res;
    }

    public static void main(String[] args){
        int[][] graph = new int[4][];
        graph[0] = new int[]{1,2,3};
        graph[1] = new int[]{0};
        graph[2] = new int[]{0};
        graph[3] = new int[]{0};
        int r = new ShortestPathVisitingAllNodes847().shortestPathLength(graph);
        System.out.println(r);
    }
}
class State {
    int mask, source;
    State(int source,int mask) {
        this.mask = mask;
        this.source = source;
    }
}