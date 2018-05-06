package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class IncreasingSubsequences491 {
	private Set<List<Integer>> result = new HashSet<List<Integer>>();

	public List<List<Integer>> findSubsequences(int[] nums) {
		result.clear();
		for (int len = 2; len <= nums.length; len++) {
			dfs(nums, 0, len, new ArrayList<Integer>());
		}
		return new ArrayList<List<Integer>>(result);
	}

	private void dfs(int[] nums, int idx, int len, List<Integer> list) {
		if (idx >= nums.length)
			return;
		if (list.size() == 0 || nums[idx] >= list.get(list.size()-1)) {
			list.add(nums[idx]);
			if (list.size() == len) {
				result.add(new ArrayList<Integer>(list));
			} else {
				dfs(nums, idx + 1, len, list);
			}
			list.remove(list.size() - 1);
		}
		dfs(nums, idx + 1, len, list);
	}

	public List<List<Integer>> findSubsequencesV2(int[] nums) {
		Set<List<Integer>> result = new HashSet<List<Integer>>();
		findSequence(nums, 0, new LinkedList<Integer>(), result);
		return new ArrayList<List<Integer>>(result);
	}

	/**
	 * 我不太习惯用这种思维解决问题；但是这种方式更高效 
	 * 从下标i开始数：下一个从i+1开始枚举；一直到最后结尾 
	 * 时间复杂度(n^2)
	 * 
	 * @param nums
	 * @param idx
	 * @param list
	 * @param result
	 */
	private void findSequence(int[] nums, int idx, Deque<Integer> list, Set<List<Integer>> result) {
		if (list.size() >= 2) {
			result.add(new ArrayList<Integer>(list));
		}
		for (int i = idx; i < nums.length; i++) {
			if (list.size() == 0 || list.getLast() <= nums[i]) {
				list.add(nums[i]);
				findSequence(nums, i + 1, list, result);
				list.removeLast();
			}
		}
	}

	public List<List<Integer>> findSubsequencesV3(int[] nums) {
		Set<List<Integer>> seqs = new HashSet<List<Integer>>();
		for (int i = 0; i < nums.length; i++) {
			List<List<Integer>> built = new ArrayList<List<Integer>>(seqs);
			ArrayList<Integer> l = new ArrayList<Integer>();
			l.add(nums[i]);
			built.add(l);
			for (List<Integer> seq : built) {
				if (seq.isEmpty() || nums[i] >= seq.get(seq.size() - 1)) {
					seq.add(nums[i]);
					seqs.add(new ArrayList<Integer>(seq));
				}
			}
		}
		List<List<Integer>> res = new ArrayList<List<Integer>>(seqs.size());
		for (List<Integer> seq : seqs)
			if (seq.size() > 1)
				res.add(seq);
		return res;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 4, 6, 7, 7 };
		List<List<Integer>> res = new IncreasingSubsequences491().findSubsequencesV3(nums);
		System.out.println(res);
	}
}
