package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FourSum18 {
	private List<List<Integer>> result;

	public List<List<Integer>> fourSum(int[] nums, int target) {
		result = new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		List<Integer> solution = new ArrayList<Integer>();
		visit(4, 0, 0, nums, target, solution);
		return result;
	}

	/**
	 * 
	 * @param count
	 *            count个数相加
	 * @param currentSum
	 *            当前和
	 * @param idx
	 *            当前从idx下标开始
	 * @param nums
	 *            数据
	 * @param target
	 *            目标和
	 * @param solution
	 *            使用的数据
	 */
	private void visit(int count, int currentSum, int idx, int[] nums, int target, List<Integer> solution) {
		if (currentSum == target && count == 0) {
			List<Integer> tmp = new ArrayList<Integer>(solution);
			result.add(tmp);
			return;
		}
		if (idx >= nums.length || count == 0) {
			return;
		}
		for (int i = idx; i <= nums.length - count; i++) {
			if (i > idx && nums[i] == nums[i - 1]) {

			} else {
				solution.add(nums[i]);
				visit(count - 1, currentSum + nums[i], i + 1, nums, target, solution);
				solution.remove(solution.size() - 1);
			}
		}
	}

	public List<List<Integer>> fourSumV2(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (nums == null)
			return result;
		int len = nums.length;
		int k = 4;
		if (len < k)
			return result;
		Arrays.sort(nums);
		int maxValue = nums[len - 1];
		if (k * nums[0] > target || k * nums[len - 1] < target)
			return result;
		int num;
		for (int i = 0; i < len; i++) {
			num = nums[i];
			// 重复
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			// num太小
			if (num + 3 * maxValue < target)
				continue;
			// num太大
			if (4 * num > target)
				continue;
			threeSum(nums, i + 1, len - 1, target - num, num, result);
		}
		return result;
	}

	public void threeSum(int[] nums, int low, int high, int target, int num1, List<List<Integer>> result) {
		if (low + 1 >= high)
			return;
		int maxValue = nums[high];
		if (3 * nums[0] > target || 3 * maxValue < target)
			return;
		int num;
		for (int i = low; i < high - 1; i++) {
			num = nums[i];
			if (i > low && nums[i] == nums[i - 1])
				continue;
			if (num + 2 * maxValue < target)
				continue;
			if (3 * num > target)
				continue;
			twoSum(nums, i + 1, high, target - num, num1, num, result);
		}
	}

	private void twoSum(int[] nums, int low, int high, int target, int num1, int num, List<List<Integer>> solution) {
		int i = low;
		int j = high;
		int sum;
		while (i < j) {
			sum = nums[i] + nums[j];
			if (sum == target) {
				solution.add(Arrays.asList(num1, num, nums[i], nums[j]));
				int x = nums[i];
				while (++i < j && nums[i] == x)
					;
				int y = nums[j];
				while (i < --j && y == nums[j])
					;

			} else if (sum > target) {
				j--;
			} else {
				i++;
			}
		}
	}

	/**
	 * 两两加和
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> fourSumV3(int[] num, int target) {
		Arrays.sort(num);
	    Map<Integer, List<int[]>> twoSumMap = new HashMap<>(); // for holding visited pair sums. All pairs with the same pair sum are grouped together
	    Set<List<Integer>> res = new HashSet<>();  // for holding the results
	    
	    for (int i = 0; i < num.length; i++) {
	    	// get rid of repeated pair sums
	        if (i > 1 && num[i] == num[i - 2]) continue;
	    	
	        for (int j = i + 1; j < num.length; j++) {
	            // get rid of repeated pair sums
	            if (j > i + 2 && num[j] == num[j - 2]) continue;

	            // for each pair sum, check if the pair sum that is needed to get the target has been visited.            	
	            if (twoSumMap.containsKey(target - (num[i] + num[j]))) {   
	                // if so, get all the pairs that contribute to this visited pair sum.
		        	List<int[]> ls = twoSumMap.get(target - (num[i] + num[j]));
		        		
		        	for (int[] pair : ls) {
		        	    // we have two pairs: one is indicated as (pair[0], pair[1]), the other is (i, j).
		        	    // we first need to check if they are overlapping with each other.
		        	    int m1 = Math.min(pair[0], i);  // m1 will always be the smallest index
		                    int m2 = Math.min(pair[1], j);  // m2 will be one of the middle two indices
		                    int m3 = Math.max(pair[0], i);  // m3 will be one of the middle two indices
		                    int m4 = Math.max(pair[1], j);  // m4 will always be the largest index
		                    
		                    if (m1 == m3 || m1 == m4 || m2 == m3 || m2 == m4) continue;  // two pairs are overlapping, so just ignore this case
		 		    
		 		    res.add(Arrays.asList(num[m1], num[Math.min(m2, m3)], num[Math.max(m2, m3)], num[m4]));  // else record the result
		        	}
	            }
	            
	            // mark that we have visited current pair and add it to the corrsponding pair sum group.
	            // here we've encoded the pair indices i and j into an integer array of length 2.
	            if(twoSumMap.get(num[i] + num[j])==null){
	            	twoSumMap.put(num[i] + num[j], new ArrayList<int[]>());
	            }
	            twoSumMap.get(num[i] + num[j]).add(new int[] {i, j});
	        }
	    }
	    
	    return new ArrayList<List<Integer>>(res);
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 0, 0, 0, 0 };
		int target = 0;
		List<List<Integer>> result = new FourSum18().fourSumV3(nums, target);
		System.out.println(result);
	}

}
