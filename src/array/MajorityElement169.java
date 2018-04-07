package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement169 {
	/**
	 * Moore voting algorithm
	 * @param num
	 * @return
	 */
	public int majorityElement(int[] num) {
		int major = num[0];
		int count = 1;
		for (int i = 1; i < num.length; i++) {
			if (count == 0) {
				major = num[i];
				count = 1;
			} else if (num[i] == major) {
				count++;
			} else {
				count--;
			}
		}
		return major;
	}

	/**
	 * hashmap
	 * @param nums
	 * @return
	 */
	public int majorityElementV2(int[] nums) {
		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		for (int num : nums) {
			countMap.put(num, countMap.get(num) != null ? countMap.get(num) + 1 : 1);
		}
		int n = nums.length % 2 == 0 ? nums.length / 2 : nums.length / 2 + 1;
		for (int num : nums) {
			if (countMap.get(num) >= n) {
				return num;
			}
		}
		return 0;
	}
	/**
	 * 排序
	 * @param nums
	 * @return
	 */
	public int majorityElementV3(int[] nums) {
		Arrays.sort(nums);
		return nums[nums.length/2];
	}
	
	/**
	 * bit manipulation
	 * 如果一个元素出现次数超过nums.length/2，那么在二进制位上出现1的次数也会超过nums.length/2
	 * @param nums
	 * @return
	 */
	public int majorityElementV4(int[] nums) {
		int[] bits = new int[32];//bits[0]表示最低位元素
		for(int num : nums){
			for(int i=0;i<32;i++){
				if(((num>>i) &1)==1){
					bits[i]++;
				}
			}
		}
		int result = 0;
		for (int i = 31; i >= 0; i--) {
			bits[i] = (bits[i]>nums.length/2?1:0);
			result = (result<<1)+bits[i];
		}
		return result;
	}
	public static void main(String[] args) {
		int r = new MajorityElement169().majorityElementV4(new int[]{6,5,5});
		System.out.println(r);
	}

}
