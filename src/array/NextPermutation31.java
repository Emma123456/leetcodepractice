package array;

import java.util.Arrays;

public class NextPermutation31 {
	public void nextPermutation(int[] nums) {
		int i = nums.length-2;
        for(;i>=0;i--){
            if(nums[i]<nums[i+1]){
                break;
            }
        }
        if(i==-1){
            Arrays.sort(nums);
            return;
        }
        int replaceVal = Integer.MAX_VALUE;
        int replaceIdx = -1;
        for(int j=i+1;j<nums.length && nums[j]>nums[i];j++){
            if(replaceVal>nums[j]){
                replaceIdx = j;
                replaceVal = nums[j];
            } 
        }
        int tmp = nums[i];
        nums[i] = nums[replaceIdx];
        nums[replaceIdx] = tmp;
        Arrays.sort(nums,i+1,nums.length);
    }
	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3};
		
	}

}
