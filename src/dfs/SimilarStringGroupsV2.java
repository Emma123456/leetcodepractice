package dfs;

import java.util.Arrays;

public class SimilarStringGroupsV2 {
	/**
	 * 首先写一个函数similar，判断两个字符串是否相似
	 * 其次，输入有n个字符串，每个字符串两两比较，相似的字符串放入一组。使用Union-Find实现。
	 * @param A
	 * @return
	 */
	public int numSimilarGroups(String[] A) {
		int N = A.length;
		DSU dsu = new DSU(N);
		for (int i = 0; i < N; ++i)
			for (int j = i + 1; j < N; ++j)
				if (similar(A[i], A[j]))
					dsu.union(i, j);
		return dsu.getGroupCount();
	}

	public boolean similar(String word1, String word2) {
		int diff = 0;
		for (int i = 0; i < word1.length(); ++i)
			if (word1.charAt(i) != word2.charAt(i))
				diff++;
		return diff <= 2;
	}

	public void swap(char[] A, int i, int j) {
		char tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
}

class DSU {
	private int[] parent;
	private int[] rank;
	private int group;

	public DSU(int n) {
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

	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}
}
