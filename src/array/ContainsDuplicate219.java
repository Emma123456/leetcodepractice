package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContainsDuplicate219 {
	/**
	 * 解决思路是：把每个值对应的下标存起来，遇到相同的比较一下是不是最多相差k
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		boolean r = false;
		Map<Integer, List<Integer>> distinct = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < nums.length; i++) {
			if (distinct.keySet().contains(nums[i])) {
				for (int idx : distinct.get(nums[i])) {
					if (i - idx <= k) {
						return true;
					}
				}
				distinct.get(nums[i]).add(i);
			} else {
				distinct.put(nums[i], new ArrayList<Integer>());
				distinct.get(nums[i]).add(i);
			}
		}

		return r;
	}

	/**
	 * 另外一种思路是，当前判断nums[i]，那集合中只保留i-k,i-k+1,...i-1范围内的nums值，其他值去掉
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public boolean containsNearbyDuplicateV2(int[] nums, int k) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (i > k) {
				set.remove(nums[i - k - 1]);
			}
			if (set.contains(nums[i]))
				return true;
			set.add(nums[i]);
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
