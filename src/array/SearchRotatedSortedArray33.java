package array;

public class SearchRotatedSortedArray33 {
	public int search(int[] nums, int target) {
		int n = nums.length;
		int start = 0;
		int end = nums.length - 1;
		while (start < end) {
			int mid = (start + end) / 2;
			if (nums[mid] > nums[end]) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		int rotatedStart = start;
		start = 0;
		end = n - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			int realMid = (mid + rotatedStart) % n;
			if (nums[realMid] == target)
				return realMid;
			if (nums[realMid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return -1;
	}

	/**
	 * https://leetcode.com/problems/search-in-rotated-sorted-array/discuss/14435/Clever-idea-making-it-simple
	 * 不太好理解
	 * @param nums
	 * @param target
	 * @return
	 */
	public int searchV2(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;
	    while (lo <= hi) {
	        int mid = lo + (hi - lo) / 2;
	        
	        int num = nums[mid];
	        // If nums[mid] and target are "on the same side" of nums[0], we just take nums[mid].
	        //if nums[0]=12,target=14 nums[mid]=1，则不在同一侧；
	        //if  nums[0]=12,target=14 nums[mid]=18，则在同一侧
	        // nums[0]<nums[mid] && nums[0]<target  这是在nums[0]的同一侧
	        // nums[0]>nums[mid] && nums[0]>target   这是在nums[0]的同一侧
	        if ((nums[mid] < nums[0]) == (target < nums[0])) {
	            num = nums[mid];
	        } else {
	            num = target < nums[0] ? Integer.MIN_VALUE : Integer.MAX_VALUE;
	        }

	        if (num < target)
	            lo = mid + 1;
	        else if (num > target)
	            hi = mid - 1;
	        else
	            return mid;
	    }
	    return -1;
	}
	
	public int searchV3(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;
	    while (lo <= hi) {
	        int mid = lo + (hi - lo) / 2;
	        if(target==nums[mid]) return mid;
 	        if(nums[mid]<nums[hi]){//从mid到hi是有序的
	        	if(target>nums[mid] && target<=nums[hi]){
	        		lo = mid+1;
	        	}else{
	        		hi = mid-1;
	        	}
	        }else{//从lo到mid是有序的
	        	if(target>=nums[lo] && target<nums[mid]){
	        		hi = mid-1;
	        	}else{
	        		lo = mid+1;
	        	}
	        }
	        
	    }
	    return -1;
	}
	public static void main(String[] args) {
			int r = new SearchRotatedSortedArray33().searchV3(new int[] { 12, 13, 14, 15, 16, 17, 18, 19, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, 111);
			System.out.println("\t"+r);
		
	}

}
