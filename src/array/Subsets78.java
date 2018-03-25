package array;

import java.util.ArrayList;
import java.util.List;

public class Subsets78 {
	/**
	 * 深度优先搜索
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		backTrack(result, list, nums, 0);
		return result;
	}

	/**
	 * 每个位置可以选择：不操作，保持上一步的状态；或者可能是从start到n-1的任意一个元素；
	 * 下一步的元素的选择范文一定是当前选择元素的下一个位置到n-1
	 * @param result
	 * @param list
	 * @param nums
	 * @param start
	 */
	private void backTrack(List<List<Integer>> result, List<Integer> list, int[] nums, int start) {
		result.add(new ArrayList<Integer>(list));
		for (int i = start; i < nums.length; i++) {
			list.add(nums[i]);
			backTrack(result, list, nums, i + 1);
			list.remove(list.size() - 1);
		}
	}

	/**
	 * 二进制
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsetsV2(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int n = nums.length;
		int total = (1 << n) - 1;// 2^n-1
		for (int i = 0; i <= total; i++) {
			List<Integer> list = new ArrayList<Integer>();
			for(int j=0;j<n;j++){
				if(((i>>j) &1)==1){
					list.add(nums[j]);
				}
			}
			result.add(list);
		}
		
		
		return result;
	}
	
	/**
	 * 每个位置都有选，和 不选两种选择
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsetsV3(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		dfs(result, list, nums, 0);
		return result;
	}

	private void dfs(List<List<Integer>> result, List<Integer> list, int[] nums, int idx) {
		if(idx==nums.length){
			result.add(new ArrayList<Integer>(list));
			return;
		}
		dfs(result, list, nums, idx+1);
		list.add(nums[idx]);
		dfs(result, list, nums, idx+1);
		list.remove(list.size()-1);
	}

	public static void main(String[] args) {
		List<List<Integer>> r = new Subsets78().subsetsV2(new int[] { 1, 2, 3 });
		System.out.println(r);
	}
}
