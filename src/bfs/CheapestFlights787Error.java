package bfs;

import java.util.*;

/***
 * 首先这个版本超时了，其次这个版本的逻辑是错误。
 *
 * 按照题目给的例子，从src出发，如果src能到dst，则直接计算成本，用于和最终结果比较。否则，计算新的成本，加入到队列中，并且K--。
 * 这样的想法在给定例子中没有问题。从最终结果看，例如从1到2的成本最低，那首先要计算出到达1的最低成本。我的思维中并没有记录到达1的最低成本。再一次错误：如果那个最低成本的停战数+1，超过了K怎么办？
 */
public class CheapestFlights787Error {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        //转graph
        Map<Integer,List<Edge>> graph = new HashMap<Integer,List<Edge>>();
        for(int i =0;i<flights.length;i++){
            int s = flights[i][0];
            if(graph.get(s)==null){
                graph.put(s,new ArrayList<Edge>());
            }
            graph.get(s).add(new Edge(s,flights[i][1],flights[i][2]));
        }
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{src,0});
        int minCost = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int[] vals = queue.poll();
                int s = vals[0];
                int cost = vals[1];
                if(graph.get(s)!=null){

                    for(Edge edge : graph.get(s)){
                        if(edge.dest == dst){
                            minCost = minCost>cost+edge.cost?cost+edge.cost:minCost;
                        }else if(K>0){
                            queue.offer(new int[]{edge.dest,cost+edge.cost});
                        }
                    }
                }
            }
            K--;
        }
        return minCost == Integer.MAX_VALUE?-1:minCost;
    }

    class Edge{
        int src;
        int dest;
        int cost;
        public Edge(int src,int dest,int cost){
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }
    }

}
