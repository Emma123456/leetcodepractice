package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class GraphBipartite {
	/**
	 * 没有考虑到如果在遍历节点i的时候，i还没有进入集合怎么处理 这种思路错误，考虑节点应该从已经处理了的节点开始考虑。
	 * [[1],[0,3],[3],[1,2]]
	 * 这个版本无效
	 * @param graph
	 * @return
	 */
	public boolean isBipartite(int[][] graph) {
		Set<Integer> a = new HashSet<Integer>();
		Set<Integer> b = new HashSet<Integer>();
		a.add(0);
		for (Integer idx : graph[0]) {
			b.add(idx);
		}
		for (int i = 1; i < graph.length; i++) {
			Set<Integer> set1 = a.contains(i) ? a : (b.contains(i) ? b : null);// 包含i顶点的集合
			if (set1 == null) {
				for (Integer idx : graph[i]) {
					if (a.contains(idx)) {
						set1 = b;
						break;
					}
				}
				set1 = (set1 == null ? a : set1);
			}
			Set<Integer> set2 = (set1 == a ? b : a);
			if (!set1.contains(i)) {
				set1.add(i);
			}
			for (Integer idx : graph[i]) {
				if (set1.contains(idx))
					return false;
				set2.add(idx);
			}
		}
		return true;
	}

	public boolean isBipartiteV2(int[][] graph) {
		List<Integer> a = new ArrayList<Integer>();
		List<Integer> b = new ArrayList<Integer>();
		List<Integer> processedIndex = new ArrayList<Integer>();
		a.add(0);
		int idx = 0;
		int idxb = 0;
		boolean change = true;
		while (change) {
			change = false;
			while (idx < a.size()) {
				int fromIdx = a.get(idx);
				processedIndex.add(fromIdx);
				for (Integer toIdx : graph[fromIdx]) {
					if (a.contains(toIdx))
						return false;
					if (!b.contains(toIdx)) {
						b.add(toIdx);
						change = true;
					}
				}
				idx++;
			}
			while (idxb < b.size()) {
				int fromIdx = b.get(idxb);
				processedIndex.add(fromIdx);
				for (Integer toIdx : graph[fromIdx]) {
					if (b.contains(toIdx))
						return false;
					if (!a.contains(toIdx)) {
						a.add(toIdx);
						change = true;
					}
				}
				idxb++;
			}
			if (!change) {
				for (int i = 0; i < graph.length; i++) {
					if (!processedIndex.contains(i)) {
						a.add(i);
						change = true;
						break;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 用colors数组来区分两个集合
	 *  DFS的思路：每次为一个节点分类，不断深入检查这个节点是否有效
	 * 
	 * @param graph
	 * @return
	 */
	public boolean isBipartiteV3(int[][] graph) {
		int n = graph.length;
		int[] colors = new int[n];
		Arrays.fill(colors, -1);
		for (int i = 0; i < n; i++) {
			if (colors[i] == -1 && !validateColor(graph, colors, 0, i)) {
				return false;
			}
		}
		return true;
	}

	private boolean validateColor(int[][] graph, int[] colors, int color, int idx) {
		if (colors[idx] != -1)
			return colors[idx] == color;
		colors[idx] = color;
		for (int next : graph[idx]) {
			if (!validateColor(graph, colors, 1 - color, next)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 用colors数组来区分两个集合
	 * 用BFS思路：节点在当前条件下符合。放入下一个要检查的节点。
	 * @param graph
	 * @return
	 */
	public boolean isBipartiteV4(int[][] graph) {
		int n = graph.length;
		int[] colors = new int[n];
		for (int i = 0; i < n; i++) {
			if (colors[i] == 0) {//还未标记颜色
				colors[i] = 1;
				Queue<Integer> queue = new LinkedList<Integer>();
				queue.add(i);
				while(!queue.isEmpty()){
					int fromIdx = queue.poll();
					for (int next : graph[fromIdx]) {
						if(colors[next]==colors[fromIdx]) return false;
						if(colors[next] == 0){
							colors[next] = - colors[fromIdx];
							queue.add(next);
						}
					}
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[][] graph = new int[4][];
		graph[0] = new int[] { 1 };
		graph[1] = new int[] { 0, 3 };
		graph[2] = new int[] { 3 };
		graph[3] = new int[] { 1, 2 };
		boolean r = new GraphBipartite().isBipartiteV3(graph);
		System.out.println(r);
	}
}
