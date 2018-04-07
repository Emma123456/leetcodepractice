package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MajorityElement229 {
	public List<Integer> majorityElement(int[] nums) {
		int candidate1 = -1, count1 = 0;
		int candidate2 = -2, count2 = 0;
		for(int i=0;i<nums.length;i++){
			if(nums[i] == candidate1){
				count1++;
			}else if(nums[i]==candidate2){
				count2++;
			}else if(count1==0){
				candidate1 = nums[i];
				count1 = 1;
			}else if(count2==0){
				candidate2 = nums[i];
				count2++;
			}else{
				count1--;
				count2--;
			}
		}
		count1 = 0;
		count2=0;
		for(int num : nums){
			if(num==candidate1){
				count1++;
			}else if(num==candidate2){
				count2++;
			}
		}
		List<Integer> list = new ArrayList<Integer>();
		if(count1>nums.length/3){
			list.add(candidate1);
		}
		if(count2>nums.length/3){
			list.add(candidate2);
		}
		return list;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{2,2,1,3};
		System.out.println(new MajorityElement229().majorityElement(nums));
	}

}
