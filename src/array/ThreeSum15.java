package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum15 {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (nums == null)
			return result;
		int len = nums.length;
		if (len < 3)
			return result;
		Arrays.sort(nums);
		int target = 0;
		int k = 3;
		int maxValue = nums[len - 1];
		if (k * nums[0] > target || k * nums[len - 1] < target)
			return result;
		int num;
		for (int i = 0; i < len; i++) {
			num = nums[i];
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			if (num + 2 * maxValue < target)
				continue;
			if (3 * num > target)
				continue;
			twoSum(nums, i + 1, len - 1, target - num, num, result);
		}
		return result;
	}

	private void twoSum(int[] nums, int low, int high, int target, int num, List<List<Integer>> solution) {
		int i = low;
		int j = high;
		int sum;
		while (i < j) {
			sum = nums[i] + nums[j];
			if (sum == target) {
				solution.add(Arrays.asList(num, nums[i], nums[j]));
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

	public static void main(String[] args) {
		int[] nums = new int[] { -1, 0, 1, 2, -1, -4 };
		List<List<Integer>> result = new ThreeSum15().threeSum(nums);
		System.out.println(result);
	}

}
