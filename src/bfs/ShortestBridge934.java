package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 先遍历A，如果第一次遇到1，则通过dfs，将所有相邻的值为1的元素坐标加入到队列。
 * count=0
 * 从队列中弹出位置，如果周围元素有值为1的，则返回count；否则将该位置没有访问过的元素坐标加入到队列。
 * count++;
 * 返回到上一步骤
 */
public class ShortestBridge934 {
    private  Queue<int[]> queue;
    private int[][] pos = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    /**
     * 因为题目中说到只有2个岛屿。首先在找到第一个元素=1的位置，用dfs找到第一个块。接着再数一数这个岛屿与另外一个岛屿的最短距离。
     * @param A
     * @return
     */
    public int shortestBridge(int[][] A){
        queue = new LinkedList<int[]>();
        int count = 0;
        int m = A.length;
        int n = A[0].length;
        boolean find = false;
        for(int i=0;i<m && !find;i++){
            for(int j=0;j<n;j++){
                if(A[i][j]==1){
                    dfs(A,i,j,m,n);
                    find = true;
                    break;
                }
            }
        }

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int[] elementPos = queue.poll();
                int x = elementPos[0] ,y =elementPos[1];
                for(int idx =0;idx<pos.length;idx++){
                    int newx = x+pos[idx][0];
                    int newy = y+pos[idx][1];
                    if(newx<m && newy <n && newx>=0 && newy>=0 && A[newx][newy]<2){
                        if(A[newx][newy] == 1){
                            return count;
                        }else{
                            A[newx][newy] = 2;
                            queue.offer(new int[]{newx,newy});
                        }
                    }
                }
            }
            count++;
        }

        return count;
    }

    private void dfs(int[][] A,int i, int j, int m, int n) {
        if(i>=m || j>=n ||i<0 || j<0) return;
        if(A[i][j]!=1){
            return;
        }
        A[i][j] = 2;
        queue.offer(new int[]{i,j});
        for(int idx =0;idx<pos.length;idx++){
            dfs(A,i+pos[idx][0],j+pos[idx][1],m,n);
        }
    }


}
