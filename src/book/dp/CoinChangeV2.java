package book.dp;

import java.util.Arrays;

public class CoinChangeV2 {
    //钱币种类
    private int[] coins = new int[]{1,3,5};

    private  int n = coins.length;
    //总金额
    private int total = 9;

    /**
     * i:决策第i个元素
     * total：需要付款total元
     * 返回使用第i个元素，最少使用多少枚硬币可以付款total元。
     * @param i
     * @param total
     */
    private int f (int i, int total){
        if(total==0) return 0;
        if(i<n && total>0){
            int maxCount =  total/coins[i];
            int minCointCount = Integer.MAX_VALUE;
            for(int j=0;j<=maxCount;j++){
                int val = f(i+1,total-coins[i]*j);
                if(val!=-1){
                    minCointCount = Math.min(minCointCount,val+j);
                }
            }
            return minCointCount==Integer.MAX_VALUE?-1:minCointCount;
        }
        return -1;
    }

    public int findCoinCount(){
        return f(0,total);
    }

    public int findCoinCountDp(){
        int[][] dp = new int[n][total+1];
        for(int[] nums : dp){
            Arrays.fill(nums,-1);
        }
        int maxCount =  total/coins[0];
        for(int j=0;j<=maxCount;j++){
            dp[0][total-coins[0]*j] = j;
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<=total;j++){
                if(dp[i-1][j]!=-1){
                    maxCount =  j/coins[i];
                    for(int k=0;k<=maxCount;k++){
                        int colJ = j-coins[i]*k;
                        int newCount = k+dp[i-1][j];
                        if(dp[i][colJ]==-1 || dp[i][colJ]>newCount){
                            dp[i][colJ]=newCount;
                        }

                    }
                }
            }
        }
        int minCointCount = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            if(dp[i][0]!=-1){
                minCointCount = Math.min(minCointCount,dp[i][0]);
            }
        }
        return minCointCount == Integer.MAX_VALUE ? -1 : minCointCount;
    }



    public static void main(String[] args){
        CoinChangeV2  c = new CoinChangeV2();
        int r = c.findCoinCountDp();
        System.out.println(r);
    }
}
