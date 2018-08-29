package dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CrackingSafeV1 {
	private Set<String> seen;
	private StringBuilder answer;

	public String crackSafe(int n, int k) {
		if (n == 1 && k == 1)
			return "0";
		StringBuilder start = new StringBuilder();
		for (int i = 0; i < n; i++) {
			start.append("0");
		}
		answer = new StringBuilder();
		seen = new HashSet<String>();
		answer.append(start);
		seen.add(start.toString());
		List<Integer> path = new ArrayList<Integer>();
		dfs(start.toString(), k, path,(int)Math.pow(k, n));
		for(Integer num : path){
			answer.append(num);
		}
		return answer.toString();
	}

	private boolean dfs(String node, int k, List<Integer> path,int total) {
		if(seen.size()==total) return true;
		for (int i = 0; i < k; i++) {
			String newNode = node.substring(1) + String.valueOf(i);
			if (!seen.contains(newNode)) {
				seen.add(newNode);
				path.add(i);
				if(dfs(newNode,k,path,total)){
					return true;
				}else{
					seen.remove(newNode);
					path.remove(path.size()-1);
				}
			}
		}
		return false;
	}
	public static void main(String[] args) {
		String str = new CrackingSafeV1().crackSafe(2, 2);
		System.err.println(str);
	}
}
