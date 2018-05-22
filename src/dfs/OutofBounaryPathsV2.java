package dfs;

import java.util.Arrays;

public class OutofBounaryPathsV2 {
	int M=1000000007;
	public int findPaths(int m, int n, int N, int i, int j) {
		if (i == -1 || j == -1 || i == m || j == n) {
			return 1;
		}
		if (N == 0)
			return 0;
		return findPaths(m, n, N - 1, i - 1, j) + findPaths(m, n, N - 1, i + 1, j) + findPaths(m, n, N - 1, i, j - 1) + findPaths(m, n, N - 1, i, j + 1);
	}

	/**
	 * 在上一方法中，可能通过多个路径到达  i,j,N相同的状态。这是重复计算的部分，用缓存保存起来。
	 * O(m*n*N)我们需要把数组填满，所以需要m*n*N次计算
	 * @param m
	 * @param n
	 * @param N
	 * @param i
	 * @param j
	 * @return
	 */
	public int findPathsV2(int m, int n, int N, int i, int j) {
		int[][][] memo = new int[m][n][N + 1];
		for(int k=0;k<memo.length;k++){
			for(int l = 0;l<n;l++){
				Arrays.fill(memo[k][l], -1);
			}
		}
		return findPaths(m, n, N, i, j, memo);
	}

	private int findPaths(int m, int n, int N, int i, int j, int[][][] memo) {
		if (i == -1 || j == -1 || i == m || j == n) {
			return 1;
		}
		if (N == 0)
			return 0;
		if (memo[i][j][N] >=0 )
			return memo[i][j][N];
		//不明白为什么这样mod
		memo[i][j][N] = (
				(findPaths(m, n, N - 1, i - 1, j, memo) + findPaths(m, n, N - 1, i + 1, j, memo))%M 
				+ (findPaths(m, n, N - 1, i, j - 1, memo)+ findPaths(m, n, N - 1, i, j + 1, memo))%M
				)%M;
		return memo[i][j][N];
	}
	
	/**
	 * dp
	 * @param m
	 * @param n
	 * @param N
	 * @param i
	 * @param j
	 * @return
	 */
	public int findPathsV3(int m, int n, int N, int i, int j) {
		int[][] dp = new int[m][n];//有多少种方式达到(i,j)
		dp[i][j] = 1;
		int M=1000000007;
		int count = 0;
		for (int move = 1; move <= N; move++) {
			int[][] temp = new int[m][n];
			for (int k = 0; k < m; k++) {
				for (int l = 0; l < n; l++) {
					if(k==0){
						count = (count+dp[k][l])%M;
					}
					if(l==0){
						count = (count+dp[k][l])%M;
					}
					if(k==m-1){
						count = (count+dp[k][l])%M;
					}
					if(l==n-1){
						count = (count+dp[k][l])%M;
					}
					if(k>0){
						temp[k][l] = (temp[k][l]+dp[k-1][l])%M;
					}
					if(k<m-1){
						temp[k][l] = (temp[k][l]+dp[k+1][l])%M;
					}
					if(l>0){
						temp[k][l] = (temp[k][l]+dp[k][l-1])%M;
					}
					if(l<n-1){
						temp[k][l] = (temp[k][l]+dp[k][l+1])%M;
					}
				}
			}
			dp = temp;
		}
		return count;
	}
	public static void main(String[] args) {
		new OutofBounaryPathsV2().findPathsV3(2, 2, 2, 0, 0);
	}
}
