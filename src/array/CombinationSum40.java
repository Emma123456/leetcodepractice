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

	private int[] nums;

	public List<List<Integer>> combinationSum2V2(int[] candidates, int target) {
		result.clear();
		this.nums = candidates;
		Arrays.sort(nums);
		if(nums==null || nums.length==0) return result;
		dfs(target,0,new ArrayList<Integer>());
		return result;
	}

	/**
	 * 按照递归树的层级，每一层有多少种选择
	 * @param target
	 * @param start
	 * @param list
	 */
	private void dfs(int target, int start, ArrayList<Integer> list) {
		if(target == 0) {
			//注意结果需要完全拷贝
			result.add(new ArrayList<Integer>(list));
			return;
		}
		for(int i = start; i< nums.length;i++){
			//元素太大，剪枝
			if(nums[i]> target){
				break;
			}
			//在同一层，重复元素不选择第二个,剪枝,
			if(i>start && nums[i] == nums[i-1]){
				continue;
			}
			list.add(nums[i]);
			dfs(target-nums[i],i+1,list);
			list.remove(list.size() - 1);
		}
	}
}
