package array;

public class RemoveDuplicates80 {
	public int removeDuplicates(int[] nums) {
        int idx = 2;
        for(int i=2;i<nums.length;i++){
            if(nums[i]>nums[idx-2]){
                nums[idx++]=nums[i];
            }
        }
        return idx;
    }
	public static void main(String[] args) {
		int[] nums = new int[]{1,1,1,2,2,3};
		new RemoveDuplicates80().removeDuplicates(nums);
		
	}

}
