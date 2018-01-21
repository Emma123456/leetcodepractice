package array;

import java.util.ArrayList;
import java.util.List;

public class FindDisappearedInArray {
	public List<Integer> findDisappearedNumbers(int[] nums) {
        for(int i=0;i<nums.length;i++){
            int val = nums[i];
            if(val!=0){
            	if(nums[val-1]!=0){
            		nums[i] = nums[val-1];
            		i--;
            	}
                nums[val-1] = 0;
            }
        }
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<nums.length;i++){
            if(nums[i] !=0 ){
                list.add(i+1);
            }
        }
        return list;
    }
	
	/**
	 * 更简洁，更快速
	 * @param nums
	 * @return
	 */
	public List<Integer> findDisappearedNumbersV2(int[] nums) {
		int n = nums.length;
		for(int i=0;i<n;i++){
			nums[(nums[i]-1)%n] +=n;
		}
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<n;i++){
			if(nums[i] <= n){
				list.add(i+1);
			}
		}
		return list;
	}
	public static void main(String[] args) {
		int[] nums = new int[]{4,3,2,7,8,2,3,1};
		List<Integer> r = new FindDisappearedInArray().findDisappearedNumbersV2(nums);
		System.out.println(r);
	}

}
