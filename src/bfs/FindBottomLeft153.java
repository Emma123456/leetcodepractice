package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class FindBottomLeft153 {
    /**
     * 套用BFS的模板直观实现
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root!=null) queue.offer(root);
        int value = 0;
        while (!queue.isEmpty()) {
            //做事情
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();//访问节点
                if(i==0) value = node.val;
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);

            }
        }
        return value;
    }

    private int recordLevel = 0;
    private int value = 0;
    /**
     * DFS
     * @param root
     * @return
     */
    public int findBottomLeftValueV2(TreeNode root) {
        dfs(root,1);
        return value;
    }

    private void dfs(TreeNode root, int curLevel) {
        if(root==null) return;
        if(curLevel>recordLevel){
            value = root.val;
            recordLevel = curLevel;
        }
        dfs(root.left,curLevel+1);
        dfs(root.right,curLevel+1);
    }
}
