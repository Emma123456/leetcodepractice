package bfs;

import java.util.*;

/***
 *
 当只有一个节点的时候，高度为0，直接返回。
 当有两个节点，并且相连的时候，任意一个做根，都可以。


 */
public class MinimumHeightTrees310BFS {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        Map<Integer,List<Integer>> graph = createGraph(edges,n);
        int[] inDegrees = new int[n];
        for(int[] edge : edges){
            inDegrees[edge[0]]++;
            inDegrees[edge[1]]++;
        }
        List<Integer> result = new ArrayList<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i=0;i<n;i++){
            if(inDegrees[i]==0){
                result.add(i);
                return result;
            }else if(inDegrees[i]==1){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            result = new ArrayList<Integer>();
            int size = queue.size();
            for(int i=0;i<size;i++){
                int node = queue.poll();
                result.add(node);
                for(Integer toNode : graph.get(node)){
                    inDegrees[toNode]--;
                    if(inDegrees[toNode]==1){
                        queue.offer(toNode);
                    }
                }
            }
        }
        return result;
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
