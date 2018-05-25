package uf;

import java.util.Arrays;

public class UnionFind2 {
	private int[] parent;
	private int[] rank;
	private int group;

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
		this.group = n;
		rank = new int[n];
		Arrays.fill(rank, 1);
	}

	/**
	 * 查找val的根节点
	 * 
	 * @param p
	 * @return
	 */
	public int find(int p) {
		while (p != parent[p]) {
			// 将p节点的父节点设置为它的爷爷节点
			parent[p] = parent[parent[p]];
			p = parent[p];
		}
		return p;
	}

	public void union(int val1, int val2) {
		int f1 = find(val1);
		int f2 = find(val2);
		if (f1 == f2)
			return;
		// ་将小树作为大树的子树
		if (rank[f1] < rank[f2]) {
			parent[f1] = f2;
			rank[f2] += rank[f1];
		} else {
			parent[f2] = f1;
			rank[f1] += rank[f2];
		}
		group--;
	}
	public int getGroupCount() {
		return this.group;
	}
	
	public boolean isConnected(int p,int q) {
        return find(p) == find(q);
    }
}
