package dfs;

public class BinaryTreeMaximumPathSum {
	private int maxSum;
	public int maxPathSum(TreeNode root) {
		maxSum = Integer.MIN_VALUE;
		dfs(root);
		return maxSum;
	}

	private int dfs(TreeNode root) {
		if (root == null)
			return 0;
		int leftSum = dfs(root.left);
		int rightSum = dfs(root.right);
		maxSum = Math.max(maxSum, root.val + Math.max(leftSum, 0) + Math.max(rightSum, 0));
		maxSum = Math.max(maxSum, root.val);
		return Math.max(root.val,Math.max(root.val+leftSum, root.val+rightSum));
	}
}
