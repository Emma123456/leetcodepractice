package array;

import java.util.Arrays;

public class ArrayPartition561 {
	private int total;
	/**
	 * 暴力枚举
	 * @param nums
	 * @return
	 */
    public int arrayPairSum(int[] nums) {
        total = Integer.MIN_VALUE;
        boolean[] flags = new boolean[nums.length];
        sum(nums,flags,0,0);
        return total;
    }
    private void sum(int[] nums,boolean[] flags,int idx,int s){
		if (idx >= nums.length) {
			total = Math.max(total, s);
			return;
		}
		if (flags[idx]) {
			sum(nums, flags, idx + 1, s);
			return;
		}
		flags[idx] = true;
		for (int i = idx + 1; i < nums.length; i++) {
			if (!flags[i]) {
				flags[i] = true;
				sum(nums, flags, idx + 1, s + Math.min(nums[i], nums[idx]));
				flags[i] = false;
			}
		}
		flags[idx] = false;
    }
    /**
     * 先排序数组，然后相邻的做paire
     * 要想让和最大，则需要每个数值尽可能大。观察：如果一次配对，其他数值已经确定，当 (1,4) 与 (1,2)比较的时候就会发现(1,2)pair 要比(1,4)pair 要好。
     * 因为4这个数字可能与其他数字配对，可能被保留下来。而(1,4)配对，那4一定被舍弃。所以，能够看出配对的两个数字要尽可能接近。
     * 
     * 另外一个解释的角度：如果一个数组已经排序好，则有a0,a1,a2....a(2n-1)
     * 所求和最大的应该是a0+a2+...a+(2n-2) ，这是因为(a0,a1),(a2,a3)....；最小的和是 a0+a1+a2+...+a(n-1)，这是因为(a0,an),(a1,a(n+1))这样匹配
     * @param nums
     * @return
     */
    public int arrayPairSumV2(int[] nums) {
    	Arrays.sort(nums);
    	int sum  = 0;
    	for(int i=0;i<nums.length-1;i+=2){
    		sum += nums[i];
    	}
    	return sum;
    }
    
    /**
     * 首先利用题目中给定的数据范围，使用数据值与下标的关系计数nums每个数值出现次数
     * 接着从小到大做paire
     * @param nums
     * @return
     */
    public int arrayPairSumV3(int[] nums) {
    	int[] exsit = new int[20000+1];
    	for(int i=0;i<nums.length;i++){
    		exsit[nums[i]+10000]++;
		}
		int sum = 0;
    	boolean odd = true;
    	for(int i=0;i<exsit.length;i++){
    		while(exsit[i]>0){
    			if(odd){
    				sum += i-100000;
				}
				odd = !odd;
				exsit[i]--;
			}
		}
		return sum;
    }
	public static void main(String[] args) {
		int[] nums = new int[]{1,8,3,3,5,5};
		int total = new ArrayPartition561().arrayPairSum(nums);
		System.out.println(total);
		
	}

}
