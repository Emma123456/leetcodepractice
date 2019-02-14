package bfs;

import java.util.*;

public class Matrix542 {
    private int[][] pos = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    public int[][] updateMatrix(int[][] matrix) {
        if(matrix==null || matrix.length==0) return null;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] result = new int[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(result[i],-1);
        }
        Queue<int[]> queue = new LinkedList<int[]>();

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    result[i][j] = 0;
                    queue.offer(new int[]{i,j});
                }
            }
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int[] dot = queue.poll();
                for(List<Integer> newPosition : newPosition(dot[0],dot[1],m,n)){
                    int newx = newPosition.get(0);
                    int newy = newPosition.get(1);
                    if(result[newx][newy]==-1 || result[newx][newy] < result[dot[0]][dot[1]]+1){
                        result[newx][newy] = result[dot[0]][dot[1]]+1;
                        queue.offer(new int[]{newx,newy});
                    }
                }
            }
        }


        return result;
    }

    private List<List<Integer>> newPosition(int x,int y,int m,int n){
        List<List<Integer>> positionList = new ArrayList<List<Integer>>();
        for(int i=0;i<pos.length;i++){
            int newx = x+pos[i][0];
            int newy = y+pos[i][1];
            if(newx>=0 && newx<m && newy>=0 && newy<n){
                positionList.add(Arrays.asList(newx,newy));
            }
        }
        return positionList;
    }

    /**
     * DP思路。如果matrix[i][j]=0，则距离为0。如果matrix[i][j]=1，并且已经知道i，j周围所有元素的最短距离，那么 result[i][j]=min(result[i][j],min(各个方向的距离)+1)
     * 各个方向是指上下左右。
     * 第一次遍历从左到右，从上到下，这样对于i，j来讲，左上的距离是确定的。
     * 第二次遍历从右到左，从下到上，这样对于i,j来讲，右下的距离是确定的。
     * 需要注意的是：result[i][j]的初始化值。如果matrix[0][0]=1，那么第一次遍历的时候,当处理到0，1元素的时候，result[0][0]=Integer.MAX_VALUE，执行Math.min(result[i][j],result[i][j-1]+1)会有Integer.MAX_VALUE+1这样一个溢出操作，所以result[i][j]的初始化值应该是Integer.MAX_VALUE-10000。减去的数值是随意写的。
     * @param matrix
     * @return
     */
    public int[][] updateMatrixDP(int[][] matrix) {
        if(matrix==null || matrix.length==0) return null;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] result = new int[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(result[i],Integer.MAX_VALUE-10000);
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    result[i][j]=0;
                }else{
                    if(i>0){
                        result[i][j]=Math.min(result[i][j],result[i-1][j]+1);
                    }
                    if(j>0){
                        result[i][j]=Math.min(result[i][j],result[i][j-1]+1);
                    }
                }
            }
        }

        for(int i=m-1;i>=0;i--){
            for (int j=n-1;j>=0;j--){
                if(i<m-1){
                    result[i][j]=Math.min(result[i][j],result[i+1][j]+1);
                }
                if(j<n-1){
                    result[i][j]=Math.min(result[i][j],result[i][j+1]+1);
                }
            }
        }

        return result;
    }
}
