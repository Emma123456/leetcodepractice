package dfs;

public class LongestIncreasingPath329 {
	private int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public int longestIncreasingPath(int[][] matrix) {
		int result = 0;
		if (matrix == null || matrix.length == 0)
			return result;
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] saved = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int r = maxPath(matrix, i, j, m, n, saved);
				result = Math.max(result, r);
			}
		}
		return result;
	}

	private int maxPath(int[][] matrix, int i, int j, int m, int n,int[][] saved) {
		if(saved[i][j]>0) return saved[i][j];
		
		int max = 1;
		for (int[] dir : dirs) {
			int r = 1;
			int newi = i + dir[0];
			int newj = j + dir[1];
			if (newi < 0 || newi >= m || newj < 0 || newj >= n || matrix[newi][newj]<=matrix[i][j]) {
				continue;
			}
			r += maxPath(matrix, newi, newj, m, n, saved);
			max = Math.max(r, max);
		}
		saved[i][j] = max;
		return max;
	}

	public static void main(String[] args) {
		int[][] data = new int[][] { { 7, 8, 9 }, { 9, 7, 6 }, { 7, 2, 3 } };
		int r= new LongestIncreasingPath329().longestIncreasingPath(data);
		System.out.println(r);
	}
}
