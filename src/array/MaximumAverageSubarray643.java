package array;

public class MaximumAverageSubarray643 {
	public double findMaxAverage(int[] nums, int k) {
		int n = nums.length;
		Double average = null;
		for (int i = 0; i <= n - k; i++) {
			double sum = 0;
			for (int j = i; j < i + k; j++) {
				sum += nums[j];
			}
			average = (average == null ? sum / k : Math.max(average, sum / k));
		}
		return average;
	}

	/**
	 * 改进版本，去掉重复的加和计算
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public double findMaxAverageV2(int[] nums, int k) {
		int n = nums.length;
		double sum = 0;
		for (int i = 0; i < k; i++) {
			sum += nums[i];
		}
		double average = sum / k;
		for (int i = k; i < n; i++) {
			sum += nums[i] - nums[i - k];
			average = Math.max(average, sum / k);
		}
		return average;
	}

	/**
	 * 只要找到最大的sum就行
	 * 滑动窗口
	 * @param nums
	 * @param k
	 * @return
	 */
	public double findMaxAverageV3(int[] nums, int k) {
		int n = nums.length;
		int sum = 0;
		for (int i = 0; i < k; i++) {
			sum += nums[i];
		}
		int maxSum = sum;
		for (int i = k; i < n; i++) {
			sum += nums[i] - nums[i - k];
			maxSum = Math.max(maxSum, sum);
		}
		return (maxSum+0.0)/k;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] { -1 };
		int k = 1;
		double r = new MaximumAverageSubarray643().findMaxAverage(nums, k);
		System.out.println(r);
	}

}
