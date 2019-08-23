package book.dp;

public class CoinChange {
    //钱币种类
    private int[] coins = new int[]{1,3,5};

    private  int n = coins.length;
    //总金额
    private int total = 9;

    private int minCount = Integer.MAX_VALUE;
    private void f (int i,int coinCount,  int sum){
        if(sum==total){
            minCount = Math.min(minCount,coinCount);
            return;
        }
        if(sum>total){
            return;
        }
        if(i==n){
            return;
        }
        int maxCount = (total - sum)/coins[i];
        System.out.println(maxCount);
        for(int j=0;j<=maxCount;j++){
            f(i+1,coinCount+j,sum+coins[i]*j);
        }
    }

    public int findCoinCount(){
        f(0,0,0);
        return minCount;
    }


    public static void main(String[] args){
        CoinChange  c = new CoinChange();
        int r = c.findCoinCount();
        System.out.println(r);
    }
}
