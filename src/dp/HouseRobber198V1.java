package dp;

public class HouseRobber198V1 {
    private int[] nums;
    private int max = 0;
    public int rob(int[] nums) {
        this.nums = nums;
        max = 0;
        rob(0,0,true);
        return max;
    }

    private void rob(int idx,int value,boolean robable){
        if(idx==nums.length){
            max = Math.max(max,value);
        }else{
            System.out.println(idx+" "+robable+" "+value);
            int idx2 = robable?1:0;
            if(robable){
                rob(idx+1,value+nums[idx],false);
            }
            rob(idx+1,value,true);
        }
    }
    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,1};

        new HouseRobber198V1().rob(nums);
    }
}
