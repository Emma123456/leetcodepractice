package dfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MaxmuimDepthOfBT {
	/**
	 * 递推公式：1+最大子节点的高度
	 * 
	 * @param root
	 * @return
	 */
	public int maxDepth(TreeNode root) {
		return visit(root);
	}

	private int visit(TreeNode node) {
		if (node == null)
			return 0;
		return Math.max(visit(node.left), visit(node.right)) + 1;
	}

	/**
	 * 用栈来记录递归过程中的一些变量
	 * DFS
	 * @param root
	 * @return
	 */
	public int maxDepthV2(TreeNode root) {
		if(root==null) return 0;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Stack<Integer> value =new Stack<Integer>();
		stack.push(root);
		value.push(1);
		int max = 0;
		while(!stack.isEmpty()){
			int val = value.pop();
			max = Math.max(max, val);
			TreeNode node = stack.pop();
			if(node.left!=null){
				stack.push(node.left);
				value.push(val+1);
			}
			if(node.right!=null){
				stack.push(node.right);
				value.push(val+1);
			}
		}
		return max;
	}

	/**
	 * using BFS
	 * 
	 * @param root
	 * @return
	 */
	public int maxDepthV3(TreeNode root) {
		if (root == null)
			return 0;
		Queue<TreeNode> nodes = new LinkedList<TreeNode>();
		nodes.offer(root);
		int level = 0;
		while (!nodes.isEmpty()) {
			level++;
			int size = nodes.size();
			while(size-->0){
				TreeNode node = nodes.poll();
				if (node.left != null) {
					nodes.offer(node.left);
				}
				if (node.right != null) {
					nodes.offer(node.right);
				}
			}
		}
		return level;
	}
}
