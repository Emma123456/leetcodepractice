package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepth559 {
	public int maxDepth(Node root) {
		if (root == null)
			return 0;
		int level = 0;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			level++;//做事情
			int size = queue.size();
			for(int i=0;i<size;i++){
				Node node = queue.remove();//访问节点
				if(node.children!=null){
					queue.addAll(node.children);//访问子节点
				}
			}
		}

		return level;
	}
	/**
	 * 哇，迭代速度更快
	 * @param root
	 * @return
	 */
	public int maxDepthV2(Node root) {
		if(root==null) return 0;
		if(root.children==null || root.children.isEmpty()) return 1;
		int maxDepth = 0;
		for(Node child : root.children){
			maxDepth = Math.max(maxDepth, maxDepthV2(child));
		}
		return maxDepth+1;
	}
}
