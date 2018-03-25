package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试数据
 * 
 * [2,3,6,7] 7 [7,3,6,2] 7 [6] 7 [1,3,2] 7
 * 
 * 每个元素不相同；每个元素可以用无限次
 * @author Administrator
 *
 */
public class CombinationSum39 {
	private List<List<Integer>> result = new ArrayList<List<Integer>>();
	private int[] path;

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		result.clear();
		dfs(candidates, 0, target, new ArrayList<Integer>());
		return result;
	}

	/**
	 * 对于nums[idx]元素可以选择0次、1次....target/nums[idx]次
	 * 
	 * @param candidates
	 * @param idx
	 * @param target
	 * @param arrayList
	 */
	private void dfs(int[] nums, int idx, int target, ArrayList<Integer> solution) {
		if (target == 0) {
			// 找到解
			ArrayList<Integer> tmp = new ArrayList<Integer>(solution);
			result.add(tmp);
			return;
		}
		if (idx >= nums.length) {
			return;
		}
		if (nums[idx] <= target) {
			solution.add(nums[idx]);
			dfs(nums, idx, target - nums[idx], solution);
			solution.remove(solution.size() - 1);
		}
		dfs(nums, idx + 1, target, solution);
	}

	public List<List<Integer>> combinationSumV2(int[] candidates, int target) {
		result.clear();
		path = new int[candidates.length];
		dfsV2(candidates, 0, target);
		return result;
	}

	private void dfsV2(int[] nums, int idx, int target) {
		if (target == 0) {
			List<Integer> solution = new ArrayList<Integer>();
			for (int i = 0; i < path.length; i++) {
				for (int j = 1; j <= path[i]; j++) {
					solution.add(nums[i]);
				}
			}
			result.add(solution);
			return;
		}
		if (idx >= nums.length || target < 0) {
			return;
		}
		int maxCount = target / nums[idx];
		for (int i = 0; i <= maxCount; i++) {
			path[idx] = i;
			dfsV2(nums, idx + 1, target - i * nums[idx]);
			path[idx] = 0;
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[]{2,3,6,7};
	}

}
