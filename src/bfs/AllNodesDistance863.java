package bfs;

import java.util.*;

/**
 * 思路1：先找到根节点距离target node 的距离 rootDistance；
 * 其次，存储所有节点与父节点的map
 * 最后，一个节点距离目标节点的距离t = 其父节点距离目标的节点 + 1
 *
 * 第三步有漏洞。
 */
public class AllNodesDistance863 {
    /***
     * BFS思路
     * @param root
     * @param target
     * @param K
     * @return
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<Integer>();
        Map<TreeNode,TreeNode> parentNodeMap = new HashMap<TreeNode, TreeNode>();
        dfsParent(root,null,parentNodeMap);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Set<TreeNode> seen = new HashSet<TreeNode>();
        queue.offer(target);
        seen.add(target);
        int distance = 0;

        while(!queue.isEmpty()){
            int size = queue.size();
            if(distance==K){
                for(int i=0;i<size;i++){
                    TreeNode node = queue.poll();
                    result.add(node.val);
                }
            }else{
                for(int i=0;i<size;i++){
                    TreeNode node = queue.poll();
                    if(node.left!=null && !seen.contains(node.left)) {
                        queue.offer(node.left);
                        seen.add(node.left);
                    }
                    if(node.right!=null && !seen.contains(node.left)) {
                        queue.offer(node.right);
                        seen.add(node.right);
                    }
                    if(parentNodeMap.get(node)!=null && !seen.contains(parentNodeMap.get(node))) {
                        queue.offer(parentNodeMap.get(node));
                        seen.add(parentNodeMap.get(node));
                    }
                }
            }
            distance++;
        }

        return result;
    }

    private void dfsParent(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> parentNodeMap) {
        if(node!=null){
            parentNodeMap.put(node,parent);
            dfsParent(node.left,node,parentNodeMap);
            dfsParent(node.right,node,parentNodeMap);
        }
    }

    List<Integer> result;
    TreeNode target;
    int K;

    /***
     * DFS思路
     * @param root
     * @param target
     * @param K
     * @return
     */
    public List<Integer> distanceKV2(TreeNode root, TreeNode target, int K) {
        result = new ArrayList<Integer>();
        this.target = target;
        this.K = K;
        dfs(root,0);
        return result;
    }

    /**
     * 返回node距离target的顶点距离。如果不存在，返回-1
     * @param node
     * @param level
     * @return
     */
    private int dfs(TreeNode node,int level) {
        if(node == null) return -1;
        if(node == target){
            //添加子节点
            subTree(node,0);
            return 1;
        }else{
            int L = dfs(node.left,level+1);
            int R = dfs(node.right,level+1);
            if(L!=-1){
                if(L==K) result.add(node.val);
                subTree(node.right,L+1);
                return L+1;
            }else if(R!=-1){
                if(R==K) result.add(node.val);
                subTree(node.left,R+1);
                return R+1;
            }else{
                return -1;
            }
        }
    }

    private void subTree(TreeNode node, int level) {
        if(level>K) return;
        if(node!=null){
            if(level==K){
                result.add(node.val);
            }else{
                subTree(node.left,level+1);
                subTree(node.right,level+1);
            }
        }
    }
}
