package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets90 {
	/**
	 * [1,2,2,3]
	 * []
	 * [1]
	 * [1,2]
	 * [1,2](重复) 情况： nums[1]=不选择，nums[2]=选择
	 * [1,3]
	 * [1,2,2]
	 * [1,2,3]
	 * [1,2,3](重复) 情况： nums[1]=不选择，nums[2]=选择
	 * [1,2,2,3]
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		dfs(result, list, nums, 0,false);
		return result;
	}

	private void dfs(List<List<Integer>> result, List<Integer> list, int[] nums, int idx,boolean selected) {
		if (idx == nums.length) {
			result.add(new ArrayList<Integer>(list));
			return;
		}
		if (idx > 0 && nums[idx - 1] == nums[idx] && !selected) {
			dfs(result, list, nums, idx + 1, false);
		}else{
			dfs(result, list, nums, idx + 1, false);
			list.add(nums[idx]);
			dfs(result, list, nums, idx + 1, true);
			list.remove(list.size() - 1);
		}
	}
	
	/**
	 * 使用下标记录的方式，深度优先搜素思路
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsetsWithDupV2(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		boolean[] visit = new boolean[nums.length];//记录是否选择
		dfsV2(result,visit, nums, 0);
		return result;
	}

	private void dfsV2(List<List<Integer>> result, boolean[] visit, int[] nums, int idx) {
		if (idx == nums.length) {
			List<Integer> list = new ArrayList<Integer>();
			for(int i=0;i<visit.length;i++){
				if(visit[i]){
					list.add(nums[i]);
				}
			}
			result.add(list);
			return;
		}
		if (idx > 0 && nums[idx - 1] == nums[idx] && visit[idx-1]==false) {
			dfsV2(result, visit, nums, idx+1);
		}else{
			dfsV2(result, visit, nums, idx+1);
			visit[idx] = true;
			dfsV2(result, visit, nums, idx+1);
			visit[idx] = false;
		}
	}

	public static void main(String[] args) {
		List<List<Integer>> r = new Subsets90().subsetsWithDupV2(new int[] { 1, 2, 2 });
		System.out.println(r);
	}

}
