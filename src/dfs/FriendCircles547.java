package dfs;

public class FriendCircles547 {
	public int findCircleNum(int[][] M) {
		int m = M.length;
		int group = 0;
		boolean[] visited = new boolean[m];
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				dfs(i, M, visited);
				group++;
			}
		}
		return group;
	}

	/**
	 * 把与i相关的都遍历一遍
	 * 
	 * @param i
	 * @param m
	 * @param visited
	 */
	private void dfs(int i, int[][] m, boolean[] visited) {
		visited[i] = true;
		for (int j = 0; j < visited.length; j++) {
			if (i != j && m[i][j] == 1 && !visited[j]) {
				dfs(j, m, visited);
			}
		}
	}

	/**
	 * 并查集的思想就是id[x] = parent。每个数组的值是该值所在节点的父节点
	 * 
	 * @param M
	 * @return
	 */
	public int findCircleNumV2(int[][] M) {
		if (M == null)
			return 0;
		int n = M.length;
		int[] id = new int[n];
		for (int i = 0; i < n; i++) {
			id[i] = i;
		}
		int group = n;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (M[i][j] == 1) {
					int parent1 = find(i, id);
					int parent2 = find(j, id);
					if (parent1 != parent2) {
						id[parent1] = parent2;
						group--;
					}
				}
			}
		}
		return group;
	}

	private int find(int val, int[] id) {
		return id[val] == val ? val : find(id[val], id);
	}

	public static void main(String[] args) {
		int[][] M = new int[][] { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
		int g = new FriendCircles547().findCircleNumV2(M);
		System.out.println(g);
	}
}
