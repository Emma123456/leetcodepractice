package dfs;

import java.util.HashMap;
import java.util.Map;

class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;

	TreeLinkNode(int x) {
		val = x;
	}
}

public class PopulatingNextRight116 {
	public void connect(TreeLinkNode root) {
		Map<Integer, TreeLinkNode> map = new HashMap<Integer, TreeLinkNode>();
		dfs(root, 0, map);
	}

	/**
	 * dfs的话，先遍历一个节点的右子树，在同一深度保持尽可能的右侧节点。但是当同一层的节点称为别的节点的next后，当前节点就成为该层右侧节点了。用一个map来存储。
	 * 
	 * @param node
	 * @param level
	 * @param map
	 */
	private void dfs(TreeLinkNode node, int level, Map<Integer, TreeLinkNode> map) {
		if (node == null)
			return;
		dfs(node.right, level + 1, map);
		dfs(node.left, level + 1, map);
		node.next = map.get(level);
		map.put(level, node);
	}

	/**
	 * 用层次遍历
	 * 
	 * @param root
	 */
	public void connectV2(TreeLinkNode root) {
		if (root == null)
			return;
		while (root != null) {
			TreeLinkNode dummy = new TreeLinkNode(0);
			TreeLinkNode currentChild = dummy;
			while(root!=null){
				if(root.left!=null){
					currentChild.next = root.left;
					currentChild = currentChild.next;
				}
				if(root.right!=null){
					currentChild.next = root.right;
					currentChild = currentChild.next;
				}
				root = root.next;
			}
			root = dummy.next;
		}
	}

	public static void main(String[] args) {
		TreeLinkNode node1 = new TreeLinkNode(1);
		TreeLinkNode node2 = new TreeLinkNode(2);
		TreeLinkNode node3 = new TreeLinkNode(3);
		TreeLinkNode node4 = new TreeLinkNode(4);
		TreeLinkNode node5 = new TreeLinkNode(5);
		TreeLinkNode node6 = new TreeLinkNode(6);
		TreeLinkNode node7 = new TreeLinkNode(7);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		new PopulatingNextRight116().connect(node1);
	}
}
