package array;
/**
 * https://leetcode.com/problems/product-of-array-except-self/discuss/65622/Simple-Java-solution-in-O(n)-without-extra-space
 * @author Administrator
 *
 */
public class ProductArrayExceptSelf238 {
	public int[] productExceptSelf(int[] nums) {
		int[] result = new int[nums.length];
		int left = 1;
		for (int i = 1; i <= nums.length; i++) {
            result[i-1] = left;
			left *= nums[i - 1];
		}
		int right = 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			if(i<nums.length-1){
				right = right * nums[i+1];
			}
			result[i] = result[i] * right;
		}
		return result;
	}

	public static void main(String[] args) {
		
	}

}
