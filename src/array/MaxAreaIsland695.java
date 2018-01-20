package array;

/**
 * 这道题目与 85 最大矩形面积的区别是：岛的面积只要连通就行，不一定要形成矩形。
 * 这道题目的精妙还在计算过就让grid[i][j] = 0，这样就不会重复计算了。
 * @author Administrator
 *
 */
public class MaxAreaIsland695 {
	public int maxAreaOfIsland(int[][] grid) {
		int m = grid.length;
		if (m == 0) {
			return 0;
		}
		int n = grid[0].length;
		int maxA = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(grid[i][j] == 1){
					int area = dfs(grid,i,j,m,n);
					maxA = Math.max(maxA, area);
				}
			}
		}
		return maxA;
	}

	private int dfs(int[][] grid, int i, int j,int m,int n) {
		if(i>=m || i<0 || j>=n || j<0 || grid[i][j] ==0 ){
			return 0;
		}
		//为了防止重复计算
		grid[i][j] = 0;
		int area = 1;
		area += dfs(grid,i-1,j,m,n);
		area += dfs(grid,i,j-1,m,n);
		area += dfs(grid,i,j+1,m,n);
		area += dfs(grid,i+1,j,m,n);
		return area;
	}

	public static void main(String[] args) {
		int[][] grid = new int[4][];
		grid[0] = new int[] { 1, 1, 0, 0, 0 };
		grid[1] = new int[] { 1, 1, 0, 0, 0 };
		grid[2] = new int[] { 0, 0, 0, 1, 1 };
		grid[3] = new int[] { 0, 0, 0, 1, 1 };

		int r = new MaxAreaIsland695().maxAreaOfIsland(grid);
		System.out.println(r);
	}

}
