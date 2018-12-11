package bfs;

import java.util.*;

public class NetworkDelayTime743 {
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] dist = new int[N+1];
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>(N,new Comparator<int[]>(){
            public int compare(int[] edge1, int[] edge2) {
                return edge1[1]-edge2[1];
            }
        });

        Map<Integer,List<int[]>> graph = new HashMap<Integer,List<int[]>>();
        for(int[] edge : times){
            if(!graph.containsKey(edge[0])){
                graph.put(edge[0],new ArrayList<int[]>());
            }
            graph.get(edge[0]).add(new int[]{edge[1],edge[2]});
        }

        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[K] = 0;
        Set<Integer> seen = new HashSet<Integer>();
        minHeap.offer(new int[]{K,0});

        while(!minHeap.isEmpty()){
            int[] curr = minHeap.poll();
            if(!seen.contains(curr[0])){
                if(graph.get(curr[0])!=null){
                    for(int[] edge : graph.get(curr[0])){
                        if(dist[edge[0]] > curr[1]+edge[1]){
                            dist[edge[0]] = curr[1]+edge[1];
                        }
                        minHeap.offer(new int[]{edge[0],dist[edge[0]]});
                    }
                }
                seen.add(curr[0]);
            }
        }

        int answer = 0;
        for(int i=1;i<=N;i++){
            if(dist[i]==Integer.MAX_VALUE) return -1;
            answer = Math.max(answer,dist[i]);
        }
        return  answer;
    }
    public static void main(String[] args){
        int[][] times = new int[][]{new int[]{2,1,1},new int[]{2,3,1},new int[]{3,4,1}};
        int N =4,K=2;
        new NetworkDelayTime743().networkDelayTime(times,N,K);
    }

}