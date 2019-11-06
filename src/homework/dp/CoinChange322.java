package homework.dp;

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


    private int[][] memo;
    public int coinChangeV2(int[] coins, int amount) {
        min = Integer.MAX_VALUE;
        this.coins = coins;
        memo = new int[amount+1][coins.length];
        payV2(amount,0,0);
        return min==Integer.MAX_VALUE?-1:min;
    }
    private void payV2(int amount,int i,int count){
        if(amount==0){
            min = Math.min(min,count);
            return;
        }
        if(amount<0) return;
        if(i>=coins.length) return;
        if(memo[amount][i]==0 || memo[amount][i]>count){
            memo[amount][i] = count;
                payV2(amount,i+1,count);
            int max = amount/coins[i];
            for(int j=1;j<=max;j++){
                payV2(amount-j*coins[i],i+1,count+j);
            }
        }

    }


    /**
     * 将递归代码改为迭代
     * 发现只和dp[i][j-1]有关系
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeV3(int[] coins, int amount) {
        if(coins==null || coins.length==0) return amount==0?0:-1;
        //dp[i][j] = 为了凑够i元，使用从0到j种硬币最少多少个
        //最后返回dp[amount][coins.length-1]
        int[][] dp = new int[amount+1][coins.length];
        for(int[] array : dp){
            Arrays.fill(array,-1);
        }

        //初始化
        for(int i=0;i<=amount;i++){
            if(i*coins[0]<=amount){
                dp[i*coins[0]][0] = i;
            }
        }

        for(int i=1;i<coins.length;i++){
            for(int k=0;k<=amount;k++){
                int max = k/coins[i];
                for(int j=0;j<=max;j++){
                    int p = k - j*coins[i];
                    if(dp[p][i-1]!=-1 && (dp[k][i]==-1 || dp[k][i]>dp[p][i-1]+j)){
                        dp[k][i] = dp[p][i-1]+j;
                    }
                }
            }

        }
        return dp[amount][coins.length-1];
    }


    /**
     * 这里将有多少种可以使用的硬币作为阶段
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeV4(int[] coins, int amount) {
        if(coins==null || coins.length==0) return amount==0?0:-1;
        //dp[i] = 为了凑够i元，使用从0到j种硬币最少多少个
        //最后返回dp[amount][coins.length-1]
        int[] dp = new int[amount+1];
        Arrays.fill(dp,-1);


        dp[0]=0;
        for(int i=0;i<coins.length;i++){
            for(int k=0;k<=amount;k++){
                int max = k/coins[i];
                for(int j=0;j<=max;j++){
                    int p = k - j*coins[i];
                    if(dp[p]!=-1 && (dp[k]==-1 || dp[k]>dp[p]+j)){
                        dp[k] = dp[p]+j;
                    }
                }
            }

        }
        return dp[amount];
    }


    /**
     * 这里将从小到大凑足amount元。
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeV5(int[] coins, int amount) {
        if(coins==null || coins.length==0) return amount==0?0:-1;
        //dp[i] = 为了凑够i元，使用从0到j种硬币最少多少个
        //最后返回dp[amount][coins.length-1]
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);


        dp[0]=0;
        for(int i=1;i<=amount;i++){
            for(int j=0;j<coins.length;j++){
                if(coins[j]<=i){
                    dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                }
            }
        }
        return dp[amount]>amount?-1:dp[amount] ;
    }

}
