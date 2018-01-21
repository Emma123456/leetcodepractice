package array;

public class SearchInsertPosition35 {
	/**
	 * 二分查找 当nums[middle]==target 情况简单
	 * 当nums[middle] > target，那么位置应该是<=middle;
	 * 当nums[middle] < target，那么位置应该是 > middle，所以至少加1;
	 * @param nums
	 * @param target
	 * @return
	 */
	public int searchInsert(int[] nums, int target) {
        if(nums.length==0) return 0;
        int start = 0, end = nums.length-1;
        int middle = (start+end)/2;
        while(start<=end){
        	middle = (start+end)/2;
            if(nums[middle]==target){
                break;
            }else if(nums[middle]>target){
                end = middle-1;
            }else{
                start = middle+1;
                middle = middle+1;
            }
            
        }
        return middle;
    }
	/**
	 * 更加简洁
	 * @param nums
	 * @param target
	 * @return
	 */
	public int searchInsertV2(int[] nums, int target) {
        int start = 0, end = nums.length-1;
        while(start<=end){
        	int middle = (start+end)/2;
            if(nums[middle]==target){
                break;
            }else if(nums[middle]>target){
                end = middle-1;
            }else{
                start = middle+1;
            }
            
        }
        return start;
    }
	public static void main(String[] args) {
		int[] nums = new int[]{1,3,5,6};
		int target = 7;
		int r = new SearchInsertPosition35().searchInsert(nums, target);
		System.out.println(r);
	}

}
