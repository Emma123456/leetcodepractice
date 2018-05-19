package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 把每个email对应的account下标  用并查集保存起来
 * @author Administrator
 *
 */
public class AccountsMergeV3 {
	private int[] id;
	private int[] size;
	
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		List<List<String>> result = new ArrayList<List<String>>();
		id = new int[accounts.size()];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
		}
		size = new int[accounts.size()];
		
		Map<String, Integer> emailMap = new HashMap<String, Integer>();
		Map<Integer, Set<String>> m2 = new HashMap<Integer, Set<String>>();
		for (int i = 0; i < accounts.size(); i++) {
			if(!m2.containsKey(i)){
				m2.put(i, new HashSet<String>());
			}
			List<String> account = accounts.get(i);
			for (int j = 1; j < account.size(); j++) {
				String email = account.get(j);
				if(emailMap.get(email)!=null){
					union(emailMap.get(email),i);
				}
				emailMap.put(email, i);
				m2.get(i).add(email);
			}
		}
		Map<Integer,Set<String>> map = new HashMap<Integer,Set<String>>();
		for (int i = 0; i < id.length; i++) {
			int p = find(i);
			map.computeIfAbsent(p, x->new HashSet<String>());
			if(m2.containsKey(i) && m2.get(i).size()>0){
				map.get(p).addAll(m2.get(i));
			}
		}
		for(Integer idx : map.keySet()){
			List<String> account = new LinkedList<String>();
			account.addAll(map.get(idx));
			Collections.sort(account);
			account.add(0,accounts.get(idx).get(0));
			result.add(account);
		}
		return result;
	}
	
	public void union(int node1, int node2) {  
        int find1 = find(node1);
        int find2 = find(node2);
        if(find1 != find2) {
        	if(size[find1]<size[find2]){
        		id[find1] = id[find2];
        		size[find2] += size[find1];
        	}else{
        		id[find2] = id[find1];
        		size[find1]+= size[find2];
        	}
        }
    }
    public int find (int val) {  
        if (id[val] == val) {  
            return val;
        }
        id[val] = find(id[val]);  
        return id[val];
    }
    
	public static void main(String[] args) {
		/*List<List<String>> accounts = new ArrayList<List<String>>();
		accounts.add(new ArrayList<String>(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com")));
		accounts.add(new ArrayList<String>(Arrays.asList("John","johnsmith@mail.com","john00@mail.com")));
		accounts.add(new ArrayList<String>(Arrays.asList("Mary","mary@mail.com")));
		accounts.add(new ArrayList<String>(Arrays.asList("John","johnnybravo@mail.com")));*/
		
		List<List<String>> accounts = new ArrayList<List<String>>();
		accounts.add(new ArrayList<String>(Arrays.asList("Lily","Lily4@m.co","Lily5@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily","Lily8@m.co","Lily9@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily","Lily15@m.co","Lily16@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily","Lily19@m.co","Lily20@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily","Lily6@m.co","Lily7@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily","Lily10@m.co","Lily11@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily","Lily5@m.co","Lily6@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily","Lily13@m.co","Lily14@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily","Lily9@m.co","Lily10@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily","Lily1@m.co","Lily2@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily","Lily3@m.co","Lily4@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily","Lily2@m.co","Lily3@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily","Lily11@m.co","Lily12@m.co")));
		accounts.add(new ArrayList<String>(Arrays.asList("Lily","Lily7@m.co","Lily8@m.co")));
		List<List<String>> r = new AccountsMergeV3().accountsMerge(accounts);
		System.out.println(r);
	}
}
