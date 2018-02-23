package array;

public class MaximumSubarray53 {
	/**
	 * 
	 * @param nums
	 * @return
	 */
	public int maxSubArray(int[] nums) {
		int answer = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			int sum = 0;
			for(int j=i;j<nums.length;j++){
				sum += nums[j];
				System.out.println(sum);
				answer = Math.max(answer, sum);
			}
			System.out.println("==========");
		}
		return answer;
	}

	/**
	 * 任何一个数+一个负数，和只能更小，所以当和为0的时候sum=0
	 * @param nums
	 * @return
	 */
	public int maxSubArrayV2(int[] nums) {
		int answer = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			answer = Math.max(answer, sum);
			if (sum < 0) {
				sum = 0;
			}
		}
		return answer;
	}
	/**
	 * 动态规划思想
	 * @param nums
	 * @return
	 */
	public int maxSubArrayV3(int[] nums) {
		//dp[i]存放以nums[i]为结束的子串的最大和
		//dp[i+1] = Math.max(dp[i]+nums[i+1],nums[i+1])
		int[] dp = new int[nums.length];
		dp[0]= nums[0];
		int answer =  dp[0];
		for(int i=1;i<nums.length;i++){
			dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
			answer = Math.max(answer, dp[i]);
		}
		return answer;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
		int r = new MaximumSubarray53().maxSubArray(nums);
		System.out.println(r);
	}
}
