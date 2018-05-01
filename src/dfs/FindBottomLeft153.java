package dfs;

import java.util.LinkedList;
import java.util.Queue;

public class FindBottomLeft153 {
	private int leftVal;
    private int depth = 0;
    public int findBottomLeftValue(TreeNode root) {
        leftVal = root.val;
        depth = 1;
        dfs(root,1);
        return leftVal;
    }
    private void dfs(TreeNode node,int curDepth){
        if(node==null) return;    
        dfs(node.left,curDepth+1);
        dfs(node.right,curDepth+1);    
        if(curDepth>depth){
            depth = curDepth;
            leftVal = node.val;
        }
                
    }
    /**
     * 也可以用BFS的策略
     * @param root
     * @return
     */
    public int findBottomLeftValueV2(TreeNode root) {
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	while(!queue.isEmpty()){
    		root = queue.poll();
    		if(root.right!=null){
    			queue.add(root.right);
    		}
    		if(root.left!=null){
    			queue.add(root.left);
    		}
    	}
    	return root.val;
    }
}
