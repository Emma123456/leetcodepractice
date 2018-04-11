package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum1 {
	/**
	 * 题目是希望找到数组中两个数字的和等于target。
	 * 直观上两层for循环肯定解决问题。
	 * 第二种思路就是借助map，存储能够解决问题的nums[i]希望的值
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            if(map.get(nums[i])!=null){
                return new int[]{map.get(nums[i]),i};
            }else{
                map.put(target - nums[i],i);
            }
        }
        return new int[]{-1,-1};
    }
	/**
	 * 两指针，需要在排序好的数组中操作。排序数组，数组下标会发生变化
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSumV2(int[] nums, int target) {
		return null;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
