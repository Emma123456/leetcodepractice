package dfs;

public class SumRootToLeafNumbers129 {

	public int sumNumbers(TreeNode root) {
		return dfs(root, 0);
	}

	private int dfs(TreeNode node, int preval) {
		if (node == null)
			return 0;
		int val = preval * 10 + node.val;
		if (node.left == null && node.right == null) {
			return val;
		}
		return dfs(node.left, val) + dfs(node.right, val);
	}

	private int sum;

	public int sumNumbersV2(TreeNode root) {
		sum = 0;
		dfsV2(root, 0);
		return sum;
	}

	/**
	 * 我总是习惯用全局变量来确定返回值
	 * 
	 * @param node
	 * @param preval
	 */
	private void dfsV2(TreeNode node, int preval) {
		if (node == null)
			return;
		int val = preval * 10 + node.val;
		if (node.left == null && node.right == null) {
			sum += val;
		} else {
			dfsV2(node.left, val);
			dfsV2(node.right, val);
		}
	}
}
