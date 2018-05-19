package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 测试数据
 [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
[["Alex","Alex5@m.co","Alex4@m.co","Alex0@m.co"],["Ethan","Ethan3@m.co","Ethan3@m.co","Ethan0@m.co"],["Kevin","Kevin4@m.co","Kevin2@m.co","Kevin2@m.co"],["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe2@m.co"],["Gabe","Gabe3@m.co","Gabe4@m.co","Gabe2@m.co"]]

[["David","David0@m.co","David1@m.co"],["David","David3@m.co","David4@m.co"],["David","David4@m.co","David5@m.co"],["David","David2@m.co","David3@m.co"],["David","David1@m.co","David2@m.co"]]

[["Lily","Lily4@m.co","Lily5@m.co"],["Lily","Lily8@m.co","Lily9@m.co"],["Lily","Lily15@m.co","Lily16@m.co"],["Lily","Lily19@m.co","Lily20@m.co"],["Lily","Lily6@m.co","Lily7@m.co"],["Lily","Lily10@m.co","Lily11@m.co"],["Lily","Lily5@m.co","Lily6@m.co"],["Lily","Lily13@m.co","Lily14@m.co"],["Lily","Lily9@m.co","Lily10@m.co"],["Lily","Lily1@m.co","Lily2@m.co"],["Lily","Lily3@m.co","Lily4@m.co"],["Lily","Lily2@m.co","Lily3@m.co"],["Lily","Lily11@m.co","Lily12@m.co"],["Lily","Lily7@m.co","Lily8@m.co"],["Lily","Lily12@m.co","Lily13@m.co"],["Lily","Lily18@m.co","Lily19@m.co"],["Lily","Lily17@m.co","Lily18@m.co"],["Lily","Lily16@m.co","Lily17@m.co"],["Lily","Lily14@m.co","Lily15@m.co"],["Lily","Lily0@m.co","Lily1@m.co"]]

 * @author Administrator
 *
 */
public class AccountsMerge721 {
	private List<Integer> deletedIndexs = new ArrayList<Integer>();
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		List<List<String>> result = new ArrayList<List<String>>();
		Map<String, Integer> emailMap = new HashMap<String, Integer>();
		deletedIndexs.clear();
		dfs(accounts, result, emailMap, 0);
		for(int i = result.size()-1;i>=0;i--){
			if(deletedIndexs.contains(i)){
				result.remove(i);
			}
		}
		return sort(result);
	}

	private List<List<String>> sort(List<List<String>> result) {
		List<List<String>> newResult = new ArrayList<List<String>>();
		for (List<String> list : result) {
			List<String> tmp = new ArrayList<String>();
			tmp.add(list.get(0));
			list.remove(0);
			Collections.sort(list);
			tmp.addAll(list);
			newResult.add(tmp);
		}
		return newResult;
	}

	private void dfs(List<List<String>> accounts, List<List<String>> result, Map<String, Integer> emailMap, int idx) {
		if (idx >= accounts.size())
			return;
		List<String> info = accounts.get(idx);
		int mergeIndx = -1;
		for (int i = 1; i < info.size(); i++) {
			if (emailMap.containsKey(info.get(i))) {// 需要合并
				if (mergeIndx == -1 || mergeIndx > emailMap.get(info.get(i))) {
					mergeIndx = emailMap.get(info.get(i));
				}
			}
		}
		if (mergeIndx == -1) {
			List<String> list = new ArrayList<String>();
			list.add(info.get(0));
			for (int i = 1; i < info.size(); i++) {
				if (!list.contains(info.get(i))) {
					list.add(info.get(i));
				}
				emailMap.put(info.get(i), result.size());
			}
			result.add(list);
		} else {
			merge(info, result, mergeIndx, emailMap);

		}
		dfs(accounts, result, emailMap, idx + 1);
	}

	/**
	 * 重点在合并这一段
	 * 1 result中被合并的部分不能直接删除，否则emailMap中更大的value值就会出问题
	 * 2 email是不是放入result中和emailMap中的状态需要同步
	 * @param account
	 * @param result
	 * @param mergeIndex
	 * @param emailMap
	 */
	private void merge(List<String> account, List<List<String>> result, int mergeIndex, Map<String, Integer> emailMap) {
		List<String> list = result.get(mergeIndex);
		List<Integer> tmp = new ArrayList<Integer>();
		for (int i = 1; i < account.size(); i++) {
			if (!list.contains(account.get(i))) {
				if (emailMap.get(account.get(i)) == null) {
					emailMap.put(account.get(i), mergeIndex);
					list.add(account.get(i));
				} else if (emailMap.get(account.get(i)) != mergeIndex) {
					if(!tmp.contains(emailMap.get(account.get(i)))){
						tmp.add(emailMap.get(account.get(i)));
					}
				}
			}
		}
		for(Integer idx2 : tmp) {
			deletedIndexs.add(idx2);
			List<String> list1 = result.get(idx2);
			for (int j = 1; j < list1.size(); j++) {
				emailMap.remove(list1.get(j));
			}
			merge(list1, result, mergeIndex, emailMap);
		}
	}
	public static void main(String[] args) {
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
		//["Lily","Lily12@m.co","Lily13@m.co"],["Lily","Lily18@m.co","Lily19@m.co"],["Lily","Lily17@m.co","Lily18@m.co"],["Lily","Lily16@m.co","Lily17@m.co"],["Lily","Lily14@m.co","Lily15@m.co"],["Lily","Lily0@m.co","Lily1@m.co"]]
		List<List<String>> r = new AccountsMerge721().accountsMerge(accounts);
		System.out.println(r);
	}
}
