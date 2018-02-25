package array;

import java.util.ArrayList;
import java.util.List;

public class FindAllDuplicatesInArray {
	/**
	 * 题目要求不用额外的空间，在O(n)时间内完成，这样就要非常注重一个前提1 ≤ a[i] ≤ n (n = size of array)，
	 * 数组内的元素是在长度范围内的。这样就可以将数组的下标与数组的值建立映射关系。
	 * 这里建立映射的关系是把nums[i]放在nums[i]-1 的位置上。如果nums[nums[i]-1]已经等于nums[i]了，那说明是重复的
	 * nums[i] = -1 就说明是缺失的值
	 * @param nums
	 * @return
	 */
	public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=i+1 && nums[i]!=-1){
                int val =  nums[i];
                if(nums[val-1]==val){
                    //重复
                    list.add(val);
                    nums[i] = -1;
                }else{
                    //交换
                    int tmp = nums[val-1];
                    nums[val-1] = val;
                    nums[i] = tmp;
                    i--;
                }
                
            }
        }
        return list;
    }
	
	/**
	 * 用负数
	 * @param nums
	 * @return
	 */
	public List<Integer> findDuplicatesV2(int[] nums) {
		 List<Integer> list = new ArrayList<Integer>();
	     for(int i=0;i<nums.length;i++){
	    	 int val = Math.abs(nums[i]);
	    	 if(nums[val-1]<0){
	    		 list.add(val);
	    	 }else{
	    		 nums[val-1] = - nums[val-1];
	    	 }
	     }
	    return list;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
