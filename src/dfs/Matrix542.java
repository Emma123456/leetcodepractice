package dfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Matrix542 {
	private int[] dx = new int[]{-1,1,0,0};
    private int[] dy = new int[]{0,0,-1,1};
    /**
     * DFS
     * 1 先判断周围是否有0，如果有则距离为1，直接退出
     * 2 不满足条件1，继续搜索周围相邻节点。这个过程中，没有把相邻节点的计算保留下来，所以会有重复计算.在整个dfs过程中并没有对r的有效修改，所以会有重复的。
     * 例如，在计算(0,4)的最近0距离的时候，会计算(1,4)点的最近0距离，但是结果并没有保存下来。
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        if(matrix==null || matrix.length==0) return null;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] r = new int[m][n];
        for(int i=0;i<m;i++){
        	Arrays.fill(r[i], -1);
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j] == 1){
                    r[i][j] = findDistance(matrix,i,j,m,n,0,r);
                }else{
                	r[i][j] = 0;
                }
            }
        }
        return r;
    }
    
    private int findDistance(int[][] matrix,int i,int j, int m, int n,int dis,int[][] r){
    	if(matrix[i][j]==0) return dis;
        if(r[i][j]>=0) return r[i][j]+dis;
        if(r[i][j]==-2) return Integer.MAX_VALUE;//如果是计算中的节点，返回一个最大的距离值。
        for(int k=0;k<4;k++){
            int newi = i+dx[k];
            int newj = j+dy[k];
            if(newi>=0 && newi<m && newj>=0 && newj<n){
                if(matrix[newi][newj] == 0)
                return 1+dis;
            }
        }
        r[i][j] = -2;
        int min = Math.max(m,n);
        for(int k=0;k<4;k++){
            int newi = i+dx[k];
            int newj = j+dy[k];
            if(newi>=0 && newi<m && newj>=0 && newj<n){
                min = Math.min(min,findDistance(matrix,newi,newj,m,n,dis+1,r));
            }
        }
        r[i][j] = -1;
        return min;
    }
    /**
     * 暴力搜索O((mxn)^2)
     * @param matrix
     * @return
     */
    public int[][] updateMatrixV2(int[][] matrix) {
    	if(matrix==null || matrix.length==0) return null;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] r = new int[m][n];
        for(int i=0;i<m;i++){
        	Arrays.fill(r[i], Integer.MAX_VALUE);
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
            	if(matrix[i][j] == 0){
            		r[i][j] = 0;
            	}else{
            		for (int k = 0; k < m; k++){
                        for (int l = 0; l < n; l++){
                        	if(matrix[k][l] == 0){
                        		int dis = Math.abs(k-i)+Math.abs(l-j);
                        		r[i][j] = Math.min(r[i][j], dis);
                        	}
                        }
            		}
            	}
            }
        }
        return r;
    }
    
    /**
     * BFS  从matrix[i][j] = 0的点开始向四周扩散。
     * O(mxn)
     * @param matrix
     * @return
     */
    public int[][] updateMatrixV3(int[][] matrix) {
    	if(matrix==null || matrix.length==0) return null;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] r = new int[m][n];
        for(int i=0;i<m;i++){
        	Arrays.fill(r[i], Integer.MAX_VALUE);
        }
        Queue<int[]> queue = new LinkedList<int[]>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
            	if(matrix[i][j] == 0){
            		queue.add(new int[]{i,j});
            		r[i][j] = 0;
            	}
            }
        }
        int[][] dir = new int[][]{new int[]{-1,0},new int[]{1,0},new int[]{0,-1},new int[]{0,1}};
        while(!queue.isEmpty()){
        	int[] tmp = queue.poll();
        	int i = tmp[0],j = tmp[1];
        	for(int k=0;k<4;k++){
                int newi = i+dir[k][0];
                int newj = j+dir[k][1];
                if(newi>=0 && newi<m && newj>=0 && newj<n){
                    if(r[newi][newj]>r[i][j]+1){
                    	r[newi][newj] = r[i][j]+1;
                    	queue.add(new int[]{newi,newj});
                    }
                }
            }
        }
        return r;
    }
    /**
     * 在完成 updateMatrix 的时候，指出是有重复计算的，并不能完全证明当前就是最小值。
     * 但是在这个版本中：在从左到右，从上到下的遍历中，dist[i][j] = Min(dist[i][j],min(dist[i-1][j]+1,dist[i][j-1]+1))。因为在这样的遍历中，(i-1,j)和(i,j-1)节点是计算过的。
     * 在从下到上，从右到左的遍历中，dist[i][j] = Min(dist[i][j],min(dist[i+1][j]+1,dist[i][j+1]+1))。因为在这样的遍历中,(i,j+1)和(i+1,j)节点是计算过的。
     * @param matrix
     * @return
     */
    public int[][] updateMatrixV4(int[][] matrix) {
    	if(matrix==null || matrix.length==0) return null;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] r = new int[m][n];
        for(int i=0;i<m;i++){
        	Arrays.fill(r[i], Integer.MAX_VALUE-10000);
        }
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					r[i][j] = 0;
				} else {
					if (i > 0) {
						r[i][j] = Math.min(r[i][j], r[i - 1][j] + 1);
					}
					if (j > 0) {
						r[i][j] = Math.min(r[i][j], r[i][j - 1] + 1);
					}
				}
			}
		}
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				if (i < m - 1) {
					r[i][j] = Math.min(r[i][j], r[i + 1][j] + 1);
				}
				if (j < n - 1) {
					r[i][j] = Math.min(r[i][j], r[i][j + 1] + 1);
				}
			}
		}
		return r;
    }
    public static void main(String[] args) {
    	int[][] matrix = new int[][]{new int[]{0,1,0,1,1},new int[]{1,1,0,0,1},new int[]{0,0,0,1,0},new int[]{1,0,1,1,1},new int[]{1,0,0,0,1}};
		new Matrix542().updateMatrix(matrix);
	}
}
