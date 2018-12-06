package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeLevelOrderTraversal429 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<Node> queue = new LinkedList<Node>();
        if(root!=null){
            queue.add(root);
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> order = new ArrayList<Integer>(size);
            for(int i=0;i<size;i++){
                Node node = queue.peek();//访问节点
                order.add(node.val);//做事情，关于这个节点的操作
                if(node.children!=null){
                    queue.addAll(node.children);//子节点进入队列
                }
            }
            result.add(order);
        }
        return result;
    }
}
