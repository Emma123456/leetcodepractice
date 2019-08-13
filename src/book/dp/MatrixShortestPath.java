package book.dp;

public class MatrixShortestPath {
    private int[][] matrix;
    private int n;
    private int minPathSum = Integer.MAX_VALUE;

    private void f(int i,int j,int currentPathSum){
        if(i==n-1 && j==n-1){
            minPathSum = Math.min(minPathSum,currentPathSum+matrix[i][j]);
            return;
        }
        if(i<n-1){
            f(i+1,j,currentPathSum+matrix[i][j]);//向下走
        }
        if(j<n-1){
            f(i,j+1,currentPathSum+matrix[i][j]);//向右走
        }
    }

    public int getMinPathSum(){
        f(0,0,0);
        return minPathSum;
    }

    public int minDistDP(int[][] matrix ,int n){
        int[][] states = new int[n][n];
        //第一行
        for(int j=0;j<n;j++){
            if(j==0){
                states[0][j] = matrix[0][j];
            }else{
                states[0][j] = states[0][j-1]+matrix[0][j];
            }

        }
        //第一列
        for(int i=1;i<n;i++){
            states[i][0] = states[i-1][0]+matrix[i][0];
        }

        for(int i=1;i<n;i++){
            for(int j=1;j<n;j++){
                states[i][j] = Math.min(states[i-1][j],states[i][j-1])+matrix[i][j];
            }
        }
        return states[n-1][n-1];
    }

    public int minDist(int i,int j){
        if(i==0 && j==0){
            return matrix[i][j];
        }
        if(i==0){
           return minDist(i,j-1)+matrix[i][j];
        }
        if(j==0){
            return minDist(i-1,j)+matrix[i][j];
        }
        return Math.min(minDist(i-1,j),minDist(i,j-1))+matrix[i][j];
    }

    public int minDistDPV2(){
        return minDist(n-1,n-1);
    }


    public static void main(String[] args){
        MatrixShortestPath m = new MatrixShortestPath();
        m.matrix = new int[][]{new int[]{1,3,5,9},new int[]{2,1,3,4},new int[]{5,2,6,7},new int[]{6,8,4,3}};
        m.n = 4;
        int r = m.getMinPathSum();
        System.out.println(r);

        r = m.minDistDP(m.matrix,m.n);
        System.out.println(r);

        r = m.minDistDPV2();
        System.out.println(r);
    }
}
