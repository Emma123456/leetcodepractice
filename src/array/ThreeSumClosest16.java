package array;

import java.util.Arrays;

public class ThreeSumClosest16 {
	/**
	 * 排序之后使用两指针
	 * @param nums
	 * @param target
	 * @return
	 */
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int minDis = Integer.MAX_VALUE;
		int rsum = 0;
		for (int i = 0; i < nums.length; i++) {
			int start = i+1;
			int end = nums.length - 1;
			int val = target - nums[i];
			while(start<end){
				int dis = val - nums[start]-nums[end];
				if(dis==0){
					return target;
				}
				if(minDis>Math.abs(dis)){
					minDis = Math.abs(dis);
					rsum = nums[i]+nums[start]+ nums[end];
				}
				if(dis>0){
					start++;
				}else{
					end--;
				}
			}
		}
		return rsum;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{0,1,2};
		new ThreeSumClosest16().threeSumClosest(nums, 0);
	}

}
