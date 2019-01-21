package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PerfectSquares279 {
    /**
     * BFS
     * 重点：将 节点0，1，2，。。。n 都看做一个节点。节点j到节点i有边，当且仅当 j=i+(a perfect square number) 或者 i=j+(a perfect square number)
     * 接着就是遍历节点
     * @param n
     * @return
     */
    public int numSquares(int n) {
        List<Integer> perfectSquares = new ArrayList<Integer>();
        int[] cntPerfectSquares = new int[n];
        for(int i=1;i*i<=n;i++){
            if(i*i==n) return 1;
            perfectSquares.add(i*i);
            cntPerfectSquares[i*i-1] = 1;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for(Integer val : perfectSquares){
            queue.offer(val);
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int val = queue.poll();
                for(Integer val2 : perfectSquares){
                    if(val+val2==n) return 1+cntPerfectSquares[val-1];
                    if(val+val2<n && cntPerfectSquares[val+val2-1]==0){
                        cntPerfectSquares[val+val2-1] = 1+cntPerfectSquares[val-1];
                        queue.offer(val+val2);
                    }
                }
            }

        }
        return -1;
    }


    /**
     * DP
     * @param n
     * @return
     */
    public int numSquaresV2(int n) {
        int[] cntPerfectSquares = new int[n+1];
        //设置最大值
        for(int i=0;i<cntPerfectSquares.length;i++){
            cntPerfectSquares[i] = n+1;
        }

        cntPerfectSquares[0] = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j*j<=i;j++){
                cntPerfectSquares[i] = Math.min(cntPerfectSquares[i],cntPerfectSquares[i-j*j]+1);

            }
        }
        return cntPerfectSquares[n];
    }
}
