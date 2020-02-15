package array;

public class FirstMissingPositive41 {
    /**
     * 要求时间复杂度O(n)，空间复杂度是一个常数O(1)
     * 具体处理是使用正负号区分数值是不是存在
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int n  = nums.length;
        //检查1是否缺失
        int oneCount = 0;
        for(int num : nums){
            if(num==1){
                oneCount =1 ;
                break;
            }
        }

        if(oneCount == 0) return 1;


        if(n == 1) return 2;
        //替换无效数字为1
        for(int i=0; i < n; i++){
            if(nums[i] <=0  || nums[i]>n){
                nums[i] = 1;
            }
        }

        //一个布隆过滤器 nums[i] >0表示 i不存在；nums[i]<0，表示i存在。nums[0]代表n是否存在。
        for(int i=0;i <n; i++){
            int a = Math.abs(nums[i]);
            if(a == n){
                nums[0] = - Math.abs(nums[0]);
            }else{
                nums[a] = -Math.abs(nums[a]);
            }
        }
        for(int i=1;i <n; i++){
            if(nums[i] > 0){
                return i;
            }
        }
        return nums[0] >0? n: n+1;
    }

    /**
     * 也是需要一个过滤器，处理 数值在[1,n]范围内的数字，超出范围不予处理
     * nums[i] = i+1，那么i+1是存在的，否则i+1不存在。
     * 具体处理是交换数组元素位置
     * @param nums
     * @return
     */
    public int firstMissingPositiveV2(int[] nums) {
        int n = nums.length;
        for(int i=0;i <n; i++){
            while(nums[i] >0 && nums[i]<=n && nums[nums[i] -1 ]!=nums[i]){
                swap(nums,i,nums[i]-1);
            }
        }
        for(int i=0;i <n; i++){
            if(nums[i] !=  i+1){
                return i+1;
            }
        }

        return n+1;
    }

    private void swap(int[] nums, int i, int j) {
        int  tmp = nums[i] ;
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
