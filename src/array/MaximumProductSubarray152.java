package array;

public class MaximumProductSubarray152 {
	/**
	 * 动态规划，这里的不同之处是min值的保存。
	 * 因为输入可能包含负数，负数*负数=正数。所以一定要存最小值。
	 * 与最大和子数组的不同之处是   负数+正数<正数		负数*正数<负数    负数*负数>负数
	 * @param nums
	 * @return
	 */
	public int maxProduct(int[] nums) {
        int n = nums.length;
        int max = nums[0];
        int min = nums[0];
        int res = nums[0];
        for(int i=1;i<n;i++){
        	int maxtmp = max;
            max = Math.max(Math.max(max*nums[i],min*nums[i]),nums[i]);
            min = Math.min(Math.min(maxtmp*nums[i],min*nums[i]),nums[i]);
            res = Math.max(max,res);
        }
        return res;
    }
	public static void main(String[] args) {

	}

}
