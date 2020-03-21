package dp;

public class WiggleSubsequence376 {
    public int wiggleMaxLength(int[] nums){
        int value1 = calculate(nums,0,true);
        int value2 = calculate(nums,0,false);
        return 1+Math.max(value1,value2);
    }

    /**
     * 计算从index开始最长的wiggle 子串长度
     * @param nums
     * @param index
     * @param isUp
     * @return
     */
    private int calculate(int[] nums, int index, boolean isUp){
        int maxCount = 0;
        for(int i= index+1; i<nums.length;i++){
            if((isUp && nums[i] > nums[index]) || (!isUp && nums[i] < nums[index])){
                maxCount = Math.max(maxCount , 1+ calculate(nums,i,!isUp));
            }
        }
        return maxCount;
    }

    public static void main(String[] args){
        int[] nums = new int[] {1,17,5,10,13,15,10,5,16,8};
        int r = new WiggleSubsequence376().wiggleMaxLengthV2(nums);
        System.out.println(r);
    }


    public int wiggleMaxLengthV2(int[] nums){
        if(nums.length <2) return nums.length;
        int n = nums.length;
        int[] up  = new int[n];//up[i]以第i个元素为结尾的子串而且以上升趋势结束
        int[] down = new int[n];//down[i]以第i个元素为结尾的子串而且以下降趋势结束

        for(int i=1;i<n;i++){
            for(int j = 0;j<i;j++){
                if(nums[i] > nums[j]){
                    //接在一个下降趋势的子串末尾
                    up[i] = Math.max(up[i], down[j] + 1);
                }else if(nums[i] < nums[j]){
                    down[i] = Math.max(down[i] , up[j] +1);
                }
            }
        }

        return  1 + Math.max(up[n-1],down[n-1]);
    }


    public int wiggleMaxLengthV3(int[] nums){
        if(nums.length <2) return nums.length;
        int n = nums.length;
        int[] up  = new int[n];
        int[] down = new int[n];

        for(int i=1;i<n;i++){
            if(nums[i] > nums[i-1]){
                //接在一个下降趋势的子串末尾
                up[i] =  down[i-1 ] + 1;
                down[i] = down [i-1];
            }else if(nums[i] < nums[i-1]){
                down[i] =  up[i-1] +1;
                up[i] = up[i-1];
            }
        }

        return  1 + Math.max(up[n-1],down[n-1]);
    }

    public int wiggleMaxLengthV4(int[] nums){
        if(nums.length <2) return nums.length;
        int n = nums.length;
        int up  = 1;
        int down = 1;

        for(int i=1;i<n;i++){
            if(nums[i] > nums[i-1]){
                //接在一个下降趋势的子串末尾
                up =  down + 1;
            }else if(nums[i] < nums[i-1]){
                down =  up +1;
            }
        }

        return   Math.max(up,down);
    }
}
