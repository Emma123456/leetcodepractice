package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumDepth111 {
    /**
     * DFS思路
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        if(root.left==null && root.right==null) return 1;
        if(root.left==null) return minDepth(root.right)+1;
        if(root.right==null) return minDepth(root.left)+1;
        return Math.min(minDepth(root.left),minDepth(root.right))+1;
    }

    /**
     * BFS ，每次处理一层节点。如果当层节点有叶子节点，则处理完毕。
     * @param root
     * @return
     */
    public int minDepthV2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root!=null) queue.offer(root);
        int deep = 0;
        while (!queue.isEmpty()) {
            //做事情
            deep++;
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();//访问节点
                if(node.left==null && node.right==null){
                    return deep;
                }
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);

            }
        }
        return deep;

    }
}
