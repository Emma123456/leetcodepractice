package dfs;

public class BalancedBinaryTree110 {
	public boolean isBalanced(TreeNode root) {
        if(root==null) return true;
        int l = depth(root.left);
        int r = depth(root.right);
        return l!=-1 && r!=-1 && Math.abs(l-r)<=1;
    }
    
    private int depth(TreeNode node){
        if(node==null) return 0;
        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);
        if(leftDepth==-1 || rightDepth==-1 || Math.abs(leftDepth-rightDepth)>1) return -1;
        return 1+Math.max(leftDepth,rightDepth);
    }
}
