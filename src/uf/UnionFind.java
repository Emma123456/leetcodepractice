package uf;

import java.util.Arrays;

public class UnionFind {
	private int[] nums;
	private int group;

	public UnionFind(int n) {
		init(n);
	}

	/**
	 * 初始化元素，初始化的时候每个元素在一个组；nums[i]=-1表i是根节点
	 * 
	 * @param n
	 */
	private void init(int n) {
		nums = new int[n];
		Arrays.fill(nums, -1);
		this.group = n;
	}

	/**
	 * 查找val的根节点
	 * 
	 * @param val
	 * @return
	 */
	public int find(int val) {
		if (nums[val] == -1) {
			return val;
		}
		nums[val] = find(nums[val]);
		return nums[val];
	}

	public void union(int val1, int val2) {
		int f1 = find(val1);
		int f2 = find(val2);
		if (f1 != f2) {
			nums[f1] = f2;
			group--;
		}
	}

	public int getGroupCount() {
		return this.group;
	}
}
