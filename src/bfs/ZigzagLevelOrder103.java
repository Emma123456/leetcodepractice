package bfs;

import java.util.*;

/**
 * 这道题目与102比较。
 */
public class ZigzagLevelOrder103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root!=null) queue.offer(root);
        boolean reverse = false;
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            }
            if(reverse){
                Collections.reverse(list);
            }
            reverse = !reverse;
            result.add(list);
        }
        return result;
    }

    /**
     * dfs
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrderV2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        zigzagDfs(root,result,1);
        boolean reverse = false;
        return result;
    }

    private void zigzagDfs(TreeNode root, List<List<Integer>> result, int level) {
        if(root ==null) return;
        if(result.size()<level){
            result.add(new ArrayList<Integer>());
        }
        if(level%2==0){
            result.get(level-1).add(0,root.val);
        }else{
            result.get(level-1).add(root.val);
        }
        zigzagDfs(root.left,result,level+1);
        zigzagDfs(root.right,result,level+1);
    }
    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(0,3);
        System.out.println(list);
    }

}
