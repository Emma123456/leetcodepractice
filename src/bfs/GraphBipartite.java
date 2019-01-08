package bfs;

import java.util.*;

/**
 * 一边遍历边，一边将节点放入到两个集合中 setA  setB
 * 所谓DFS，BFS就是指边的遍历方式
 * 注意的是：有些节点没有任何边
 * 分成两个集合的方式：方法一，放入两个set；方法二，用数组表示
 */
public class GraphBipartite {
    public boolean isBipartite(int[][] graph) {
        Set<Integer> setA = new HashSet<Integer>();
        Set<Integer> setB = new HashSet<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();

        boolean[] visited = new boolean[graph.length];
        for(int idx = 0;idx<graph.length;idx++) {
            if (!visited[idx]) {
                queue.offer(idx);
                setA.add(idx);
                visited[idx] = true;
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int i = 0; i < size; i++) {
                        int startNode = queue.poll();
                        for (Integer toNode : graph[startNode]) {
                            if (setA.contains(startNode)) {
                                if (setA.contains(toNode)) return false;
                                if (!visited[toNode]) {
                                    setB.add(toNode);
                                    queue.offer(toNode);
                                    visited[toNode] = true;
                                }

                            } else {
                                if (setB.contains(toNode)) return false;
                                if (!visited[toNode]) {
                                    setA.add(toNode);
                                    queue.offer(toNode);
                                    visited[toNode] = true;
                                }
                            }


                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * colors数组既可以表示节点是否访问过，也可以表示节点的分组
     * @param graph
     * @return
     */
    public boolean isBipartiteV2(int[][] graph) {
        int[] colors = new int[graph.length];
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int idx = 0;idx<graph.length;idx++) {
            if (colors[idx]==0) {
                queue.offer(idx);
                colors[idx]=1;
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int i = 0; i < size; i++) {
                        int startNode = queue.poll();
                        for (Integer toNode : graph[startNode]) {
                            if (colors[startNode]==1) {
                                if (colors[toNode]==1) return false;
                                if (colors[toNode]==0) {
                                    colors[toNode]=2;
                                    queue.offer(toNode);
                                }

                            } else {
                                if (colors[toNode]==2) return false;
                                if (colors[toNode]==0) {
                                    colors[toNode]=1;
                                    queue.offer(toNode);
                                }
                            }


                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * V2版本的if else 判断优点low。colors[i]=0 表示一组，colors[i]=1表示另外一组
     * @param graph
     * @return
     */
    public boolean isBipartiteV3(int[][] graph) {
        int[] colors = new int[graph.length];
        Arrays.fill(colors,-1);
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int idx = 0;idx<graph.length;idx++) {
            if (colors[idx]==-1) {
                queue.offer(idx);
                colors[idx]=0;
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int i = 0; i < size; i++) {
                        int startNode = queue.poll();
                        for (Integer toNode : graph[startNode]) {
                            if(colors[toNode]==colors[startNode]){
                                return false;
                            }
                            if (colors[toNode]==-1) {
                                colors[toNode]=1-colors[startNode];
                                queue.offer(toNode);
                            }

                        }
                    }
                }
            }
        }
        return true;
    }


    /**
     * isBipartiteV3版本的dfs版本
     * @param graph
     * @return
     */
    public boolean isBipartiteV4(int[][] graph) {
        int[] colors = new int[graph.length];
        Arrays.fill(colors,-1);
        for(int idx = 0;idx<graph.length;idx++) {
            if (colors[idx]==-1) {
                if(!dfs(graph,colors,idx,0)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph,int[] colors,int idx,int color){
        if(colors[idx]==1-color) return false;
        if(colors[idx]==-1){
            colors[idx] = color;
            for(Integer toNode: graph[idx]){
                if(!dfs(graph,colors,toNode,1-color)) return false;
            }
        }
        return true;
    }
}
