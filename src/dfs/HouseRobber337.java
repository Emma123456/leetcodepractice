package dfs;

import java.util.HashMap;
import java.util.Map;

public class HouseRobber337 {
	public int rob(TreeNode root) {
		return dfs(root, true);
	}

	private int dfs(TreeNode node, boolean robAble) {
		if (node == null) {
			return 0;
		}
		if (node.left == null && node.right == null) {
			return robAble ? node.val : 0;
		}

		// 不抢node
		int val = dfs(node.left, true) + dfs(node.right, true);
		// 抢劫node
		if (robAble) {
			val = Math.max(val, node.val + dfs(node.left, false) + dfs(node.right, false));
		}
		return val;
	}

	/**
	 * 同rob 思路一样，代码更加简洁
	 * 
	 * @param root
	 * @return
	 */
	public int robV2(TreeNode root) {
		if (root == null)
			return 0;
		int val = 0;
		if (root.left != null) {
			val += robV2(root.left.left) + robV2(root.left.right);
		}
		if (root.right != null) {
			val += robV2(root.right.left) + robV2(root.right.right);
		}
		return Math.max(root.val + val, robV2(root.left) + robV2(root.right));
	}

	/**
	 * 考虑一下，我们重复解决的子问题。
	3
    / \
   2   3
    \   \ 
     3   1
     * 例如解决根节点3的时候 需要rob(2),rob(3),rob(2->3),rob(3->1);
     * 在解决节点2的时候也需要rob(2->3)。重复了。
	 * @param root
	 * @return
	 */
	public int robV3(TreeNode root) {
		return dfsV3(root, new HashMap<TreeNode, Integer>());
	}

	/**
	 * 抢劫 root，（前提就是root被抢劫是可允许的）
	 * @param root
	 * @param hashMap
	 * @return
	 */
	private int dfsV3(TreeNode root, HashMap<TreeNode, Integer> hashMap) {
		if (root == null)
			return 0;
		if (hashMap.get(root) != null)
			return hashMap.get(root);

		int val = 0;
		if (root.left != null) {
			val += dfsV3(root.left.left, hashMap) + dfsV3(root.left.right, hashMap);
		}
		if (root.right != null) {
			val += dfsV3(root.right.left, hashMap) + dfsV3(root.right.right, hashMap);
		}
		int max = Math.max(root.val + val, dfsV3(root.left, hashMap) + dfsV3(root.right, hashMap));
		hashMap.put(root, max);
		return max;
	}
	
	public int robV4(TreeNode root) {
		int[] res = robNode(root);
		return Math.max(res[0], res[1]);
	}

	/**
	 * 把问题定义为抢劫root的时候，如果抢劫可以获得的收益，如果不抢可以获得收益
	 * 这种思路在DP的时候见过。为每个状态维护一个数组，记录收益最大值
	 * res[0]=不抢劫root的最大收益
	 * res[1]=抢劫root的最大收益
	 * @param root
	 * @return
	 */
	private int[] robNode(TreeNode root) {
		if (root == null)
			return new int[2];
		int[] left = robNode(root.left);
		int[] right = robNode(root.right);
		return new int[] { Math.max(left[0], left[1]) + Math.max(right[0],right[1]), root.val + left[0] + right[0] };
	}
	
	/**
	 * 对rob的改进，缓存状态
	 * @param root
	 * @return
	 */
	public int robV5(TreeNode root) {
		return dfsV5(root, true,new HashMap<TreeNode,Integer>(),new HashMap<TreeNode,Integer>());
	}

	private int dfsV5(TreeNode node, boolean robAble,Map<TreeNode,Integer> canMap,Map<TreeNode,Integer> notMap) {
		if (node == null) {
			return 0;
		}
		if(robAble && canMap.get(node)!=null) return canMap.get(node);
		if(!robAble && notMap.get(node)!=null) return notMap.get(node);
		

		// 不抢node
		int val = dfsV5(node.left, true,canMap,notMap) + dfsV5(node.right, true,canMap,notMap);
		notMap.put(node, val);
		// 抢劫node
		if (robAble) {
			val = Math.max(val, node.val + dfsV5(node.left, false,canMap,notMap) + dfsV5(node.right, false,canMap,notMap));
			canMap.put(node, val);
		}
		return val;
	}
	
	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(3);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(1);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node3.right = node5;
		int r = new HouseRobber337().robV5(node1);
		System.out.println(r);
	}
}
