package array;

public class FindMinimumRotatedSortedArray153 {
	/**
	 * 最小元素的特点有两个：1 如果是旋转元素，那么nums[min]<nums[min-1] ;2 如果不是旋转元素，那么它应该是nums[0]。
	 * 所以可以使用二分查找法：如果nums[mid]<nums[mid-1]，则就是最小元素；否则如果nums[mid]>nums[start] && nums[mid]>nums[end]，那最小元素在右侧；否则在左侧
	 * @param nums
	 * @return
	 */
	public int findMin(int[] nums) {
		int start = 0;
		int end = nums.length-1;
		while (start < end) {
			int mid = (start + end) / 2;
			if (mid > 0 && nums[mid] < nums[mid - 1]) {
				return nums[mid];
			}
			if(nums[start]<=nums[mid] && nums[end]<nums[mid]){
				start = mid+1;
			}else{
				end = mid -1;
			}
		}
		return nums[start];
	}

	public static void main(String[] args) {

	}

}
