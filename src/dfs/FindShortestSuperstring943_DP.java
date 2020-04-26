package dfs;

import java.util.Arrays;

public class FindShortestSuperstring943_DP {
    /**
     * 时间复杂度O(2^n)，16ms
     * @param A
     * @return
     */
    public String shortestSuperstring(String[] A) {
        int n = A.length;
        int[][] cost = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j = 0; j<n;j++){
                cost[i][j] = minLength(A[i], A[j]);
            }
        }
        int[][] dp = new int[1<<n][n];
        int[][] parent = new int[1<<n][n];
        for(int s = 0; s < (1<<n); s++){
            Arrays.fill(dp[s],100000);
            Arrays.fill(parent[s],-1);
        }
        for(int i=0;i<n;i++){
            dp[1<<i][i] = A[i].length();
        }
        for(int s = 1; s < (1<<n); s++){
            for(int j = 0;j<n;j++){//end point
                if ((s & (1 << j)) ==0) continue;
                int pre = s - (1<<j);
                for(int i =0;i<n;i++){
                    if(dp[pre][i] + cost[i][j] < dp[s][j]){
                        dp[s][j] = dp[pre][i] + cost[i][j];
                        parent[s][j] = i;
                    }
                }

            }
        }
        int mask = (1<<n)-1;
        int minCost = dp[mask][0];
        int endIndex = 0;
        for(int j =1;j<n;j++){
            System.out.println(dp[mask][j]);
            if(dp[mask][j] < minCost){
                minCost = dp[mask][j] ;
                endIndex = j;
            }
        }
        String result = "";
        int cur = endIndex;
        int s = (1<<n)-1;
        while(s > 0){
            System.out.println(cur);
            int p = parent[s][cur];
            if(p<0){
                result = A[cur] + result;
                break;
            }else{
                result = A[cur].substring(A[cur].length()-cost[p][cur]) + result;
            }
            s &= ~(1 << cur);
            cur = p;
        }
        return result;
    }

    private int minLength(String str1, String str2){
        int m = Math.min(str1.length(),str2.length());
        for(int i = m; i>0;i--){
            if(str1.endsWith(str2.substring(0,i))){
                return  str2.length()-i;
            }
        }
        return str2.length();
    }
    public static void main(String[] args) {
        String[] A = new String[]{"catg","ctaagt","gcta","ttca","atgcatc"};
        String str = new FindShortestSuperstring943_DP().shortestSuperstring(A);
        System.out.println(str);
    }
}
