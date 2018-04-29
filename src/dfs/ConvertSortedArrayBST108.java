package dfs;


public class ConvertSortedArrayBST108 {
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0)
			return null;
		return createNode(nums, 0, nums.length - 1);
	}

	private TreeNode createNode(int[] nums, int start, int end) {
		int middle = start + (end - start + 1) / 2;
		TreeNode node = new TreeNode(nums[middle]);
		if (start < middle) {
			node.left = createNode(nums, start, middle - 1);
		}
		if (end > middle) {
			node.right = createNode(nums, middle + 1, end);
		}
		
		return node;
	}
}
