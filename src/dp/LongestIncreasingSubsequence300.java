package dp;

/**
 * 最长子序列可以是不连续的，最长子数组必须是连续的
 */
public class LongestIncreasingSubsequence300 {
    public int lengthOfLIS(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i=1;i<n;i++){
            dp[i] = 1;
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        int max = dp[0];
        for(int i=1;i<n;i++){
            max = Math.max(max,dp[i]);
        }
        return max;
    }


    public int lengthOfLISV2(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        int n = nums.length;
        int[] tails = new int[n];
        int len = 0;
        for(int i=0;i<n;i++){
            int l = 0, r = len;
            while(l<r){
                int middle = (l+r)/2;
                if(tails[middle] < nums[i]){
                    l = middle +1;
                }else{
                    r = middle;
                }
            }

            System.out.println(nums[i]+" "+l+" "+r);
            tails[l] = nums[i];
            if(r==len) len++;
        }

        return len;
    }


    public static void main(String[] args){
        int r = new LongestIncreasingSubsequence300().lengthOfLISV2(new int[]{10,9,2,5,3,7,101,18});
        System.out.println(r);
    }
}
