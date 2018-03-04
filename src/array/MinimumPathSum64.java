package array;

public class MinimumPathSum64 {
	/**
	 * 动态规划，本题和62 Unique Paths的动态规划类似
	 * 
	 * @param grid
	 * @return
	 */
	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0)
			return 0;
		int m = grid.length;
		int n = grid[0].length;
		int[][] dp = new int[m][n];
		visit(grid, dp, m - 1, n - 1);
		return dp[m - 1][n - 1];
	}
	private void visit(int[][] grid, int[][] dp, int i, int j) {
		if (i == 0 && j == 0) {
			dp[i][j] = grid[i][j];
			return;
		}
		int val1 = Integer.MAX_VALUE;
		int val2 = Integer.MAX_VALUE;
		if (i > 0) {
			if (dp[i - 1][j] == 0) {
				visit(grid, dp, i - 1, j);
			}
			val1 = dp[i - 1][j];
		}
		if (j > 0) {
			if (dp[i][j - 1] == 0) {
				visit(grid, dp, i, j - 1);
			}
			val2 = dp[i][j - 1];
		}
		dp[i][j] = Math.min(val1, val2) + grid[i][j];
	}

	public int minPathSumV2(int[][] grid) {
		if (grid == null || grid.length == 0)
			return 0;
		int m = grid.length;
		int n = grid[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(i==0 && j!=0){
					grid[i][j] = grid[i][j-1]+grid[i][j];
				}else if(i!=0 && j==0){
					grid[i][j] = grid[i-1][j]+grid[i][j];
				}else if(i>0 && j>0){
					grid[i][j] = Math.min(grid[i-1][j], grid[i][j-1])+grid[i][j];
				}
			}
		}
		return grid[m-1][n-1];
	}

	public int minPathSumV3(int[][] grid) {
		if (grid == null || grid.length == 0)
			return 0;
		int m = grid.length;
		int n = grid[0].length;
		int[][] dp = new int[m][n];
		
		return dp[m - 1][n - 1];
	}
	public static void main(String[] args) {
		int[][] grid = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
		int r =new MinimumPathSum64().minPathSum(grid);
		
	}

}
