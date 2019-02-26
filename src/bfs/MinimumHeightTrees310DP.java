package bfs;

import java.util.*;

public class MinimumHeightTrees310DP {
    private Map<String,Integer> cache = new HashMap<String,Integer>();
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer,List<Integer>> graph = createGraph(edges,n);
        int[] heights = new int[n];
        int minHeight = n;
        for(int i=0;i<n;i++){
            heights[i] = findHeight(graph,i,-1);
            minHeight = Math.min(minHeight,heights[i]);
        }
        List<Integer> result = new ArrayList<Integer>();
        for(int i=0;i<n;i++){
            if(heights[i]==minHeight){
                result.add(i);
            }
        }
        return result;
    }
    private int findHeight(Map<Integer,List<Integer>> graph,int root,int parent){
        int height = 0;
        for(Integer toNode : graph.get(root)){
            if(toNode==parent){
                continue;
            }
            String key = String.valueOf(root)+"->"+toNode;
            int tmp;
            if(cache.get(key)!=null){
                tmp = cache.get(key);
            }else{
                tmp = findHeight(graph,toNode,root);
                cache.put(key,tmp);
            }
            height = Math.max(height,tmp);
        }
        return height+1;
    }

    private Map<Integer,List<Integer>> createGraph(int[][] edges,int n){
        Map<Integer,List<Integer>> graph = new HashMap<Integer,List<Integer>>();
        for(int i=0;i<n;i++){
            graph.put(i,new ArrayList<Integer>());
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
}
