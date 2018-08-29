package dfs;

import java.util.HashSet;
import java.util.Set;

public class CrackingTheSafeV2 {
	private Set<String> seen;
	private StringBuilder answer;

	public String crackSafe(int n, int k) {
		if (n == 1 && k == 1)
			return "0";
		seen = new HashSet<String>();
		answer = new StringBuilder();

		StringBuilder strb = new StringBuilder();
		for (int i = 0; i < n - 1; i++) {
			strb.append("0");
		}

		String start = strb.toString();
		answer.append(start);
		dfs(start, k);
		return answer.toString();
	}

	private void dfs(String node, int k) {
		String newNode = null;
		for (int x = 0; x < k; x++) {
			String nei = node +x;
			if(!seen.contains(nei)){
				seen.add(nei);
				answer.append(x);
				newNode = nei.substring(1);
				if(!newNode.equals(node)){
					dfs(newNode,k);
				}
			}
		}
	}
	public static void main(String[] args) {
		String str = new CrackingTheSafeV2().crackSafe(2, 2);
		System.err.println(str);
	}
}
