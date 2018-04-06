package array;

public class MinimumSizeSubarraySum209 {
	/**
	 * 同样是滑动窗口的思想
	 * @param s
	 * @param nums
	 * @return
	 */
	public int minSubArrayLen(int s, int[] nums) {
		int min = nums.length + 1;
		int start = 0;
		int end = 0;
		int sum = 0;
		while (end < nums.length) {
			sum += nums[end];
			while (sum >= s && start<=end) {
				min = Math.min(min, end - start+1);
				sum -= nums[start++];
			}
			end++;
		}
		return min == nums.length + 1 ? 0 : min;
	}
	
	public int minSubArrayLenV2(int s, int[] nums) {
		int len = nums.length;
		int min = len + 1;
		int[] sums = new int[len+1];
		for(int i=0;i<len;i++){
			sums[i+1] = sums[i]+nums[i];
		}
		for (int i = 0; i < len + 1; i++) {
			int right = search(i + 1, len, sums[i] + s, sums);
			if (right == len + 1)
				break;
			min = Math.min(min, right - i);
		}
		return min == len + 1 ? 0 : min;
	}

	private int search(int start, int end, int target, int[] sums) {
		while (start <= end) {
			int mid = (start + end) / 2;
			if (sums[mid] >= target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{2,3,1,2,4,3};
		int r = new MinimumSizeSubarraySum209().minSubArrayLen(7, nums);
		System.out.println(r);
	}

}
