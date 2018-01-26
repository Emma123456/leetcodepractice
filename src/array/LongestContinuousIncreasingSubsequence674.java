package array;

public class LongestContinuousIncreasingSubsequence674 {
	/**
	 * 速度更快的代码长什么样呢？
	 * @param nums
	 * @return
	 */
	public int findLengthOfLCIS(int[] nums) {
		if (nums.length == 0)
			return 0;
		int maxLength = 1;
		int length = 1;
		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] < nums[i + 1]) {
				length++;
			} else {
				maxLength = Math.max(maxLength, length);
				length = 1;
			}
		}
		maxLength = Math.max(maxLength, length);
		return maxLength;
	}
	/**
	 * 思路基本相同，但是代码简洁性差很多
	 * @param nums
	 * @return
	 */
	public int findLengthOfLCISV2(int[] nums) {
		int cnt = 0, maxLength = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i == 0 || nums[i - 1] < nums[i])
				maxLength = Math.max(maxLength, ++cnt);
			else
				cnt = 1;
		}
		return maxLength;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 1, 3, 5, 4, 7 };
		int r = new LongestContinuousIncreasingSubsequence674().findLengthOfLCIS(nums);
		System.out.println(r);
	}

}
