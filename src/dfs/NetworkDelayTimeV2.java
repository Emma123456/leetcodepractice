package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkDelayTimeV2 {
	/**
	 * 我的问题是1：没有排序边；2 dfs的退出条件不对
	 * @param times
	 * @param N
	 * @param K
	 * @return
	 */
	public int networkDelayTime(int[][] times, int N, int K) {
        int[] costs = new int[N+1];
        Arrays.fill(costs,-1);
        Map<Integer, List<int[]>> map = new HashMap<Integer, List<int[]>>();
        for(int[] a : times){
            if( map.get(a[0])==null){
                map.put(a[0],new ArrayList<int[]>());
            }
            map.get(a[0]).add(new int[]{a[1],a[2]});
        }
        //按dist排序，可以优先选择距离短的边
        for (int node: map.keySet()) {//(a, b) -> a[0] - b[0]
            Collections.sort(map.get(node), new Comparator<int[]>(){
				public int compare(int[] o1, int[] o2) {
					return o1[1]-o2[1];
				}
            	
            });
        }
        visitNode(map,costs,K,0);
        int max = 0;
        for(int i=1;i<=N;i++){
            if(costs[i] == -1) return -1;
            max = Math.max(max, costs[i]);
        }
        return max;
            
    }
    
    public void visitNode(Map<Integer,List<int[]>> timeMap,int[] costs,int node,int cost){
        if(costs[node]!=-1){
            if(cost >= costs[node]){
            	return;
            }
        }
        costs[node] = cost;
        if(timeMap.get(node)!=null){
            for(int[] edge :timeMap.get(node)){
                visitNode(timeMap,costs,edge[0],edge[1]+cost);
            }
        }
    }
}
