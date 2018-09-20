package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SumOfDistancesInTree {
	private int[] answer;
	private int[] count;
	/**
	 * 时间复杂度O(n)
	 * @param N
	 * @param edges
	 * @return
	 */
	public int[] sumOfDistancesInTree(int N, int[][] edges) {
		answer = new int[N];//存储以每个节点为根节点的路径和
		count = new int[N];//存储以每个节点为根节点的子树的节点数
		Arrays.fill(count,1);//默认所有节点都有1个节点
		Map<Integer,List<Integer>> edgeMap = new HashMap<Integer,List<Integer>>();
		for (int i = 0; i < N; ++i){
			edgeMap.put(i,new ArrayList<Integer>());
		}
        for(int[] edge : edges){
            edgeMap.get(edge[0]).add(edge[1]);
            edgeMap.get(edge[1]).add(edge[0]);
        }
        dfsCount(edgeMap,0,-1);
        dfsChildPathSum(edgeMap,N,0,-1);
		return answer;
	}
	
	/**
	 * 从叶子节点开始遍历，计算每个节点子树的节点数和路径和
	 * @param edgeMap
	 * @param node
	 * @param parent
	 */
	private void dfsCount(Map<Integer, List<Integer>> edgeMap, int node, int parent) {
		for (int child : edgeMap.get(node)) {
			if (child != parent) {
				dfsCount(edgeMap, child, node);
				count[node] += count[child];//以父节点为子树的节点数=各个以子节点为子树的节点数的和
				answer[node] += answer[child] + count[child];//从 以子节点为子树到 以父节点为子树 路径变化是：answer[child] + count[child]， 以子节点为子树的所有路径加1.
			}
		}
	}
	
	private void dfsChildPathSum(Map<Integer, List<Integer>> edgeMap,int N, int node, int parent) {
		for (int child : edgeMap.get(node)) {
			if (child != parent) {
				answer[child] = answer[node] - count[child] + N - count[child];//设置子节点的节点数
				dfsChildPathSum(edgeMap, N, child, node);
			}
		}
	}

	/**
	 * TL
	 * @param N
	 * @param edges
	 * @return
	 */
	public int[] sumOfDistancesInTreeV1(int N, int[][] edges) {
        int[] answer = new int[N];
        Map<Integer,List<Integer>> edgeMap = new HashMap<Integer,List<Integer>>();
        for(int[] edge : edges){
            if(edgeMap.get(edge[0])==null) edgeMap.put(edge[0],new ArrayList<Integer>());
            edgeMap.get(edge[0]).add(edge[1]);
            if(edgeMap.get(edge[1])==null) edgeMap.put(edge[1],new ArrayList<Integer>());
            edgeMap.get(edge[1]).add(edge[0]);
        }
        for(int i=0;i<N;i++){
            boolean[] pathNums = new boolean[N];//从i到j节点是否计算过=pathNums[j]
            pathNums[i] = true;
            answer[i] = dfs(i,edgeMap,pathNums,0);
        }
        return answer;
    }
	
    private int dfs(int startNode,Map<Integer,List<Integer>> edgeMap,boolean[] pathNums, int edgeCount){
        int pathCounts = 0;
        if(edgeMap.get(startNode)!=null){
            for(Integer toNode : edgeMap.get(startNode)){
                if(pathNums[toNode]==false){
                    pathNums[toNode] = true;
                    pathCounts += edgeCount+1;
                    pathCounts += dfs(toNode,edgeMap,pathNums,edgeCount+1);
                } 
            }
        }
        
        return pathCounts;
    }
    public static void main(String[] args) {
		int[][] edges = new int[][]{ new int[]{0,1},new int[]{0,2},new int[]{2,3},new int[]{2,4},new int[]{2,5}} ;
		int N = 6;
		new SumOfDistancesInTree().sumOfDistancesInTree(N, edges);
	}
}
