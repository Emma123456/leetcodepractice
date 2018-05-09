package dfs;

public class FlattenBinaryTree114 {
	/**
	 * dfs的思路
	 * 今天的收获是：起一个好的方法名字有利于自己思路形成。以前的代码都直接将方法命名为dfs，其实不知道在做什么。今天的方法名flattenNode，是对node这一个节点做展开。
	 * 能够理清楚一个节点做展开应该做哪些事情，递归就好写了。
	 * @param root
	 */
	public void flatten(TreeNode root) {
        if(root==null) return;
        flattenNode(root);
    }
    /**
     * 展开节点node，并且返回展开后的节点
     * @param node
     * @return
     */
    private TreeNode flattenNode(TreeNode node){
        if(node==null || (node.left==null && node.right==null)) return node;
        if(node.left!=null){
            TreeNode newLeft = flattenNode(node.left);
            TreeNode newRight = flattenNode(node.right); 
            node.left = null;
            node.right = newLeft;
            TreeNode r = node.right;
            while(r!=null && r.right!=null){
                r = r.right;
            }
            r.right = newRight;
        }else{
            node.right = flattenNode(node.right);  
        }
        return node;
    }
}
