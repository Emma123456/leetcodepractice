package book.dp;

import java.util.Arrays;

public class IncreaseArray {
    private int[] nums = new int[]{2,9,3,6,5,1,7};
    private int n = nums.length;
    private int maxLen = 0;

    /**
     *
     * @param i
     *          当前处理的元素下标
     * @param lastIdx
     *          最后一个进入到最长递增子序列的元素下标，用于比较
     */
    private void f(int i,int lastIdx,int len){
        if(i==n){
            maxLen = Math.max(maxLen,len);
            return;
        }
        if(lastIdx == -1 || nums[i]>nums[lastIdx]){
            //加入到最长递增子数组中
            f(i+1,i,len+1);
        }
        //不加入到最长递增子数组中
        f(i+1,lastIdx,len);
    }

    private int maxIncreaseArrayLength(){

        f(0,-1,0);
        return maxLen;
    }


    private int fV2(int i,int lastIdx,int[][] memo){
        if(i==n){
            return 0;
        }
        if(memo[lastIdx+1][i]>=0) return memo[lastIdx+1][i];
        int value = 1;
        if(lastIdx == -1 || nums[i]>nums[lastIdx]){
            //加入到最长递增子数组中
            value = 1+fV2(i+1,i,memo);
        }
        //不加入到最长递增子数组中
        int value1 = fV2(i+1,lastIdx,memo);
        memo[lastIdx+1][i] = Math.max(value,value1);
        return memo[lastIdx+1][i];
    }

    private int maxIncreaseArrayLengthV2(){
        int[][] memo = new int[n][n];
        for(int[] array : memo){
            Arrays.fill(array,-1);
        }
        return fV2(0,-1,memo);
    }

    private int maxIncreaseArrayLengthDp(){
        if(nums==null || nums.length == 0) return 0;
        int[] dp = new int[n];//表示以第i个元素作为结尾的最长递增子序列的最大长度
        int maxLen = 1;
        for(int i=0;i<n;i++){
            int value = 1;
            for(int j=i-1;j>=0;j--){
                if(nums[i]>nums[j]){
                    value = Math.max(value,dp[j]+1);
                }
            }
            dp[i] = value;
            maxLen = Math.max(maxLen,dp[i]);
        }
        return maxLen;
    }

    public static void main(String[] args){
        int r = new IncreaseArray().maxIncreaseArrayLengthDp();
        System.out.println(r);
    }
}
