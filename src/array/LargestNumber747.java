package array;

public class LargestNumber747 {
	public int dominantIndex(int[] nums) {
        int max = -1;
        int idx = -1;
        for(int i=0;i<nums.length;i++){
            if(max<nums[i]){
                max = nums[i];
                idx = i;
            }
        }
        max = max/2;
        for(int i=0;i<nums.length;i++){
            if(i!=idx && nums[i]>max){
                return -1;
            }
        }
        return idx;
    }
	
	/**
	 * 找到最大的数，第二大的数，比较
	 * @param nums
	 * @return
	 */
	public int dominantIndexV2(int[] nums) {
		int sec_i = -1,max_i = -1;
		for(int i=0;i<nums.length;i++){
			if(max_i==-1 || nums[i]>nums[max_i]){
				sec_i = max_i;
				max_i = i;
				
			}else if(sec_i==-1 || nums[i]>nums[sec_i]){
				sec_i = i;
			}
		}
		return sec_i!=-1 && max_i!=-1 && nums[sec_i]*2 > nums[max_i] ? -1:max_i;
	}
	public static void main(String[] args) {
		int[] nums = new int[]{0,0,0,1};
		int r = new LargestNumber747().dominantIndex(nums);
		System.out.println(r);
	}

}
