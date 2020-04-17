package array;

import java.util.ArrayList;
import java.util.Arrays;
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
	private int[] candidates;
	/**
	 * path[i] = 使用nums[i]元素的次数
	 * @param candidates
	 * @param target
	 * @return
	 */
	public List<List<Integer>> combinationSumV2(int[] candidates, int target) {
		result.clear();
		path = new int[candidates.length];
		this.candidates = candidates;
		dfsV2(0, target);
		return result;
	}

	private void dfsV2(int idx, int target) {
		if (target == 0) {
			List<Integer> solution = new ArrayList<Integer>();
			for (int i = 0; i < path.length; i++) {
				for (int j = 1; j <= path[i]; j++) {
					solution.add(candidates[i]);
				}
			}
			result.add(solution);
			return;
		}
		if (idx >= candidates.length || target < 0) {
			return;
		}
		int maxCount = target / candidates[idx];
		for (int i = 0; i <= maxCount; i++) {
			path[idx] = i;
			dfsV2( idx + 1, target - i * candidates[idx]);
			path[idx] = 0;
		}
	}



	/**
	 * 按照组合模板的思路解决问题
	 * 排序是为了回溯剪枝，提高效率
	 * @param candidates
	 * @param target
	 * @return
	 */
	public List<List<Integer>> combinationSumV3(int[] candidates, int target) {
		result.clear();
		Arrays.sort(candidates);
		this.candidates = candidates;
		if(candidates==null || candidates.length==0) return result;
		dfsV3(0,target,new ArrayList<Integer>());
		return result;
	}

	/**
	 * 在当前状态下，可以选择从start到数组结尾的任意一个元素到结果集
	 *
	 * @param start
	 * @param target
	 * @param list
	 */
	private void dfsV3(int start, int target,ArrayList<Integer> list) {
		if(target == 0) {
			//注意结果需要完全拷贝
			result.add(new ArrayList<Integer>(list));
			return;
		}
		if(start>= candidates.length) return;
		for(int i = start;i<candidates.length;i++){
			if(candidates[i] > target) break;
			list.add(candidates[i]);
			dfsV3(i,target-candidates[i],list);
			list.remove(list.size() - 1);
		}

	}
	public static void main(String[] args) {
		int[] nums = new int[]{2,3,6,7};
	}

}
