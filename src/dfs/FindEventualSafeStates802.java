package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class FindEventualSafeStates802 {
	/**
	 * 超时了
	 * @param graph
	 * @return
	 */
	public List<Integer> eventualSafeNodes(int[][] graph) {
        if(graph==null || graph.length==0) return new ArrayList<Integer>();
        int n = graph.length;
        int[] colors = new int[n];
        List<Integer> safeState = new ArrayList<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();
        Map<Integer,List<Integer>> inMap = new HashMap<Integer,List<Integer>>();
        for(int i=0;i<graph.length;i++){
            if(graph[i].length==0){
                colors[i] = 1;
                queue.add(i);
            }
            for(int toIdx : graph[i]){
                if(inMap.get(toIdx)==null){
                    inMap.put(toIdx,new ArrayList<Integer>());
                }
                inMap.get(toIdx).add(i);
            }
            if(inMap.get(i)==null){
                inMap.put(i,new ArrayList<Integer>());
            }
        }
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int fromIdx : inMap.get(node)){
                boolean safe = true;
                for(int i=0;i<graph[fromIdx].length;i++){
                    if(colors[graph[fromIdx][i]]!=1){
                        safe = false;
                        break;
                    }
                }
                if(safe){
                	colors[fromIdx] = 1;
                	queue.add(fromIdx);
                }
            }
        }
        for(int i=0;i<colors.length;i++){
        	if(colors[i] == 1){
        		safeState.add(i);
        	}
        }
        return safeState;
    }
	
	/**
	 * 相比较与上一个版本的改进之处是：添加outGraphSet，能够确定最多比较次数=边的数量。
	 * @param graph
	 * @return
	 */
	public List<Integer> eventualSafeNodesV2(int[][] graph) {
		if(graph==null || graph.length==0) return new ArrayList<Integer>();
        int n = graph.length;
        int[] colors = new int[n];
        List<Integer> safeState = new ArrayList<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();
        List<Set<Integer>> outGraphSet = new ArrayList<Set<Integer>>();
        List<Set<Integer>> inGraphSet = new ArrayList<Set<Integer>>();
        for(int i = 0;i<n;i++){
        	outGraphSet.add(new HashSet<Integer>());
        	inGraphSet.add(new HashSet<Integer>());
        }
        for(int i = 0;i<n;i++){
        	if(graph[i].length==0){
        		queue.offer(i);
        	}
        	for(int toIdx:graph[i]){
        		outGraphSet.get(i).add(toIdx);
        		inGraphSet.get(toIdx).add(i);
        	}
        }
        while(!queue.isEmpty()){
        	int node = queue.poll();
        	colors[node] = 1;
        	for(int idx : inGraphSet.get(node)){
        		outGraphSet.get(idx).remove(node);
        		if(outGraphSet.get(idx).isEmpty()){
        			queue.offer(idx);
        		}
        	}
        }
        for(int i=0;i<colors.length;i++){
        	if(colors[i] == 1){
        		safeState.add(i);
        	}
        }
        return safeState;
	}
	/**
	 * 从一个节点开始深度优先进行搜索。如果能够走到终点并且只能走到终点则认为无环，是一个safe state。
	 * 我们将访问节点的不同状态称为 白-灰-黑：还没有开始访问节点是白(0)，开始访问一个节点是灰(1)，访问一个节点结束是黑(2)。
	 * 如果在访问节点A的过程中遇到了灰色的节点B，那么说明A在一个环内。A不是一个safe state。
	 * 如果在访问节点A的过程中所有的路径都能达到一个terminal node，没有进入环内，则A是一个safe state。
	 * 时间复杂度O(N+E) N是顶点数，E是边数
	 * @param graph
	 * @return
	 */
	public List<Integer> eventualSafeNodesV3(int[][] graph) {
		 int n = graph.length;
	     int[] colors = new int[n];
	     List<Integer> ans = new ArrayList<Integer>();
	     for(int i =0 ;i<n;i++){
	    	 if(visit(i,graph,colors)){
	    		 ans.add(i);
	    	 }
	     }
	     return ans;
	}
	private boolean visit(int node, int[][] graph, int[] colors) {
		if(colors[node]>0) return colors[node] == 2;
		colors[node] = 1;//开始循环
		for(int toNode : graph[node]){
			if(colors[toNode]==2){
				continue;
			}
			if(colors[toNode] == 1 || !visit(toNode,graph,colors)){
				return false;
			}
			
		}
		colors[node] = 2;
		return true;
	}

	public static void main(String[] args) {
		int[][] graph = new int[][]{{1,2},{2,3},{5},{0},{5},{},{}};
		new FindEventualSafeStates802().eventualSafeNodes(graph);
	}
}
