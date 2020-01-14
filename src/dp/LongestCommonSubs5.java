package dp;

public class LongestCommonSubs5 {
    public String longestPalindrome(String s) {
        if(s==null || s.length()==0) return s;
        int n = s.length();
        String r = "";
        int maxLen = 0;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                //判断i到j之间的字符串是不是回文
                //从最长的地方开始判断
                boolean result = isPalindromic(s,i,j);
                if(result && j-i+1>maxLen){
                    r =  s.substring(i,j+1);
                    maxLen = j-i+1;
                }
            }
        }
        return r;
    }

    /**
     * 这个递归公式比较难找到
     * dp[i][j]=true表示从i到j字符串是一个回文字符串
     * 如果s[i]==s[j]，并且dp[i+1][j-1]=true,那么dp[i][j]=true.其他时候为false。
     * @param s
     * @return
     */
    public String longestPalindromeV2(String s) {
        if (s == null || s.length() == 0) return s;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int maxLen = 1;
        int start = 0;
        for(int i=0;i<n;i++){
            dp[i][i] = true;
            if(i<n-1 && s.charAt(i)==s.charAt(i+1)){
                dp[i][i+1]=true;
                maxLen = 2;
                start = i;
            }
        }
        for(int i=2;i<n;i++){
            for(int j=0;j<i;j++){
                if(s.charAt(i)==s.charAt(j) && dp[j+1][i-1]){
                    dp[j][i]=true;
                    if(maxLen<i-j+1){
                        maxLen = i-j+1;
                        start = j;
                    }
                }
            }
        }
        return s.substring(start,start+maxLen);
    }
    private boolean isPalindromic(String s, int start,int end){
        while(start<=end && s.charAt(start)==s.charAt(end)){
            start++;
            end--;
        }
        return end<start;
    }
}
