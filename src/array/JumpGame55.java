package array;

public class JumpGame55 {
	/**
	 * 回溯 backtracing
	 * https://leetcode.com/problems/jump-game/solution/
	 * @param nums
	 * @return
	 */
	public boolean canJumpV1(int[] nums) {
		return canJumpFromPositionV1(0, nums);
	}

	private boolean canJumpFromPositionV1(int idx, int[] nums) {
		if (idx == nums.length - 1) {
			return true;
		}
		int maxPosition = Math.max(idx + nums[idx], nums.length - 1);
		for (int i = idx + 1; i <= maxPosition; i++) {
			if (canJumpFromPositionV1(i, nums)) {
				return true;
			}
		}
		return false;
	}
	enum Index {
	    GOOD, BAD, UNKNOWN
	}
	/**
	 * 动态规划 Top-down:是自顶向下是因为:从要求的状态开始出发，遇到一个没有求解的状态就去求解。
	 * 本题目的是求index=0是否是一个GOOD状态。
	 * @param nums
	 * @return
	 */
	public boolean canJumpV2(int[] nums) {
		Index[] memo = new Index[nums.length];
		for(int i=0;i<nums.length;i++){
			memo[i] = Index.UNKNOWN;
		}
		memo[nums.length - 1] =Index.GOOD;
		return canJumpFromPositionV2(0,nums,memo);
	}

	private boolean  canJumpFromPositionV2(int idx, int[] nums, Index[] memo) {
		if(memo[idx]!=Index.UNKNOWN){
			return memo[idx]==Index.GOOD?true:false;
		}
		if (idx == nums.length - 1) {
			return true;
		}
		int maxPosition = Math.max(idx + nums[idx], nums.length - 1);
		for (int i = idx + 1; i <= maxPosition; i++) {
			if (canJumpFromPositionV2(i, nums,memo)) {
				memo[idx] = Index.GOOD;
				return true;
			}
		}
		memo[idx] = Index.BAD;
		return false;
	}
	
	/**
	 * 通过消除递归实现动态规划的自底向上。自底向上：从已知条件出发，向外拓展，最后达到目标状态
	 * @param nums
	 * @return
	 */
	public boolean canJumpV3(int[] nums) {
		Index[] memo = new Index[nums.length];
		for(int i=0;i<nums.length;i++){
			memo[i] = Index.UNKNOWN;
		}
		memo[nums.length - 1] =Index.GOOD;
		
		for(int idx = nums.length-2;idx>=0;idx--){
			int maxPosition = Math.min(idx + nums[idx], nums.length - 1);
			for (int i = idx + 1; i <= maxPosition; i++) {
				if (memo[i]==Index.GOOD) {
					memo[idx] = Index.GOOD;
					break;
				}
			}
		}
		return memo[0] == Index.GOOD;
	}

	public boolean canJumpV4(int[] nums) {
		int last = nums.length - 1;
		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] + i >= last)
				last = i;
		}
		return last <= 0;
	}
	

	public boolean canJump(int[] nums) {
		int reachableIdx = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i > reachableIdx)
				return false;
			reachableIdx = Math.max(reachableIdx, i + nums[i]);
		}
		return true;
	}

	

	public static void main(String[] args) {
		int[] nums = new int[] { 2, 5, 0, 0 };
		nums = new int[] { 3,2,1,0,4};
		System.out.println(new JumpGame55().canJumpV3(nums));
	}

}
