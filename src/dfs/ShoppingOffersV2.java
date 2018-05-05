package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingOffersV2 {
	private List<Integer> price;
	private List<List<Integer>> special;

	/**
	 * 动态规划怎么做？动态规划几个元素：初始条件，递归方程。
	 * 找到递归方程，首先要找到变化的量,或者是可能重复的状态。这里就是needs，还需要购买的量。 把DFS的结果缓存起来变动态规划
	 * 这种方法更慢，我认为是多了dot这一步造成的。还未验证。
	 * @param price
	 * @param special
	 * @param needs
	 * @return
	 */
	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
		this.price = price;
		this.special = special;
		Map<List<Integer>, Integer> map = new HashMap<List<Integer>, Integer>();
		return dp(needs,map);
	}

	private int dp(List<Integer> needs,Map<List<Integer>, Integer> map) {
		if(map.containsKey(needs)){
			return map.get(needs);
		}
		int res = dot(price, needs);
		int itemSize = price.size();
		for (List<Integer> offer : special) {
			List<Integer> list = new ArrayList<Integer>(needs);
			int i = 0;
			for (; i < itemSize; i++) {
				if (offer.get(i) > needs.get(i)) {
					break;
				} else {
					list.set(i, needs.get(i) - offer.get(i));
				}
			}
			if (i == itemSize) {
				res = Math.min(res, offer.get(i) + dp(list,map));
			}
		}
		map.put(needs, res);
		return res;
	}

	private int dot(List<Integer> a, List<Integer> b) {
		int sum = 0;
		for (int i = 0; i < a.size(); i++) {
			sum += a.get(i) * b.get(i);
		}
		return sum;
	}

	public static void main(String[] args) {

	}
}
