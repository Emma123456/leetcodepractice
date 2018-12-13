package bfs;

import java.util.*;

public class NetworkDelayTimeV2_743 {
    /**
     * 耗时39ms
     * 时间复杂度O(NlgN)
     * @param times
     * @param N
     * @param K
     * @return
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] dist = new int[N + 1];
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>(N, new Comparator<int[]>() {
            public int compare(int[] edge1, int[] edge2) {
                return edge1[1] - edge2[1];
            }
        });

        Map<Integer, List<int[]>> graph = new HashMap<Integer, List<int[]>>();
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<int[]>());
            }
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] seen = new boolean[N+1];
        //加入起始节点
        minHeap.offer(new int[]{K, 0});

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int node = curr[0];
            int d = curr[1];
            if (seen[node]) continue;
            //做事情
            dist[node] = d;
            seen[node] = true;
            if (graph.get(curr[0]) != null) {
                //遍历未处理的子节点
                for (int[] edge : graph.get(curr[0])) {
                    if (!seen[edge[0]]) {
                        //添加子节点
                        minHeap.offer(new int[]{edge[0], d + edge[1]});
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            answer = Math.max(answer, dist[i]);
        }
        return answer;
    }
}
