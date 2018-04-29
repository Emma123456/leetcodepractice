package dfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SymmetricTree101 {
	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;
		return isSymmetric(root.left, root.right);
	}

	private boolean isSymmetric(TreeNode node1, TreeNode node2) {
		if (node1 == null && node2 == null)
			return true;
		if (node1 == null && node2 != null || node1 != null && node2 == null)
			return false;
		if (node1.val != node2.val)
			return false;
		return isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left);
	}
	
	/**
	 * 迭代
	 * @param root
	 * @return
	 */
	public boolean isSymmetricV2(TreeNode root) {
		if (root == null)
			return true;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root.right);
		stack.push(root.left);
		boolean symmetric = true;
		while(stack.size()>1){
			TreeNode node2 = stack.pop();
			TreeNode node1 = stack.pop();
			if (node1 == null && node2 == null){
				continue;
			}
			if (node1 == null && node2 != null || node1 != null && node2 == null){
				symmetric =  false;
				break;
			}
			if (node1.val != node2.val){
				symmetric =  false;
				break;
			}
			stack.push(node2.left);
			stack.push(node1.right);
			
			stack.push(node2.right);
			stack.push(node1.left);
		}
		return symmetric;
	}
	
	/**
	 * Iterative 用队列也可以
	 * @param root
	 * @return
	 */
	public boolean isSymmetricV3(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		queue.add(root);
		while(!queue.isEmpty()){
			TreeNode node1 = queue.poll();
			TreeNode node2 = queue.poll();
			if(node1==null && node2==null) continue;
			if(node1==null || node2==null) return false;
			if(node1.val!=node2.val) return false;
			queue.add(node1.left);
			queue.add(node2.right);
			
			queue.add(node1.right);
			queue.add(node2.left);
		}
		return true;
	}
}
