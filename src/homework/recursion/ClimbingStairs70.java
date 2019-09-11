package homework.recursion;

public class ClimbingStairs70 {
    /**
     * 暴力
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if(n==0) return 0;
        if(n==1) return 1;
        if(n==2) return 2;
        return climbStairs(n-1)+climbStairs(n-2);
    }


    /**
     * 备忘录模式
     * @param n
     * @return
     */
    public int climbStairsV2(int n) {
        int[] memo = new int[n+1];
        climbStairs(n,memo);
        return memo[n];
    }

    private int climbStairs(int n,int[] memo){
        if(n==0) return 0;
        if(memo[n]>0) return memo[n];
        if(n<=2){
            memo[n]=n;
            return memo[n];
        }
        memo[n] = climbStairs(n-1,memo)+climbStairs(n-2,memo);
        return memo[n];
    }

    /**
     * 动态规划：自底向上
     * @param n
     * @return
     */
    public int climbStairsV3(int n) {
        if(n==1) return 1;
        int[] dp = new int[n+1];
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    /**
     * 节省内存的动态规划，但实际LeetCode反馈出来的内存并不少
     * @param n
     * @return
     */
    public int climbStairsV4(int n) {
        if(n==1) return 1;
        int num1 =1;
        int num2 =2;
        int num3=0;
        for(int i=3;i<=n;i++){
            num3=num1+num2;
            num1=num2;
            num2=num3;
        }
        return num2;
    }


    /**
     * 利用矩阵乘法
     * @param n
     * @return
     */
    public int climbStairsV5(int n) {
        if(n<=2) return n;
        int[][] q = {{1,1},{1,0}};
        int[][] p = {{2,1},{1,0}};
        int[][] res = pow(q,n-2);
        res = multiply(p,res);
        return res[0][0];
    }


    private int[][] pow(int[][] a,int n){
        if(n<=1){
            return a;
        }
        a = pow(a,n/2);
        a = multiply(a,a);
        if(n%2!=0){
            int[][] ret = {{1,1},{1,0}};
            a = multiply(ret,a);
        }
        return a;
    }
    private int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        c[0][0] = a[0][0]*b[0][0]+a[0][1]*b[1][0];
        c[0][1] = a[0][0]*b[0][1] + a[0][1]*b[1][1];
        c[1][0] = a[1][0] * b[0][0]+a[1][1]*b[1][0];
        c[1][1] = a[1][0]*b[0][1]+a[1][1]*b[1][1];
        return c;
    }

}
