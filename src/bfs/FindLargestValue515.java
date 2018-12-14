package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindLargestValue515 {
    /**
     * 一看题目就知道层次遍历最合适
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root!=null) queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            int maxValue = Integer.MIN_VALUE;
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                maxValue = Math.max(maxValue,node.val);
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            }
            result.add(maxValue);
        }
        return result;
    }

    /**
     * 用DFS处理层次遍历的问题，需要把结果以及层次 对应保存下来
     * @param root
     * @return
     */
    public List<Integer> largestValuesV2(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        dfs(root,1,result);
        return result;
    }

    private void dfs(TreeNode root, int level, List<Integer> result) {
        if(root==null) return;
        if(result.size()<level){
            result.add(root.val);
        }else if(result.get(level-1)<root.val){
            result.set(level-1,root.val);
        }
        dfs(root.left,level+1,result);
        dfs(root.right,level+1,result);
    }
}
