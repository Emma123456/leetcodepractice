package stack;

import java.util.Stack;

public class BSTIteratorV3 {
	private Stack<TreeNode> stack;
	private TreeNode cur = null;
	public BSTIteratorV3(TreeNode root){
		cur = root;
		stack = new Stack<TreeNode>();
	}
	public boolean hasNext() {
		return !stack.isEmpty() || cur !=null;
	}
	public int next(){
		while(cur!=null){
			stack.push(cur);
			cur = cur.left;
		}
		TreeNode node = stack.pop();
		//我缺少的是这一步
		cur = node.right;
		return node.val;
	}
}
