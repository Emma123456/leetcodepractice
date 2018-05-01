package dfs;

public class UF {
	private int[] id;
	private int count;

	public UF(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
	}

	public int count() {
		return count;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	private int find(int p) {
		while (p != id[p]) {
			// 将p节点的父节点设置为它的爷爷节点
			id[p] = id[id[p]];
			p = id[p];
		}
		return p;
	}

	public void union(int p, int q) {
		// Give p and q the same root.
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot == qRoot)
			return;
		id[pRoot] = qRoot;
		count--;
	}
}
