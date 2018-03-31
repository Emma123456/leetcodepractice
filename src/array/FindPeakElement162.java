package array;

public class FindPeakElement162 {
	public int findPeakElement(int[] nums) {
		if (nums.length == 0)
			return 0;
		int difference = 1;
		for (int i = 1; i < nums.length; i++) {
			if ((nums[i] - nums[i - 1]) * difference < 0) {
				return i - 1;
			} else {
				difference = nums[i] - nums[i - 1];
			}
		}
		return nums.length - 1;
	}

	public int findPeakElementV2(int[] nums) {
		int low = 0, high = nums.length ;//左闭右开
		while (low < high) {
			int mid = (low + high) / 2;
			int left = mid>0?nums[mid-1]:Integer.MIN_VALUE;
			int right = mid < nums.length-1? nums[mid+1]:Integer.MIN_VALUE;
			if(left<nums[mid] && nums[mid]>right) return mid;
			if(left<nums[mid] && nums[mid]<right) low = mid+1;else high = mid;
		}
		return low;
	}
}
