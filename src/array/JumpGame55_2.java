package array;

public class JumpGame55_2 {
	public boolean canJumpV1(int[] nums) {
		return canJumpFromPositionV1(nums.length-1, nums);
	}
	/**
	 * 判断idx是否可以到达
	 * @param i
	 * @param nums
	 * @return
	 */
	private boolean canJumpFromPositionV1(int idx, int[] nums) {
		if(idx<=0){
			return true;
		}
		for(int i = idx-1;i>=0;i--){
			if(nums[i]+i>=idx){
				if(canJumpFromPositionV1(i,nums)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 自顶向下，从目标状态开始
	 * @param nums
	 * @return
	 */
	public boolean canJumpV2(int[] nums) {
		Boolean[] dp = new Boolean[nums.length];
		dp[0] = true;
		return canJumpFromPositionV2(nums.length-1, nums,dp);
	}
	private boolean canJumpFromPositionV2(int idx, int[] nums, Boolean[] dp) {
		if(dp[idx]!=null){
			return dp[idx];
		}
		if(idx<=0){
			return true;
		}
		for(int i = idx-1;i>=0;i--){
			if(nums[i]+i>=idx){
				if(canJumpFromPositionV1(i,nums)){
					dp[idx] = true;
					return true;
				}
			}
		}
		dp[idx] = false;
		return false;
	}
	/**
	 * 自底向上，从初始状态开始
	 * @param nums
	 * @return
	 */
	public boolean canJumpV3(int[] nums) {
		Boolean[] dp = new Boolean[nums.length];
		dp[0] = true;
		for (int idx = 1; idx < nums.length; idx++) {
			dp[idx] = false;
			for(int i = idx-1;i>=0;i--){//从idx-1到idx可行吗？从idx-2到idx可行吗？只要有其中一个可行就可以
				if(nums[i]+i>=idx && dp[i] == true){
					dp[idx] = true;
					break;
				}
			}
		}
		return dp[nums.length-1];
	}
	/**
	 * 如何从V3进化到V4呢？
	 * 如果可以从0最远跳转到5，那么下标1,2,3,4,5都是可行的（可跳转到的）。需要注意的是这句话的前提是0是可到达的。
	 * @param nums
	 * @return
	 */
	public boolean canJumpV4(int[] nums) {
		int maxIndex = 0;
		for (int idx = 0; idx < nums.length; idx++) {
			if(idx>maxIndex) return false;
			maxIndex = Math.max(maxIndex, nums[idx]+idx);
		}
		return true;
	}
	public static void main(String[] args) {
		int[] nums = new int[] { 2, 5, 0, 0 };
		//nums = new int[] { 3,2,1,0,4};
		//nums = new int[]{0};
		System.out.println(new JumpGame55_2().canJumpV3(nums));
	}

}
