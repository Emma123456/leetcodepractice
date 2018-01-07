package stack;

import java.util.Stack;

public class Pattern132_456 {
	/**
	 * 直觉上这么做，超时
	 * 
	 * @param nums
	 * @return
	 */
	public boolean find132pattern(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] > nums[i]) {
					for (int k = j + 1; k < nums.length; k++) {
						if (nums[k] > nums[i]) {
							if (nums[k] < nums[j]) {
								return true;
							}
						}
					}
				}

			}
		}
		return false;
	}

	/**
	 * 固定j；当 i确定了之后，nums[k]就一定在 (nums[i],nums[j])之间。既然nums[j]已经确定了，那就应该找一个尽可能小的nums[i]在[0,j)之间。
	 * @param nums
	 * @return
	 */
	public boolean find132patternV2(int[] nums) {
		int min = Integer.MAX_VALUE;
		for (int j = 0; j < nums.length; j++) {
			min = Math.min(min, nums[j]);
			if(min == nums[j]){
				continue;
			}
			for(int k = nums.length - 1; k> j;k--){
				if(min < nums[k] && nums[k] < nums[j]){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * i<j<k
	 * a[i]<a[k]<a[j]
	 * 这个思路是怎么想出来的？
	 * @param nums
	 * @return
	 */
	public boolean find132patternV3(int[] nums) {
		int third = Integer.MIN_VALUE;//a[k]
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=nums.length - 1;i>=0;i--){
			//nums[i] = a[i]
			if(nums[i] < third){
				return true;
			}else{
				while(!stack.isEmpty() && nums[i] > stack.peek()){
					third = stack.pop();
				}
				stack.push(nums[i]);//a[j]
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] { -1, 3, 2, 0 };
		boolean r = new Pattern132_456().find132patternV3(nums);
		System.out.println(r);
	}

}
