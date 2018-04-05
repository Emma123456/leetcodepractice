package array;

public class SubarrayProduct713 {
	/**
	 * 10, 5, 2, 6
	 * 10:count+1
	 * 10*5<100:count+2，因为[5],[10,5]
	 * 10*5*2=100:count+2     因为：[2],[5,2]
	 * 5*2*6<100:count+3   因为[6][6,2][6,2,5]  
	 * 需要保持最大的乘积<k的窗口；每次向右移动一个数值nums[j]；如果右移后乘积>=k，就增加i，减少左边的数值，直到乘积＜k;每一步都会新增(j-i+1)个子数组
	 * @param nums
	 * @param k
	 * @return
	 */
	public int numSubarrayProductLessThanK(int[] nums, int k) {
		int count = 0;
		int product = 1;
		for (int i = 0,j=0; j < nums.length; j++) {
			product *= nums[j];
			while(i<=j && product>=k){
				product /= nums[i++];
			}
			count += (j-i+1);
		}
		return count;
	}
	
	public int numSubarrayProductLessThanKV2(int[] nums, int k) {
        int count = 0;
        int product = 1;
        for(int i=0;i<nums.length;i++){
            product = 1;
            for(int j=i;j<nums.length;j++){
                product = product * nums[j];
                if(product<k){
                    count++;
                }else{
                    break;
                }
            }
        }
        return count;
    }
	
	public static void main(String[] args) {
		int[] nums = new int[]{10, 5, 2, 6};
		int k = 100;
		int r= new SubarrayProduct713().numSubarrayProductLessThanK(nums, k);
	}
}
