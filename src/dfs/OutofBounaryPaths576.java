package dfs;
/**
 * 自己思考1个半小时没有找到dp方程
 * @author Administrator
 *
 */
public class OutofBounaryPaths576 {
	private int count;
	private int[][] dis = new int[][] { new int[] { -1, 0 }, new int[] { 1, 0 }, new int[] { 0, -1 }, new int[] { 0, 1 } };

	/**
	 * O(4^n)
	 * @param m
	 * @param n
	 * @param N
	 * @param i
	 * @param j
	 * @return
	 */
	public int findPaths(int m, int n, int N, int i, int j) {
		count = 0;
		move(m, n, N, i, j, 0);
		return (int) (count%(Math.pow(10, 9)+7));
	}

	private void move(int m, int n, int N, int i, int j, int step) {
		if (step > N)
			return;
		if (i == -1 || j == -1 || i == m || j == n) {
			count++;
			return;
		}
		for (int k = 0; k < dis.length; k++) {
			int newi = i + dis[k][0];
			int newj = j + dis[k][1];
			if (newi >= -1 && newi <= m && newj >= -1 && newj <= n) {
				move(m, n, N, newi, newj, step + 1);
			}
		}
	}
	
	
	public static void main(String[] args) {
		int r = new OutofBounaryPaths576().findPaths(2, 2, 2, 0, 0);
		System.out.println(r);
	}
}
