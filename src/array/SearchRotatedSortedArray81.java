package array;

public class SearchRotatedSortedArray81 {
	public boolean search(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (target == nums[mid])
				return true;
			if (nums[mid] > nums[lo]) {// 从lo到mid是有序的
				if (target < nums[mid] && target >= nums[lo]) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			} else if (nums[mid] < nums[lo]) {// 从mid到hi是有序的
				if (target <= nums[hi] && target > nums[mid]) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			} else {
				lo++;
			}

		}
		return false;
	}
	
	public boolean searchV2(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (target == nums[mid])
				return true;
			if (nums[mid] < nums[hi]) {// 从mid到hi是有序的
				if (target > nums[mid] && target <= nums[hi]) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			} else if (nums[mid] > nums[hi]) {// 从lo到mid是有序的
				if (target >= nums[lo] && target < nums[mid]) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			} else {
				hi--;
			}

		}
		return false;
	}

	public static void main(String[] args) {
		boolean r = new SearchRotatedSortedArray81().searchV2(new int[] { 3,1,1}, 3);
		System.out.println("\t" + r);
	}

}
