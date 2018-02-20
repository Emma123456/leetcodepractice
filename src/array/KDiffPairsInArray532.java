package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KDiffPairsInArray532 {
	/**
	 * 可能需要O(n^2)的计算了
	 * 每处理一个nums[i]，需要判断j从i+1到n的数组值，是否符合|nums[i]-nums[j]|=k。
	 * 去重部分使用set。例如nums[i]=2,k=3，则需要找到数值5,-1。当需要处理nums[i]=5的时候，因为2已经匹配过了，所以需要找到数值8。
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findPairs(int[] nums, int k) {
		if (k < 0)
			return 0;
		int count = 0;
		Set<Integer> set = new HashSet<Integer>();// 存放已经计算过的数值
		for (int i = 0; i < nums.length; i++) {
			if (!set.contains(nums[i])) {
				Set<Integer> list = new HashSet<Integer>();
				if (!set.contains(nums[i] - k)) {
					list.add(nums[i] - k);
				}
				if (!set.contains(nums[i] + k)) {
					list.add(nums[i] + k);
				}
				for (int j = i + 1; j < nums.length && !list.isEmpty(); j++) {
					if (list.contains(nums[j])) {
						count++;
						list.remove((Integer) (nums[j]));
					}
				}
				set.add(nums[i]);
			}

		}
		return count;
	}

	/**
	 * 排序数组，去重
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findPairsV2(int[] nums, int k) {
		if (k < 0)
			return 0;
		Arrays.sort(nums);
		int idx = 0;// 新的长度
		int sameMatcherCount = 0;
		for (int i = 0; i < nums.length; i++) {
			nums[idx++] = nums[i];
			if (i + 1 < nums.length && nums[i + 1] == nums[i]) {
				sameMatcherCount++;
			}
			while (i + 1 < nums.length && nums[i + 1] == nums[i]) {
				i++;
			}
		}
		if (k == 0)
			return sameMatcherCount;
		int newLength = idx;
		int count = 0;
		for (int i = 0; i < newLength; i++) {
			for (int j = i + 1; j < newLength; j++) {
				if (Math.abs(nums[i] - nums[j]) == k) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * 存放每个数字以及出现次数
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findPairsV3(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k < 0)
			return 0;

		Map<Integer, Integer> map = new HashMap<>();
		int count = 0;
		for (int num : nums) {
			map.put(num, map.get(num) != null ? map.get(num) + 1 : 1);
		}
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (k == 0) {
				if (entry.getValue() >= 2) {
					count++;
				}
			} else {
				// 只判断entry.getKey()+k，避免重复
				if (map.keySet().contains(entry.getKey() + k)) {
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {

	}

}
