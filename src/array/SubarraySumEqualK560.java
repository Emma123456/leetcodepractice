package array;

import java.util.HashMap;
import java.util.Map;


public class SubarraySumEqualK560 {
	/**
	 *** 递归改循环；没有考虑负数;没有考虑[0,0,0,0,0] 0 这种情况;能不能更快？
	 **/
	public int subarraySum(int[] nums, int k) {
		int count = 0;
        for(int startIdx=0;startIdx<nums.length;startIdx++){
            int sum = 0;
            for(int i=startIdx;i<nums.length;i++){
                sum += nums[i];
                if(sum==k){
                    count++;
                }
            }
        }
        return count;
	}

	
	public int subarraySumV2(int[] nums, int k) {
		int sum = 0;
		int count = 0;
		Map<Integer, Integer> preSum = new HashMap<Integer, Integer>();
        preSum.put(0, 1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if(preSum.get(sum-k)!=null){
				count += preSum.get(sum-k);
			}
			preSum.put(sum, preSum.get(sum)==null?1:preSum.get(sum)+1);
		}
		return count;
	}
	public static void main(String[] args) {

	}

}
