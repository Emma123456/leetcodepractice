package array;

public class MoveZeroes283 {
    /**
     * 用非0数字替换0，技巧题目
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int idx = 0;
        int n = nums.length;
        for(int i=0;i<n;i++){
            if(nums[i]!=0){
                nums[idx++] = nums[i];
            }
        }
        while(idx<n){
            nums[idx++] = 0;
        }
    }
}

