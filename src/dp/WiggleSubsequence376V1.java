package dp;

public class WiggleSubsequence376V1 {
    private int[] nums;
    private int maxLength;
    private int[][] memo;
    private int[][] negativeMemo;
    public int wiggleMaxLength(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        if(nums.length==1) return 1;
        this.nums = nums;
        maxLength = 0;
        memo = new int[nums.length][nums.length];
        negativeMemo = new int[nums.length][nums.length];
        for(int i=0;i<nums.length-1;i++){
            wiggleMaxLength(i+1,i,0,1);
        }
        return maxLength;
    }

    private void wiggleMaxLength(int i,int lastIdx,int sign ,int count){
        System.out.println(i+" "+lastIdx+" "+sign+" "+count);
        if(i==nums.length){
            maxLength = Math.max(maxLength,count);
            return;
        }
        if(sign == 1 && count <  memo[i][lastIdx]){
            return;
        }
        if(sign == -1 && count <  negativeMemo[i][lastIdx]){
            return;
        }
        if(sign==1){
            memo[i][lastIdx] = count;
        }
        if(sign==-1){
            negativeMemo[i][lastIdx] = count;
        }
        if(sign==0 && nums[i]-nums[lastIdx]!=0){
            wiggleMaxLength(i+1,i,nums[i]-nums[lastIdx]>0?-1:1,count+1);
        }
        if(sign==1 && nums[i]-nums[lastIdx]>0){
            wiggleMaxLength(i+1,i,-1,count+1);
        }
        if(sign==-1 && nums[i]-nums[lastIdx]<0){
            wiggleMaxLength(i+1,i,1,count+1);
        }

        wiggleMaxLength(i+1,lastIdx,sign,count);
    }
}
