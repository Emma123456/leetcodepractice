package homework.array;

public class FirstMissingPositive41 {
    /**
     * 因为我们要找的是没有出现的最小正整数，所以我们可以忽略 <1的数。
     * 在长度为n的数组中，能够出现的最小正整数是1，最大正整数是n，所以>n的也可以忽略。
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        boolean findOne = false;
        for(int num : nums){
            if(num==1){
                findOne = true;
                break;
            }
        }
        if(!findOne) return 1;

        for(int i=0;i<nums.length;i++){
            if(nums[i]<1){
                nums[i] = 1;
            }else if(nums[i]>nums.length){
                nums[i] = 1;
            }
        }

        for(int i=0;i<nums.length;i++){
            int num = Math.abs(nums[i]);
            if(nums[num-1]>0){
                nums[num-1] = -nums[num-1];
            }
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                return i+1;
            }
        }
        return nums.length+1;
    }
}
