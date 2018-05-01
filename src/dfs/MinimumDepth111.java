package dfs;

public class MinimumDepth111 {
	private int min = Integer.MAX_VALUE;

	/**
	 * 注意：不能简单的用 Math.min(minDepth(root.left),minDepth(root.right))+1来递归；
	 * 因为深度一定是从根节点到叶子节点。只有到了叶子节点才算数。
	 * 
	 * @param root
	 * @return
	 */
	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		min = Integer.MAX_VALUE;
		minDepth(root, 0);
		return min;
	}

	private void minDepth(TreeNode node, int curDepth) {
		if (node.left == null && node.right == null) {
			min = Math.min(min, curDepth + 1);
		} else {
			if (node.left != null) {
				minDepth(node.left, curDepth + 1);
			}
			if (node.right != null) {
				minDepth(node.right, curDepth + 1);
			}
		}
	}

	public int minDepthV2(TreeNode root) {
		if (root == null)
			return 0;
		if(root.left ==null){
			return 1 + minDepthV2(root.right);
		}
		if(root.right ==null){
			return 1 + minDepthV2(root.left);
		}
		int leftDepth = minDepthV2(root.left);
		int rightDepth = minDepthV2(root.right);
		return Math.min(leftDepth, rightDepth)+1;
	}

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		node1.left = node2;
		new MinimumDepth111().minDepth(node1);
	}
}
