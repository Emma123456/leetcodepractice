package dp;

import java.util.List;

/**
 * 时间复杂度O（n^2）
 * 空间复杂度O（n）
 * 动态规划方程：dp[j]=true,表示从0到j-1这个子串在字典中存在。
 * 如果查找到从j到i-1子串在字典中也存在，则说明从0到i-1子串在字典中是存在的，说明dp[i]=true
 * 例如 leetcode 字符串，已知dp[4]=true,也就是说leet在字典中存在。
 * 查找从4到8（code）子串在字典中存在，所以dp[8]=true。
 */
public class WordBreak139V3 {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i=1;i<=n;i++){
            for(int j = 0;j<i;j++){
                if(dp[j] && wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
