package book.dp;

public class PascaltriangleTransformV2 {
    private int[][] triangle = new int[][]{new int[]{5},new int[]{7,8},new int[]{2,3,4},new int[]{4,9,6,1},new int[]{2,7,9,4,5}};


    /**
     * 对于f(i,j)是有重复计算的。例如在计算f(2,1)的时候需要计算f(1,1)，计算f(2,2)的时候需要计算f(1,1)和f(1,2)。f(1,1)计算了两次
     * @return
     */
    public int getShortestpathSum(){
        int m = triangle.length;
        int n = triangle[m-1].length;
        int minPathSum = Integer.MAX_VALUE;
        int[][] memo = new int[m][n];
        for(int j=0;j<n;j++){
            minPathSum = Math.min(minPathSum,f(m-1,j,memo));
        }
        return minPathSum;
    }

    /**
     * 到达第i层,第j列的最短路径和
     * @param i
     */
    private int f(int i, int j,int[][] memo) {
        if(j<0) return Integer.MAX_VALUE;//此路不同
        if(j>=triangle[i].length) return Integer.MAX_VALUE;//此路不同
        if(i==0){
            return triangle[0][0];
        }else{
            if(memo[i][j]>0) return memo[i][j];//假设triangle的值都是正整数
            int val1 = f(i-1,j,memo);
            int val2 = f(i-1,j-1,memo);
            memo[i][j] = Math.min(val1,val2)+triangle[i][j];
            return memo[i][j];
        }
    }

    public static void main(String[] args){
        int r = new PascaltriangleTransformV2().getShortestpathSum();
        System.out.println(r);
    }
}
