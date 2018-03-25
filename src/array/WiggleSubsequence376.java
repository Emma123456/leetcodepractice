package array;

import java.util.Arrays;
import java.util.List;

public class WiggleSubsequence376 {
	/**
	 * 贪心
	 * @param nums
	 * @return
	 */
	public int wiggleMaxLength(int[] nums) {
        if(nums.length<=1){
            return nums.length;
        }
        int ans = 1;
        int diff = nums[1]-nums[0];
        if(diff!=0){
            ans++;
        }
        for(int i=2;i<nums.length;i++){
            if(nums[i]!=nums[i-1]){
                 if(diff * (nums[i]-nums[i-1])<=0){
                    ans++;
                }
                diff = nums[i]-nums[i-1];
            }
        }
        return ans;
    }
	/**
	 * 动态规划
	 * @param nums
	 * @return
	 */
	public int wiggleMaxLengthV2(int[] nums) {
		if (nums.length <= 1) {
			return nums.length;
		}
		int[] dp = new int[nums.length];
		dp[0] = 1;
		int diff = nums[1] - nums[0];
		dp[1] = (diff==0?1:2);
		for (int i = 2; i < nums.length; i++) {
			int newDiff = nums[i] - nums[i-1];
			if (diff * newDiff < 0 || (diff==0 && newDiff!=0)) {
				dp[i] = dp[i-1]+1;
			}else{
				dp[i] = dp[i-1];
			}
			if(newDiff!=0){
				diff = newDiff;
			}
		}
		return dp[nums.length - 1];
    }
	public static List<int[]> getTestData(){
		return Arrays.asList(
				new int[]{}
		,new int[]{1}
		,new int[]{1,1}
		,new int[]{1,2}
		,new int[]{1,7,4,9,2,5}
		,new int[]{3,3,3,4,5,6,2,1}
		,new int[]{1,5,3,6,4,4,4,4}
		,new int[]{1,5,4,4,4}
		,new int[]{1,5,4,4,8,6,9});
	}
	/**
	 * 0
	 * 1
	 * 1
	 * 2
	 * 6
	 * 3
	 * 5
	 * 3
	 * 6
	 * @param args
	 */
	public static void main(String[] args) {
		WiggleSubsequence376 w = new WiggleSubsequence376();
		for(int[] nums : getTestData()){
			int r = w.wiggleMaxLengthV2(nums);
			System.out.println(r);
		}
	}

}
