package array;

public class UniquePaths62 {
	private int count = 0;

	/**
	 * 思路一：在每个位置(i,j)有两种走向：向右走到达(i+1,j)，向下走到达(i,j+1)。暴力搜索每种情况。处理边界条件
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePaths(int m, int n) {
		count = 0;
		visit(0, 0, m - 1, n - 1);
		return count;
	}

	private void visit(int i, int j, int m, int n) {
		if (i == m && j == n) {
			count++;
		} else {
			if (i < m) {
				visit(i + 1, j, m, n);
			}
			if (j < n) {
				visit(i, j + 1, m, n);
			}
		}
	}

	/**
	 * 动态规划思想：dp[i][j]表示到达(i,j)位置，有几种走法。
	 * 初始条件是第一行、第一列的每个位置只有一种走法所以dp[0][*] = 1,dp[*][0]=1
	 * 递归条件 dp[i][j] = dp[i-1][j]+dp[i][j-1]
	 * 时间复杂度O(m*n)
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePathsV2(int m, int n) {
		int[][] dp = new int[m][n];// 表示达到m,n有多少条路径
		for (int i = 0; i < m; i++) {
			dp[i][0] = 1;
		}
		for (int j = 0; j < n; j++) {
			dp[0][j] = 1;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}
		return dp[m - 1][n - 1];
	}
	/**
	 * 排列组合思想:表格是m行，n列，robot需要向下移动m-1次，向右移动n-1次。如果用D表示向下，R表示向右。
	 * 例如在3x7的表格中，需要2次D，6次R。这样可以是任意组合例如D,D,R,R,R,R,R,R或者D,R,D,R,R,R,R,R。这样只需要计算(m-1)+(n-1)的组合数。
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePathsV3(int m, int n) {
		if(m==1 ||n ==1 ) return 1;
		m--;
		n--;
		//做交换是为了：1保证最小的循环次数；2 防止乘法溢出 If m is taken to be higher, we can directly cancel m! part from the equation (m+n)! / (m! * n!) and so now we only calculate (m+1)(m+2)…(m+n) / n!
		if(m<n){
			m = m+n;
			n = m -n;
			m = m -n;
		}
		long res = 1;
		int j = 1;
		for(int i=m+1;i<=m+n;i++,j++){
			res *= i;
			res /=j;
		}
		return (int) res;
	}
}
