package array;

public class UniquePaths63 {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        int[][] dp = new int[m][n];// 表示达到m,n有多少条路径
        int val = 1;
		for (int i = 0; i < m; i++) {
            if(obstacleGrid[i][0]==1){
                val = 0;
            }
			dp[i][0] = val;
		}
        val = 1;
		for (int j = 0; j < n; j++) {
            if(obstacleGrid[0][j]==1){
                val = 0;
            }
			dp[0][j] = val;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
                if(obstacleGrid[i][j]==1){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
				
			}
		}
		return dp[m - 1][n - 1];
    }
	
	/**
	 * 合并一些步骤
	 * @param obstacleGrid
	 * @return
	 */
	public int uniquePathsWithObstaclesV2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];// 表示达到m,n有多少条路径
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
                if(obstacleGrid[i][j]==1){
                    dp[i][j] = 0;
                }else if(i==0 && j==0){
                	dp[i][j] = 1;
                }else if(i==0){
                	dp[i][j] = dp[i][j-1];
                }else if(j==0){
                	dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
				
			}
		}
		return dp[m - 1][n - 1];
    }
}
