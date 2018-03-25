package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 可能有重复元素，每个元素只能用一次
 * 
 * @author Administrator
 *
 */
public class CombinationSum40 {
	private List<List<Integer>> result = new ArrayList<List<Integer>>();
	private boolean[] path;

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		result.clear();
		Arrays.sort(candidates);
		path = new boolean[candidates.length];
		dfs(candidates, 0, target);
		return result;
	}

	/**
	 * nums[idx]元素是否选择
	 * [1,1,2,5,6,7,10] target = 8
	 * 1,1,6
	 * 1,  7
	 *   1,7 不可以
	 * @param nums
	 * @param idx
	 * @param target
	 */
	private void dfs(int[] nums, int idx, int target) {
		if (target == 0) {
			List<Integer> r = new ArrayList<Integer>();
			for(int i=0;i<path.length;i++){
				if(path[i]){
					r.add(nums[i]);
				}
			}
			result.add(r);
			return;
		}
		if (idx >= nums.length || target < 0) {
			return;
		}
		
		if(idx>0 && nums[idx]==nums[idx-1] && path[idx-1]==false){
			//相同元素，前一个元素不选择，当前元素只能不选择
			path[idx] = false;
			dfs(nums, idx + 1, target);
		}else{
			path[idx] = true;
			dfs(nums, idx + 1, target - nums[idx]);
			path[idx] = false;
			dfs(nums, idx + 1, target);
		}
	}
}
