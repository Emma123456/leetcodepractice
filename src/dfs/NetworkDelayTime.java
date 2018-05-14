package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class NetworkDelayTime {
	private int G[][] = new int[105][105];
	private int dist[] = new int[105];
	private boolean[] s = new boolean[105];

	/**
	 * Dijkstra's Algorithm
	 * 时间复杂度O(N^2)
	 * @param times
	 * @param N
	 * @param k
	 * @return
	 */
	public int networkDelayTime(int[][] times, int N, int k) {
		build(times);
		dijkstra(k, N);
		return result(N);
	}

	private int result(int N) {
		int longestTime = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			longestTime = Math.max(longestTime, dist[i]);
		}
		return longestTime == Integer.MAX_VALUE ? -1 : longestTime;
	}

	private void dijkstra(int v, int N) {
		//dist存储从v到各个顶点的最短距离
		for (int i = 1; i <= N; i++) {
			dist[i] = G[v][i];
		}
		dist[v] = 0;
		s[v] = true;
		for (int i = 2; i <= N; i++) {// 遍历的次数
			int minDist = Integer.MAX_VALUE;
			int minIndex = 0;
			for (int j = 1; j <= N; j++) {
				if (s[j] == false && dist[j] < minDist) {
					minDist = dist[j];
					minIndex = j;
				}
			}
			s[minIndex] = true;//找到当前距离最小的那个节点
			for (int j = 1; j <= N; j++) {
				if (s[j] == false && G[minIndex][j] != Integer.MAX_VALUE && dist[minIndex] + G[minIndex][j] < dist[j]) {
					dist[j] = dist[minIndex] + G[minIndex][j];
				}
			}
		}
	}

	private void build(int[][] times) {
		 for(int i=0;i<G.length;i++){
			 Arrays.fill(G[i], Integer.MAX_VALUE);
		 }
		for (int i = 0; i < times.length; i++) {
			G[times[i][0]][times[i][1]] = times[i][2];
		}
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(s, false);
	}
	
	/**
	 * Dijkstra's Algorithm 用堆实现的版本
	 * @param times
	 * @param N
	 * @param k
	 * @return
	 */
	public int networkDelayTimeV2(int[][] times, int N, int k) {
		Map<Integer, List<int[]>> graph = new HashMap<Integer, List<int[]>>();
		for(int[] a : times){
            if( graph.get(a[0])==null){
            	graph.put(a[0],new ArrayList<int[]>());
            }
            graph.get(a[0]).add(new int[]{a[1],a[2]});
        }
		PriorityQueue<int[]> heap = new PriorityQueue<int[]>(N,new Comparator<int[]>(){
			public int compare(int[] o1, int[] o2) {
				return o1[1]-o2[1];
			}
        	
        });
		heap.offer(new int[]{k,0});
		int[] dist = new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        while(!heap.isEmpty()){
        	int[] info = heap.poll();
        	int d = info[1];
        	int node = info[0];
        	if(dist[node]!=Integer.MAX_VALUE) continue;
        	dist[node] = d;
        	if(graph.containsKey(node)){
        		 for(int[] edge :graph.get(node)){
        			 if(dist[edge[0]]==Integer.MAX_VALUE){
        				 heap.offer(new int[]{edge[0],edge[1]+d});
        			 }
                 }
        	}
        }
        int max = 0;
        for(int i=1;i<=N;i++){
            if(dist[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, dist[i]);
        }
        return max;
	}
	public static void main(String[] args) {
		int[][] times = new int[][]{{2,1,1},{2,3,1},{3,4,1}};
		int r = new NetworkDelayTime().networkDelayTime(times, 4, 2);
		System.out.println(r);
	}
}
