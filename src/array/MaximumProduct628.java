package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaximumProduct628 {
	/**
	 * 找到三个最大的数相乘 因为有整数有负数， 
	 * 所以首先按照绝对值排序数组，如果前三个数字中有负数，且是2个，或者前三个都是整数 没有实现，失败
	 * 
	 * @param nums
	 * @return
	 */
	public int maximumProduct(int[] nums) {
		List<Integer> newdata = new ArrayList<Integer>(nums.length);
		for (int i = 0; i < nums.length; i++) {
			newdata.add(nums[i]);
		}
		Collections.sort(newdata, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return Math.abs(o2) - Math.abs(o1);
			}

		});
		System.out.println(newdata);
		int maxProduct = Integer.MIN_VALUE;
		for (int i = newdata.size() - 1; i >= 2; i--) {
			int product = newdata.get(i) * newdata.get(i - 1) * newdata.get(i - 2);
			maxProduct = Math.max(maxProduct, product);
		}
		return maxProduct;
	}

	/**
	 * 最大的三个正数 或者最小的两个负数*最大的1个正数 得到结果
	 * 
	 * @param nums
	 * @return
	 */
	public int maximumProductV2(int[] nums) {
		int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
		int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
		for (int num : nums) {
			if (num > max1) {
				max3 = max2;
				max2 = max1;
				max1 = num;
			}else if(num > max2){
				max3 = max2;
				max2 = num;
			}else if(num > max3){
				max3 = num;
			}
			if (num < min1) {
				min2 = min1;
				min1 = num;
			}else if(num<min2){
				min2 = num;
			}
		}
		return Math.max(max1 * max2 * max3, min1 * min2 * max1);
	}

	/**
	 * 两个具有最大绝对值的负数就是整个数组中最小的两个数
	 * @param nums
	 * @return
	 */
	public int maximumProductV3(int[] nums) {
		Arrays.sort(nums);
		int n = nums.length;
		return Math.max(nums[0]*nums[1]*nums[n-1], nums[n-1]*nums[n-2]*nums[n-3]);
	}
	public static void main(String[] args) {
		int[] nums = new int[] { 1,2,3 };
		int r = new MaximumProduct628().maximumProductV2(nums);
		System.out.println(r);
	}

}
