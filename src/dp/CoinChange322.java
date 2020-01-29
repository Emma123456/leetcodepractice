package dp;

import java.util.Arrays;

public class CoinChange322 {
    private int min;
    private int[] coins;
    public int coinChange(int[] coins, int amount) {
        min = Integer.MAX_VALUE;
        this.coins = coins;
        pay(amount,0,0);
        return min==Integer.MAX_VALUE?-1:min;
    }
    private void pay(int amount,int i,int count){
        //System.out.println(amount+" "+i+" "+count);
        if(amount==0){
            min = Math.min(min,count);
            return;
        }
        if(amount<0) return;
        if(i>=coins.length) return;
        pay(amount,i+1,count);
        int max = amount/coins[i];
        for(int j=1;j<=max;j++){
            pay(amount-j*coins[i],i+1,count+j);
        }

    }


    public int coinChangeV2(int[] coins, int amount) {
        int[] dp = new int[amount+1];//dp[i]金额i需要最少的硬币个数
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for(int i=1;i<=amount;i++){
            for(int j=0;j<coins.length;j++){
                if(i>=coins[j]){
                    dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                }

            }
        }
        return dp[amount] == amount+1?-1:dp[amount];
    }
}
