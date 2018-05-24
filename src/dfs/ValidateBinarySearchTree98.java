package dfs;

public class ValidateBinarySearchTree98 {
	public boolean isValidBST(TreeNode root) {
		return isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
	}

	private boolean isValidBST(TreeNode root, long maxVal, long minVal) {
		if (root == null)
			return true;
		if (root.val > minVal && root.val < maxVal) {
			return isValidBST(root.left, root.val, minVal) && isValidBST(root.right, maxVal, root.val);
		} else {
			return false;
		}
	}
}
