package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> resultList = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root!=null) queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            int value = -1;
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                value = node.val;

                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            }
            resultList.add(value);
        }


        return resultList;
    }

    /**
     * DFS
     * @param root
     * @return
     */
    public List<Integer> rightSideViewV2(TreeNode root) {
        List<Integer> resultList = new ArrayList<Integer>();
        leveOrder(root,1,resultList);
        return resultList;
    }

    private void leveOrder(TreeNode root, int level, List<Integer> resultList) {
        if(root==null) return;
        if(resultList.size()<level){
            resultList.add(root.val);
        }
        leveOrder(root.right,level+1,resultList);
        leveOrder(root.left,level+1,resultList);

    }
}
