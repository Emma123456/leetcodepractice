package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal144 {
	/**
	 * 二叉树的先序遍历
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if(root!=null){
			visit(root,result);
		}
		return result;
    }
	
	
	private void visit(TreeNode root, List<Integer> result) {
		result.add(root.val);
		if(root.left!=null){
			visit(root.left,result);
		}
		if(root.right!=null){
			visit(root.right,result);
		}
	}

	/**
	 * if判断的位置会改变程序的运行时间
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversalV2(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while(!stack.isEmpty()){
			TreeNode node = stack.pop();
			if(node!=null){
				result.add(node.val);
				stack.push(node.right);
				stack.push(node.left);
			}
		}
		return result;
	}

	/**
	 * 用了更少的变量，直接放右节点到栈中
	 * @param node
	 * @return
	 */
	public List<Integer> preorderTraversalV3(TreeNode node) {
		List<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> rights = new Stack<TreeNode>();
		while(node != null){
			result.add(node.val);
			if(node.right!=null){
				rights.add(node.right);
			}
			node = node.left;
			if(node==null && !rights.isEmpty()){
				node = rights.pop();
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
