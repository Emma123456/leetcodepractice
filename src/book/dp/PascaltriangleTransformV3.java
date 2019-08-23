package book.dp;

/**
 * 根据状态转移方程来计算
 * min_sum(i,j)=min(min_sum(i-1,j),min_sum(i-1,j-1))+triangle[i][j]
 */
public class PascaltriangleTransformV3 {
    private int[][] triangle = new int[][]{new int[]{5},new int[]{7,8},new int[]{2,3,4},new int[]{4,9,6,1},new int[]{2,7,9,4,5}};

    public int getShortestpathSum(){
        int m = triangle.length;
        int n = triangle[m-1].length;
        int[][] dp = new  int[m][n];
        dp[0][0] = triangle[0][0];
        for(int i=1;i<m;i++){
            for(int j=0;j<=i;j++){
                int val1 = (j-1)>=0?dp[i-1][j-1]:Integer.MAX_VALUE;
                int val2 = (j<=i-1)?dp[i-1][j]:Integer.MAX_VALUE;
                dp[i][j] = Math.min(val1,val2)+triangle[i][j];
            }
        }
        int minPathSum = Integer.MAX_VALUE;
        for(int j=0;j<n;j++){
            minPathSum = Math.min(minPathSum,dp[m-1][j]);
        }
        return minPathSum;
    }
    public static void main(String[] args){
        int r = new PascaltriangleTransformV3().getShortestpathSum();
        System.out.println(r);
    }
}
