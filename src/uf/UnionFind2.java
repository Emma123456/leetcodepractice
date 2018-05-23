package uf;


public class UnionFind2 {
	private int[] parent;
	private int[] rank;

	public UnionFind2(int n) {
		init(n);
	}

	/**
	 * 初始化元素，初始化的时候每个元素在一个组；parent[i]=i 表i是根节点
	 * 
	 * @param n
	 */
	private void init(int n) {
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
		rank = new int[n];
	}

	/**
	 * 查找val的根节点
	 * 
	 * @param val
	 * @return
	 */
	public int find(int val) {
		if (parent[val] == val) {
			return val;
		}
		parent[val] = find(parent[val]);
		return parent[val];
	}

	public void union(int val1, int val2) {
		int f1 = find(val1);
		int f2 = find(val2);
		if (f1 == f2)
			return;
		if (rank[f1] < rank[f2]) {
			parent[f2] = f1;
			rank[f1] += rank[f2];
		} else {
			parent[f1] = f2;
			rank[f2] += rank[f1];
		}
	}

}
