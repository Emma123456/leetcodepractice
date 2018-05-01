package dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindLargestValue515 {
	/**
	 * BFS
	 * @param root
	 * @return
	 */
	public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        if(root == null) return list;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            while(size>0){
                TreeNode node = queue.poll();
                max = Math.max(max,node.val);
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
                size--;
            }
            list.add(max);
        }
        return list;
    }
	
	/**
	 * DFS
	 * @param root
	 * @return
	 */
	public List<Integer> largestValuesV2(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        if(root == null) return list;
        dfs(root,1,list);
        return list;
    }
    private void dfs(TreeNode node,int curDepth,List<Integer> list){
        if(list.size()<curDepth){
            list.add(node.val);
        }else{
            if(list.get(curDepth-1)<node.val){
                list.set(curDepth-1,node.val);
            }
        }
        if(node.left!=null){
            dfs(node.left,curDepth+1,list);
        }
        if(node.right!=null){
            dfs(node.right,curDepth+1,list);
        }
        
    }
    public static void main(String[] args) {
		char[][] aa = new char[4][];
		aa[0][0] = 48+8;
		System.out.println(aa[0]);
	}
}
