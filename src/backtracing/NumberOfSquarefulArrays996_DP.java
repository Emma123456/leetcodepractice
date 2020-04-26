package backtracing;

import java.util.ArrayList;
import java.util.List;

public class NumberOfSquarefulArrays996_DP {
    public int numSquarefulPerms(int[] A) {
        int n = A.length;
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        for(int i =0; i<n; i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int i = 0; i<n;i++){
            for(int j=i+1;j<n;j++){
                if(isPerfectSquareElement(A[i],A[j])){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        int[][] dp = new int[1<<n][n];
        for(int i=0;i<n;i++){
            dp[1<<i][i] = 1;
        }

        for(int s =0;s<(1<<n);s++){//枚举状态
            for(int i = 0;i<n;i++){//end point
                if(dp[s][i]==0) continue;
                for(Integer j : graph.get(i)){
                    //s中已经包含节点j，忽略
                    if((s&(1<<j))>0) continue;
                    //相同元素，在同一个s状态只能有一个作为结尾位置，保留2->1->0，去掉2->0->1，1->0->2....这些格式
                    if(j > 0 && A[j-1]== A[j] && (s & (1<<(j-1)))>0) continue;
                    dp[s|(1<<j)][j] += dp[s][i];
                }
            }
        }


        int s = (1<<n)-1;
        int result = 0;
        for(int j =0;j<n;j++){
            result += dp[s][j];
        }

        return result;
    }


    private boolean isPerfectSquareElement(int  i,int  j){
        int sum = i+j;
        int a = (int)Math.sqrt(sum);
        return a*a == sum;
    }

    public static void main(String[] args) {
        int r = new NumberOfSquarefulArrays996_DP().numSquarefulPerms(new int[]{2,2,2});
        System.out.println(r);
    }
}
