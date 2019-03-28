package bfs;

import java.awt.*;
import java.util.*;
import java.util.List;

public class BusRoutes815_2 {
    /**
     * 思路：把公交车看做是图的节点。要返回最少的公交车数量，就是一个最短路径问题。
     * 如果两个公交车至少有一个站是相同的，那这两个公交车之间有连线。
     * 从起始站开始BFS遍历图，直到遇到目标站。
     * 一个站可能在多个公交车里面有，所以起始队列和终点目标都是多个的。
     * BFS遍历总是能解决最短路径问题？？？！！！
     * @param routes
     * @param S
     * @param T
     * @return
     */
    public int numBusesToDestination(int[][] routes, int S, int T) {
        //构建图
        Map<Integer, List<Integer>> graph = new HashMap<Integer,List<Integer>>();
        int N = routes.length;
        for(int i=0;i<N;i++){
            Arrays.sort(routes[i]);
            graph.put(i,new ArrayList<Integer>());
        }
        for(int i=0;i<N;i++){
            for(int j = i+1;j<N;j++){
                if(intersection(routes[i],routes[j])){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        //遍历
        Queue<Point> queue = new ArrayDeque<>();
        Set<Integer> seen = new HashSet<>();
        List<Integer> targets = new ArrayList<>();
        for(int i=0;i<N;i++){
            if(Arrays.binarySearch(routes[i],S)!=-1){
                queue.offer(new Point(i,0));
                seen.add(i);
            }
            if(Arrays.binarySearch(routes[i],T)!=-1){
                targets.add(i);
            }
        }


        while(!queue.isEmpty()){
            Point point = queue.poll();
            int node = point.x;
            int depth = point.y;
            if(targets.contains(node)){
                return depth+1;
            }else{
                for(Integer next : graph.get(node)){
                    if(!seen.contains(next)){
                        seen.add(next);
                        queue.offer(new Point(next,depth+1));
                    }
                }
            }
        }

        return -1;
    }

    /**
     * 判断两个数组是否有交集
     * @param a
     * @param b
     * @return
     */
    private boolean intersection(int[] a,int[] b) {
        int i = 0;
        int j = 0;
        while(i<a.length && j<b.length){
            if(a[i]==b[j]) return true;
            if(a[i]<b[j]){
                i++;
            }else{
                j++;
            }
        }
        return false;
    }
}
