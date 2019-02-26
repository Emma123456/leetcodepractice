package bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CheapestFlights787DP {
    /**
     * 学习了带权重的有向图的表示方式
     * 注意1 在有限的步骤范围内
     * 注意2 计算成本的数组cost，需要有两个。因为例如队列中有节点v5，v4.如果v5能达到v4，那么再计算v4到达其目的节点的值就会出错。
     * 注意3 在同一步计算中可能有两个节点同时到达节点vi。newCost[i]需要保存最小的值。
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param K
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] cost = new int[n];
        int[][] graph = new int[n][n];
        for(int[] flight : flights){
            graph[flight[0]][flight[1]] = flight[2];
        }
        Arrays.fill(cost,Integer.MAX_VALUE);
        cost[src] = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(src);
        while(K>=0){
            int[] newCost = Arrays.copyOf(cost,cost.length);
            int size = queue.size();
            for(int i=0;i<size;i++){
                int node = queue.poll();
                for(int j=0;j<n;j++){
                    if(graph[node][j]==0) continue;
                    int newValue = cost[node]+graph[node][j];
                    if(newCost[j]>newValue){
                        newCost[j] = newValue;
                        queue.offer(j);
                    }
                }

            }
            K--;
            cost = newCost;
        }
        return cost[dst]==Integer.MAX_VALUE?-1:cost[dst];
    }
}
