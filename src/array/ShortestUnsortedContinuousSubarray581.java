package array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/discuss/103066
 * O(nlogn)
 * 解释：nums是入参，nums_sorted是nums排序好的数组,我们假设我们排序了nums[i,...j]子数组才使得nums变为了nums_sorted。
 * 所以nums[0,...i-1]必然和nums_sorted[0,...i-1]是相同的；nums[j+1,...n-1]和nums_sorted[j+1,....n-1]是相同的；
 * 而且我们能推论出nums[i]!=nums_sorted[i]，nums[j]!=nums_sorted[j]
 * 
 * O(n) time two-pass solution
 * 根据题意我们得到：
 * 1 nums[0, i - 1] and nums[j + 1, n - 1] are both sorted.
 * 2 nums[i] != nums_sorted[i] and nums[j] != nums_sorted[j].
 * 3 nums[i - 1] <= min and max <= nums[j + 1], where min and max are the minimum and maximum values of subarray nums[i, j].
 * 我们的目标是分别从下标0和n-1开始找到两个连续的最长的排序好的子数组，假设这两个数组分别为nums[0...l]和nums[r....n-1]
 * 如果两个子数组有重叠部分( l >=r)那整个数组是已经排序好的。否则则需要排序。但是此时我们还不能说把nums[l...r]排序好了，整个数组就排序好了，因为第3个性质不能得到保证。
 * 为了保证第3条性质，我们假设min和max同时也是nums[l...r]上最小的元素。也就是说我们需要不断减小l只要 nums[l]>min；我们需要不断增加r只要nums[r]<max。
 * 最终得到nums[l+1,r-1]就是那个我们需要找到的子数组。也就是i=l+1,j=r-1
 * 
 * O(n) time one-pass solution
 * 一个升序排序好的数组具有如下性质
 * 1 nums[k] <= nums[k+1] (0<=k<n-1)
 * 2 nums[k] == max[k] for all (0<=k<=n-1)，max[k]是子数组nums[0,k]的最大值
 * 3 nums[k] == min[k] for all (0<=k<=n-1)，min[k]是子数组nums[k,n-1]的最小值
 * 如果i，j满足以下要求，则nums[i,j]就是我们要查找的子数组：
 * 1 i 是最小的下标使的 nums[i]!=min[i];min[i]是nums[i,n-1]数组中最小值
 * 2 j 是最大的下标使得 nums[j]!=max[j];max[j]是nums[0,j]数组中最大值
 * 
 * @author Administrator
 *
 */
public class ShortestUnsortedContinuousSubarray581 {
	/**
	 * 数一数需要修改多长的子串，才能让整个字符串按照升序排列
	 * 我的思路：在左边找到第一个 a[i]>a[i+1] 最小的i；从右边找到最大的 a[i-1]>a[i] 最大的i
	 * 两个i相减+1就是答案了
	 * 这个答案是错误的。举个反例. [2, 6, 4, 8, 1, 9, 15] 最小的i是1，最大的i是4也就是子数组[6, 4, 8, 1]。很明显不对。
	 * 参考别人的答案：找到最小值的位置，从右向左扫描，这是考虑到元素会重复，记为start;从左向右扫描，查找最大元素的位置，记为end；end-start+1  ； 这样根本没有办法保证end>start,结论不对
	 * 再次参考，发现思路应该是：从左向右扫描，不断更新max值，找到a[i]<max的最大i，记为end(如果a[i]<max则说明a[i]的左右至少有一个数大于a[i])；
	 * 从右向左扫描，不断更新min值，找到a[i]>min 说明至少在i的右侧至少有一个数小于a[i]。
	 * 还是那句话：怎么想到的？为什么我一开始思路就是错误的
	 * @param nums
	 * @return
	 */
	public int findUnsortedSubarray(int[] nums) {
		int end = -2,start = -1;
		int minNum = nums[nums.length -1];
		for(int i=nums.length - 2;i>=0;i--){
			minNum = Math.min(minNum, nums[i]);
			if(nums[i] > minNum) start = i;
		}
		int maxNum = nums[0];
		for(int i=1;i<nums.length;i++){
			maxNum = Math.max(maxNum, nums[i]);
			if(nums[i] < maxNum) end = i;
		}
		return end-start+1;
	}
	
	public int findUnsortedSubarrayV2(int[] nums) {
        int n = nums.length;
        int maxNum = nums[0], minNum = nums[n - 1];
        int end = -2, start = -1; // in case the array is already sorted
        
        for (int i = 1; i < n; i++) {
            maxNum = Math.max(maxNum, nums[i]);
            if (maxNum > nums[i]) end = i;
            
            minNum = Math.min(minNum, nums[n - 1 - i]);
            if (minNum < nums[n - 1 - i]) start = n - 1 - i;
        }
        return end - start + 1;
    }
	public int findUnsortedSubarrayV3(int[] nums) {
		int n = nums.length;
		int[] nums_sorted = Arrays.copyOf(nums, n);
		Arrays.sort(nums_sorted);

		int i = 0, j = n - 1;
		while (i < n && nums[i] == nums_sorted[i])
			i++;
		while(j>=0 && nums[j] == nums_sorted[j])
			j--;
		return i==0 && j==n-1? 0 :j-i+1;
	}
	public int findUnsortedSubarrayV4(int[] nums) {
		int l = 0, r = nums.length -1;
		while(l<r && nums[l] <= nums[l+1]) l++;
		if(l>=r) return 0;
		
		while(nums[r-1] <= nums[r]) r--;
		
		int max = Integer.MIN_VALUE,min = Integer.MAX_VALUE;
		for(int i=l;i<=r;i++){
			max = Math.max(max, nums[i]);
			min = Math.min(min, nums[i]);
		}
		while(l>=0 && nums[l] > min) l--;
		while(r < nums.length && nums[r] < max) r++;
		return r-l-1;
	}
	
	public int findUnsortedSubarrayV5(int[] nums) {
	    int i = 0,j = -1;
	    int max =Integer.MIN_VALUE,min = Integer.MAX_VALUE;
	    for(int l =0 , r = nums.length -1 ; l<nums.length;l++,r--){
	    	max = Math.max(max, nums[l]);
	    	if(max != nums[l]) j = l;
	    	
	    	min = Math.min(min, nums[r]);
	    	if(min !=nums[r])  i = r;
	    }
	    return j-i+1;
	}
	public static void main(String[] args) {
		int[] nums = new int[]{2, 6, 4, 8, 1, 9, 15};
		int r = new ShortestUnsortedContinuousSubarray581().findUnsortedSubarrayV5(nums);
		System.out.println(r);
	}

}
