package dfs;
/**
 * 中序遍历搜索二叉树，遍历的结果应该是一个升序序列。如果发现当前元素<前面的元素，则前面的元素需要换位置。
 * 换到哪个位置呢？可能是当前位置，继续遍历，也可能是其他位置，但肯定是一个前元素<前面的元素的当前位置
 * @author Administrator
 *
 */
public class RecoverBinarySearchTree {
	private TreeNode firstElement = null;
	private TreeNode secondElement = null;
	//初始化preElement，防止在比较的时候空指针
	private TreeNode preElement = new TreeNode(Integer.MIN_VALUE);
	public void recoverTree(TreeNode root) {
		inOrderTranversal(root);
		//交换要修改的结点
		int tempVal = firstElement.val;
		firstElement.val = secondElement.val;
		secondElement.val = tempVal;
	}

	private void inOrderTranversal(TreeNode root) {
		if (root == null)
			return;
		inOrderTranversal(root.left);
		// do sth
		//发现当前值<前一个值，且 firstElement 还未赋值，则把preElement 赋值给firstElement
		if(firstElement == null && preElement.val>root.val){
			firstElement = preElement;
		}
		//发现当前值<前一个值，且 firstElement 已经赋值，则把root 赋值给secondElement
		if(firstElement !=null && preElement.val>root.val){
			secondElement = root;
		}
		preElement = root;
		inOrderTranversal(root.right);
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(3);
		root.left.right = new TreeNode(2);

		RecoverBinarySearchTree reconver = new RecoverBinarySearchTree();
		reconver.recoverTree(root);
	}
}
