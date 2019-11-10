package homework.dp;

public class MatrixShortestPath {
    private int[][] matrix;
    private int n;
    private int minPathSum;

    public int getMinPathSum(){
        minPathSum = Integer.MAX_VALUE;
        f(0,0,0);
        return minPathSum;
    }

    /**
     * 当i，j相同的时候可能有多个currentPathSum，保留最小的那个就好
     * @param i
     * @param j
     * @param currentPathSum
     */
    private void f(int i, int j, int currentPathSum) {

        if(i==n && j==n){
            minPathSum = Math.min(minPathSum,currentPathSum);
            return;
        }
        if(i>=n || j>=n) return;
        //向右走
        f(i,j+1,currentPathSum+matrix[i][j]);
        //向下走
        f(i+1,j,currentPathSum+matrix[i][j]);
    }


    public int getMinPathSumV2(){
        minPathSum = Integer.MAX_VALUE;
        int[][] state = new int[n][n];
        //state[i][j]=n，表示到达i,j位置，最短的路径和
        //第0行
        for(int j=0;j<n;j++){
            if(j==0){
                state[0][j] = matrix[0][j];
            }else{
                state[0][j] = state[0][j-1]+matrix[0][j];
            }

        }
        //第0列
        for(int i=1;i<n;i++){
            state[i][0] = state[i-1][0]+matrix[i][0];
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<n;j++){
                state[i][j] = Math.min(state[i-1][j],state[i][j-1])+matrix[i][j];
            }
        }
        return state[n-1][n-1];
    }

}
