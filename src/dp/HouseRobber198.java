package dp;

public class HouseRobber198 {




    private int[] nums;
    private int max = 0;
    private int[][] memo;
    public int robV2(int[] nums) {
        this.nums = nums;
        max = 0;
        memo = new int[nums.length][2];
        rob(0,0,true);
        return max;
    }

    private void rob(int idx,int value,boolean robable){
        if(idx==nums.length){
            max = Math.max(max,value);
        }else{
            //System.out.println(idx+" "+robbed+" "+value);
            int idx2 = robable?1:0;
            if(value<memo[idx][idx2]){
                return;
            }
            memo[idx][idx2]=value;
            if(robable){
                rob(idx+1,value+nums[idx],false);
            }
            rob(idx+1,value,true);
        }
    }


    public int robV3(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for(int i=1;i<n;i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]);
            dp[i][1] = dp[i-1][0]+nums[i];
        }

        return Math.max(dp[n-1][0],dp[n-1][1]);
    }

    public int robV4(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        int n = nums.length;
        int v0 = 0 ,v1=nums[0];
        for(int i=1;i<n;i++){
            int tmp0 = Math.max(v0,v1);
            int tmp1 = v0+nums[i];
            v0=tmp0;
            v1=tmp1;
        }
        return Math.max(v0,v1);
    }
}
