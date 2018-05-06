package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeRightSideView199 {
	/**
	 * BFS
	 * @param root
	 * @return
	 */
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			int val = 0;
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				val = node.val;
				if (node.left != null)
					queue.add(node.left);
				if (node.right != null)
					queue.add(node.right);
			}
			result.add(val);
		}
		return result;
	}

	/**
	 * DFS 需要遍历所有节点
	 * @param root
	 * @return
	 */
	public List<Integer> rightSideViewV2(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> nodeStack = new Stack<TreeNode>();
		Stack<Integer> depthStack = new Stack<Integer>();
		nodeStack.push(root);
		depthStack.push(0);
		int max_depth = -1;
		Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
		while (!nodeStack.isEmpty()) {
			TreeNode node = nodeStack.pop();
			int depth = depthStack.pop();
			if (node != null) {
				max_depth = Math.max(max_depth, depth);
				if (!rightmostValueAtDepth.containsKey(max_depth)) {
					rightmostValueAtDepth.put(max_depth, node.val);
				}
				nodeStack.push(node.left);
				nodeStack.push(node.right);
				depthStack.push(depth + 1);
				depthStack.push(depth + 1);
			}
		}
		for (int i = 0; i <= max_depth; i++) {
			result.add(rightmostValueAtDepth.get(i));
		}
		return result;
	}

}
