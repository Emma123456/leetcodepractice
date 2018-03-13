package array;

import java.util.Arrays;

public class ValidTriangleNumber611 {
	private int count = 0;
	private int kk = 0;

	public int triangleNumber(int[] nums) {
		boolean[] selected = new boolean[nums.length];
		count = 0;
		dfs(nums, selected, 0, 3);
		return count;
	}

	private void dfs(int[] nums, boolean[] selected, int idx, int k) {
		if (k == 0) {
			kk++;
			System.out.println(kk);
			// 计数
			int[] lengths = new int[3];
			int lenIdx = 0;
			for (int i = 0; i < selected.length; i++) {
				if (selected[i]) {
					lengths[lenIdx++] = nums[i];
				}
			}
			if (lengths[0] + lengths[1] > lengths[2] && Math.abs(lengths[0] - lengths[1]) < lengths[2] && lengths[0] + lengths[2] > lengths[1] && Math.abs(lengths[0] - lengths[2]) < lengths[1]) {
				count++;
			}
			return;
		}
		if (idx >= nums.length) {
			return;
		}
		if (nums[idx] > 0) {
			selected[idx] = true;
			dfs(nums, selected, idx + 1, k - 1);
			selected[idx] = false;
		}
		dfs(nums, selected, idx + 1, k);
	}

	/**
	 * O(n^2)  50%
	 * @param nums
	 * @return
	 */
	public int triangleNumberV2(int[] nums) {
		Arrays.sort(nums);
		int count = 0;
		for (int i = nums.length - 1; i >= 2; i--) {
			int l = 0, r = i - 1;
			while (l < r) {
				if (nums[l] + nums[r] > nums[i]) {
					count += r - l;
					r--;
				} else {
					l++;
				}
			}
		}
		return count;
	}

	public int triangleNumberV3(int[] nums) {
		count = 0;
		Arrays.sort(nums);
		dfsV3(nums, 0, 0, 0);
		return count;
	}
	
	private void dfsV3(int[] nums, int idx, int sideUsed, int sum) {
		if (sideUsed == 3 && sum-nums[idx-1] > nums[idx-1]) {
			count++;
			return;
		}
		if (idx >= nums.length) {
			return;
		}
		for (int i = idx; i < nums.length; i++) {
			dfsV3(nums, i + 1, sideUsed + 1, sum + nums[i]);
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 2, 2, 3, 4 };
		new ValidTriangleNumber611().triangleNumberV3(nums);
	}

}
