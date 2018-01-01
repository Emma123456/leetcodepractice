package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BinaryTreeZigzag103 {
	/**
	 * 栈与递归 
	 * O(n^2)每个节点要访问两次
	 * @param root
	 * @return
	 */
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(root!=null){
			Stack<TreeNode> nodeStack = new Stack<TreeNode>();
			nodeStack.push(root);
			result.add(Arrays.asList(root.val));
			visit(result,nodeStack,false);
		}
		return result;
    }
	/**
	 * 
	 * @param result
	 * @param nodeStack
	 * @param readLeft
	 * 			从左节点开始读吗
	 */
	private void visit(List<List<Integer>> result, Stack<TreeNode> nodeStack, boolean readLeft) {
		Stack<TreeNode> newStack = new Stack<TreeNode>();
		List<Integer> values = new ArrayList<Integer>();
		while (!nodeStack.isEmpty()) {
			TreeNode node = nodeStack.pop();
			if (readLeft) {
				if (node.left != null) {
					newStack.add(node.left);
					values.add(node.left.val);
				}
				if (node.right != null) {
					newStack.add(node.right);
					values.add(node.right.val);
				}
			}else{
				if (node.right != null) {
					newStack.add(node.right);
					values.add(node.right.val);
				}
				if (node.left != null) {
					newStack.add(node.left);
					values.add(node.left.val);
				}
			}
		}
		if(!newStack.isEmpty()){
			result.add(values);
			visit(result, newStack, !readLeft);
		}
	}
	
	/**
	 * 
	 * @param root
	 * @return
	 */
	public List<List<Integer>> zigzagLevelOrderV2(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Stack<TreeNode> nodeStack = new Stack<TreeNode>();
		if(root!=null){
			nodeStack.push(root);
			result.add(Arrays.asList(root.val));
		}
		boolean readLeft = false;
		while(!nodeStack.isEmpty()){
			int size = nodeStack.size();
			Stack<TreeNode> newStack = new Stack<TreeNode>();
			List<Integer> values = new ArrayList<Integer>();
			while(size>0){
				size --;
				TreeNode node = nodeStack.pop();
				if(readLeft){
					if (node.left != null) {
						newStack.add(node.left);
						values.add(node.left.val);
					}
					if (node.right != null) {
						newStack.add(node.right);
						values.add(node.right.val);
					}
				}else{
					if (node.right != null) {
						newStack.add(node.right);
						values.add(node.right.val);
					}
					if (node.left != null) {
						newStack.add(node.left);
						values.add(node.left.val);
					}
				}
			}
			nodeStack = newStack;
			readLeft = !readLeft;
			if(!values.isEmpty()){
				result.add(values);
			}
		}
		return result;
    }
	
	/**
	 * 深度优先搜索
	 * 这种思路是：节点从左到右遍历，访问值的时候判断是正序还是逆序
	 * 我的问题还是思考的时候分不清该遍历节点呢还是该访问值
	 * @param root
	 * @return
	 */
	public List<List<Integer>> zigzagLevelOrderV3(TreeNode root) {
		List<List<Integer>> solution = new ArrayList<List<Integer>>();
		travel(root,solution,0);
		return solution;		
	}
	
	private void travel(TreeNode node, List<List<Integer>> solution, int level) {
		// 先判断退出条件
		if (node == null) {
			return;
		}
		//level相同的遍历可能会进来两次，所以有判断
		if (solution.size() <= level) {
			solution.add(new ArrayList<Integer>());
		}
		List<Integer> collection = solution.get(level);
		if (level % 2 == 0) {
			collection.add(node.val);
		} else {
			collection.add(0, node.val);
		}
		travel(node.left, solution, level + 1);
		travel(node.right, solution, level + 1);
	}
	public static void main(String[] args) {

	}

}
