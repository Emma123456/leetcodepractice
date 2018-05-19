package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * 
 * @author Administrator
 *
 */
public class AccountsMergeV2 {
	/**
	 * DFS 还是没看到问题的本质
	 * 这是一个图。图的链接技巧是将第一个email和同一个list内的其他email双向相连。连通的email就是在同一个组内。
	 * @param accounts
	 * @return
	 */
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		Map<String, String> emailToName = new HashMap<String, String>();
		Map<String, List<String>> graph = new HashMap<String, List<String>>();

		for (List<String> account : accounts) {
			String name = account.get(0);
			for (int j = 1; j < account.size(); j++) {
				String email = account.get(j);
				graph.computeIfAbsent(email, x -> new ArrayList<String>()).add(account.get(1));
				graph.computeIfAbsent(account.get(1), x -> new ArrayList<String>()).add(email);
				emailToName.put(email, name);
			}
		}

		Set<String> seen = new HashSet<String>();
		List<List<String>> ans = new ArrayList<List<String>>();
		for (String email : graph.keySet()) {
			if (!seen.contains(email)) {
				seen.add(email);
				Stack<String> stack = new Stack<String>();
				stack.push(email);
				List<String> list = new ArrayList<String>();
				while(!stack.isEmpty()){
					String node = stack.pop();
					list.add(node);
					for(String nei : graph.get(node)){
						if(!seen.contains(nei)){
							stack.push(nei);
							seen.add(nei);
						}
					}
				}
				Collections.sort(list);
				list.add(0,emailToName.get(email));
				ans.add(list);
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		List<List<String>> accounts = new ArrayList<List<String>>();
		accounts.add(new ArrayList<String>(Arrays.asList("Lily", "Lily4@m.co", "Lily5@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily", "Lily8@m.co", "Lily9@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily", "Lily15@m.co", "Lily16@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily", "Lily19@m.co", "Lily20@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily", "Lily6@m.co", "Lily7@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily", "Lily10@m.co", "Lily11@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily", "Lily5@m.co", "Lily6@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily", "Lily13@m.co", "Lily14@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily", "Lily9@m.co", "Lily10@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily", "Lily1@m.co", "Lily2@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily", "Lily3@m.co", "Lily4@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily", "Lily2@m.co", "Lily3@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily", "Lily11@m.co", "Lily12@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily", "Lily7@m.co", "Lily8@m.co")));
		// ["Lily","Lily12@m.co","Lily13@m.co"],["Lily","Lily18@m.co","Lily19@m.co"],["Lily","Lily17@m.co","Lily18@m.co"],["Lily","Lily16@m.co","Lily17@m.co"],["Lily","Lily14@m.co","Lily15@m.co"],["Lily","Lily0@m.co","Lily1@m.co"]]
		List<List<String>> r = new AccountsMergeV2().accountsMerge(accounts);
		System.out.println(r);
	}
}
