package array;

public class NonDecreasingArray665 {
	public boolean checkPossibility(int[] nums) {
		int errorCount = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i + 1] < nums[i]) {
				// 检查能否修改
				if (updateAble(i + 1, nums) || updateAble(i, nums)) {
					errorCount++;
					if (errorCount > 1) {
						return false;
					}
				} else {
					return false;
				}

			}
		}
		return !(errorCount > 1);
	}

	private boolean updateAble(int idx, int[] nums) {
		int max = Integer.MAX_VALUE;
		if (idx + 1 < nums.length) {
			max = nums[idx + 1];
		}
		int min = Integer.MIN_VALUE;
		if (idx > 0) {
			min = nums[idx - 1];
		}
		return max >= min;
	}
	public boolean checkPossibilityV2(int[] nums) {
		int errorCount = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i + 1] < nums[i]) {
				errorCount++;
				if(i==0 || nums[i-1]<=nums[i+1]) {
					nums[i] = nums[i+1];
				}else{
					nums[i+1] = nums[i];
				}
			}
		}
		return errorCount<=1;
	}
	public static void main(String[] args) {

	}

}
