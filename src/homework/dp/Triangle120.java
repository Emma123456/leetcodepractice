package homework.dp;

import java.util.Arrays;
import java.util.List;

public class Triangle120 {
    private List<List<Integer>> triangle;
    private int n;
    private int minSum;
    private int[][] memo;
    public int minimumTotalV2(List<List<Integer>> triangle) {
        if(triangle==null || triangle.size()==0) return 0;
        this.triangle = triangle;
        this.n = triangle.size();
        minSum = Integer.MAX_VALUE;
        memo = new  int[n][triangle.get(n-1).size()];
        for(int[] array : memo){
            Arrays.fill(array,Integer.MAX_VALUE);
        }
        walk(0,0,0);
        return minSum;
    }
    private void walk(int i,int j,int sum){
        //System.out.println(i+" "+j+" "+sum);
        if(i==n) {
            minSum = Math.min(minSum,sum);
            return;
        };
        if(j>=triangle.get(i).size()) return;
        if(memo[i][j]>sum){
            memo[i][j] = sum;
            walk(i+1,j,sum+triangle.get(i).get(j));
            walk(i+1,j+1,sum+triangle.get(i).get(j));
        }
    }



    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null || triangle.size()==0) return 0;
        int n = triangle.size();
        //dp[i][j]到达元素（i，j）的最小路径和
        int[][] dp = new  int[n][triangle.get(n-1).size()];
        dp[0][0] = triangle.get(0).get(0);
        for(int i=1;i<n;i++){
            int m = triangle.get(i).size();
            for(int j=0;j<m;j++){
                if(j==0){
                    dp[i][j]=dp[i-1][j]+triangle.get(i).get(j);
                }else if(j==m-1){
                    dp[i][j]=dp[i-1][j-1]+triangle.get(i).get(j);
                }else{
                    dp[i][j]=Math.min(dp[i-1][j],dp[i-1][j-1])+triangle.get(i).get(j);
                }
            }
        }
        int min = dp[n-1][0];
        for(int j=1;j<triangle.get(n-1).size();j++){
            min = Math.min(min,dp[n-1][j]);
        }
        return min;
    }
}
