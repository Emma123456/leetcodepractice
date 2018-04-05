package array;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class ConstructBinaryTree {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
	}

	public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
		System.out.println(preStart+"\t"+preEnd);
		if (preStart > preEnd || preStart>=preorder.length) {
			return null;
		}
		if (inStart > inEnd || inStart>=inorder.length) {// =?
			return null;
		}
		int rootVal = preorder[preStart];
		int i = inStart;
		while (i <= inEnd && inorder[i] != rootVal) {
			i++;
		}
		TreeNode root = new TreeNode(rootVal);
		int leftLen = i - inStart;
		root.left = buildTree(preorder, preStart + 1, (preStart + 1)  + leftLen-1, inorder, inStart, inStart + leftLen - 1);
		root.right = buildTree(preorder, preStart + leftLen+1, preEnd, inorder, i + 1, inEnd);
		return root;
	}

	public static void main(String[] args) {
		new ConstructBinaryTree().buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
	}

}
