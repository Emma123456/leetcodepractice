package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal102 {
    /**
     * BFS
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root!=null) queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                list.add(node.val);

                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            }
            resultList.add(list);
        }


        return resultList;
    }

    /***
     * DFS 记下结果以及结果对应的层次
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderV2(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        leveOrder(root,1,resultList);
        return resultList;
    }

    private void leveOrder(TreeNode root, int level, List<List<Integer>> resultList) {
        if(root==null) return;
        if(resultList.size()<level){
            resultList.add(new ArrayList<Integer>());
        }
        resultList.get(level-1).add(root.val);
        leveOrder(root.left,level+1,resultList);
        leveOrder(root.right,level+1,resultList);
    }
}
