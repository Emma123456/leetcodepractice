package bfs;

import java.util.*;

public class BusRoutes815_1 {
    /**
     * https://leetcode.com/problems/bus-routes/discuss/122712/Simple-Java-Solution-using-BFS
     * 我一定认为要按照公交车的行驶顺利遍历站点。
     * 如果roets[0]={1,5,7},如果从5开始，那么1，7可以同时加入队列，因为只要在这辆公交车内不管走多少遍，都是1辆公交车。题目需求求解的也是最少公交车数量，不是站点数量。
     * @param routes
     * @param S
     * @param T
     * @return
     */
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(S==T) return 0;

        Map<Integer,List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for(int i=0;i<routes.length;i++){
            for(int j=0;j<routes[i].length;j++){
                List<Integer> busList = map.getOrDefault(routes[i][j],new ArrayList<>());
                busList.add(i);
                map.put(routes[i][j],busList);
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(S);
        Set<Integer> seen = new HashSet<>();
        int level = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            level++;
            for(int i=0;i<size;i++){
                int stop = queue.poll();
                if(stop == T) return level;
                for(int bus : map.get(stop)){
                    if(seen.contains(bus)) continue;
                    seen.add(bus);
                    for(int j=0;j<routes[bus].length;j++){
                        queue.offer(routes[bus][j]);
                    }
                }
            }
        }

        return -1;
    }


}
