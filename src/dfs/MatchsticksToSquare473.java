package dfs;

import java.util.Arrays;

public class MatchsticksToSquare473 {
	public boolean makesquare(int[] nums) {
        if(nums==null ||nums.length<4) return false;
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        int len = sum/4;
        Arrays.sort(nums);
        int[] sums = new int[4];
        return makesquare(nums,len,nums.length-1,sums);
    }
    
    private boolean makesquare(int[] nums,int width,int idx,int[] sums){
        for(int sum : sums){
            if(sum>width) return false;
        }
        if(idx<0){
            for(int sum : sums){
                if(sum!=width) return false; 
            }
            return true;
        }
        for(int i=0;i<sums.length;i++){
            sums[i]+=nums[idx];
            if(makesquare(nums,width,idx-1,sums)){
                return true;
            }
            sums[i]-=nums[idx];
        }
        return false;
    }
}
