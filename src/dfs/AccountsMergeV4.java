package dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountsMergeV4 {
	/**
	 * union-find 还是依照DFS图的思路，union操作把email和第一个email作为同一组
	 * @param accounts
	 * @return
	 */
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		int[] id = new int[accounts.size()];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
		}
		Map<String, Integer> emailToId = new HashMap<String, Integer>();
		Map<String, String> emailToName = new HashMap<String, String>();
		int idx = 0;
		for (int i = 0; i < accounts.size(); i++) {
			List<String> account = accounts.get(i);
			String name = account.get(0);
			for (int j = 1; j < account.size(); j++) {
				String email = account.get(j);
				emailToName.put(email, name);
				if(!emailToId.containsKey(email)){
					emailToId.put(email, idx++);
				}
				union(emailToId.get(account.get(1)),emailToId.get(email),id);
			}
		}
		Map<Integer,List<String>> ans = new HashMap<Integer,List<String>>();
		for(String email : emailToName.keySet()){
			int index = find(emailToId.get(email),id);
			ans.computeIfAbsent(index, x->new ArrayList<String>()).add(email);
		}
		for(List<String> componet : ans.values()){
			Collections.sort(componet);
			componet.add(0,emailToName.get(componet.get(0)));
		}
		return new ArrayList(ans.values());
	}
	
	public void union(int node1, int node2,int[] id) {  
        int find1 = find(node1,id);
        int find2 = find(node2,id);
        if(find1 != find2) {
        	if(find1<find2){
        		id[find2] = find1;
        	}else{
        		id[find1] = find2;
        	}
        }
    }
    public int find (int val,int[] id) {  
        if (id[val] == val) {  
            return val;
        }
        id[val] = find(id[val],id);  
        return id[val];
    }
}
