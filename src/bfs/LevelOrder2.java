package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***
 * 这个递归版使用的有点勉强
 */
public class LevelOrder2 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levelTraversal = new ArrayList<List<Integer>>();
        levelMaker(root, levelTraversal, 0);
        return levelTraversal;
    }

    private void levelMaker(TreeNode node, List<List<Integer>> result, int level) {
        if (node == null) return;
        if (result.size() <= level) {
            result.add(0, new ArrayList<Integer>());
        }
        result.get(result.size() - 1 - level).add(node.val);
        levelMaker(node.left, result, level + 1);
        levelMaker(node.right, result, level + 1);
    }


    public List<List<Integer>> levelOrderBottomV2(TreeNode root) {
        List<List<Integer>> levelTraversal = new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root!=null){
            queue.add(root);
        }
        while(!queue.isEmpty()){
            int size = queue.size();//长度
            List<Integer> level = new ArrayList<Integer>();
            for(int i=0;i<size;i++){//遍历本次元素
                TreeNode node = queue.poll();
                level.add(node.val);//做事情
                //添加子节点
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }
            levelTraversal.add(0,level);
        }
        return levelTraversal;
    }
}
