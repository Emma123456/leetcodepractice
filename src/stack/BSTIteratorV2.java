package stack;

import java.util.Stack;

public class BSTIteratorV2 {
	private Stack<TreeNode> stack = new Stack<TreeNode>();
	public BSTIteratorV2(TreeNode root) {
        fillStack(root);
    }

	private void fillStack(TreeNode node) {
		for(;node!=null;node = node.left){
			stack.push(node);
		}
	}

	/** @return whether we have a next smallest number */
    public boolean hasNext() {
    	return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
    	TreeNode tmpNode = stack.pop();
    	fillStack(tmpNode.right);
    	return tmpNode.val;
    }
}
