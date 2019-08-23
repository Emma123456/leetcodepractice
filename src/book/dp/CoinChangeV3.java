package book.dp;

import java.util.Arrays;

public class CoinChangeV3 {
    //钱币种类
    private int[] coins = new int[]{1,3,5};

    private  int n = coins.length;
    //总金额
    private int total = 9;

    private int f (int total,int[] memory){
        if(n==0) return -1;
        if(total == 0) return 0;
        if(memory[total] !=0) return memory[total];
        int minCount = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            if(coins[i]<=total){
                int  count = f(total-coins[i],memory);
                minCount = Math.min(count,minCount);
            }
        }
        memory[total] =  minCount==Integer.MAX_VALUE?-1:minCount+1;
        return memory[total];
    }

    /**
     * 自上而下，思考方式是先解决total，解决total需要哪些条件，再解决这些问题。
     * @return
     */
    public int findCoinCount(){
        int[] memory = new int[total+1];
        return f(total,memory);
    }

    /**
     * 自下而上，也就是先解决子问题
     * @return
     */
    public int findCoinCountDp(){
       int max = total+1;
       int[] dp = new int[total+1];
        Arrays.fill(dp,max);
       dp[0] = 0;
       for(int i=1;i<=total;i++){
           for(int j=0;i<coins.length;j++){
               if(coins[j]<=i){
                   dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
               }
           }
       }
       return dp[total]==max?-1:dp[total];
    }
    public static void main(String[] args){
        CoinChangeV3  c = new CoinChangeV3();
        int r = c.findCoinCount();
        System.out.println(r);
    }
}
