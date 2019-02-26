package bfs;

import java.util.*;

/**
 *
 我在思考
 我可以以每个节点作为根节点查找当前这棵树的高度是多少，然后记录下来，再比较。
 以0为根，高度是height[0],
 以1为根，高度是height[1],
 ....
 以n-1为根，高度是height[n-1]

 那么当0为根的时候，怎么计算这棵树的高度呢

 图表示：Map<Integer,List<Integer>> 是表示双向链接的
 map 0    1
 1    0，2，3
 2    1
 3    1

 BFS遍历树
 0       1
 1       2，3（0已经访问过，不放入新的队列）
 2,3         (1已经访问过，不放入队列)

 遍历完成高度为2

 那么当1为根的时候，怎么计算这棵树的高度呢


 BFS遍历树
 1       0，2，3
 0，2，3

 遍历完成高度为1
 ...


 最后查找height数组中值最小的下标，返回
 */
public class MinimumHeightTrees310 {
    /**
     * 代码执行正确，但是耗时太长。
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer,List<Integer>> graph = createGraph(edges,n);
        int[] heights = new int[n];
        int minHeight = n;
        for(int i=0;i<n;i++){
            heights[i] = findHeight(graph,n,i);
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

    private int findHeight(Map<Integer,List<Integer>> graph,int n,int root){
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(root);
        visited[root]=true;
        int height = 0 ;
        while(!queue.isEmpty()){
            int size = queue.size();
            height++;
            for(int i=0;i<size;i++){
                int node = queue.poll();
                for(Integer toNode : graph.get(node)){
                    if(!visited[toNode]){
                        queue.offer(toNode);
                        visited[toNode]=true;
                    }
                }
            }
        }
        return height;
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
