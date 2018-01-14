package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal94 {
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if(root!=null){
			traversal(root,result);
		}
		return result;
	}
	private void traversal(TreeNode node,List<Integer> result) {
		if(node.left!=null){
			traversal(node.left,result);
		}
		result.add(node.val);
		if(node.right!=null){
			traversal(node.right,result);
		}
	}
	
	/**
	 * 改成迭代版本
	 * 这个版本与 BSTIteratorV3 的代码很类似
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversalV2(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode curNode = root;
		while(curNode!=null || !stack.isEmpty()){
			while(curNode!=null){
				stack.push(curNode);
				curNode = curNode.left;
			}
			TreeNode node = stack.pop();
			curNode = node.right;
			result.add(node.val);
		}
		return result;
	}
}
