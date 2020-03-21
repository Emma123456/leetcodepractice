package dp;

public class IsSubsequence392 {
    private String s;
    private String t;

    /**
     * 使用暴力搜索：当每一个匹配的字符可以选择使用这个字符或者不使用这个字符
     * 超时
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        this.s = s;
        this.t = t;
        return f(0,0);
    }
    private boolean f(int i,int j){
        if(i== s.length()){
            return true;
        }
        if(j == t.length()){
            return false;
        }
        if(s.charAt(i) == t.charAt(j)){
            if(f(i+1,j+1)) return true;
        }
        return f(i,j+1);
    }


    /**
     * dp[i][j] =true表示可以到达s[i]t[j]这个状态，也就是说子串s[0,i]是t的一个子序列
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequenceV2(String s, String t) {
        if(s==null || s.length()==0) return true;
        if(t==null || t.length()==0) return false;
        boolean[][]  dp = new boolean[s.length()][t.length()];
        int m = s.length();
        int n = t.length();
        for(int j = 0;j < n;j++){
            if(s.charAt(0) == t.charAt(j)){
                dp[0][j] = true;
            }else{
                dp[0][j] = (j>0?dp[0][j-1]:false);
            }
        }
        for(int i=1;i<m;i++){
            for(int j=0;j<n;j++){
                if(s.charAt(i) == t.charAt(j)){
                    dp[i][j] = (j>0?dp[i-1][j-1]:false);
                }else{
                    dp[i][j] = j>0? dp[i][j-1] :false;
                }
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 发现思维过度考虑动态规划中的状态转换。
     * 只要在前一个字符串位置后面找到下一个字符就可以
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequenceV3(String s, String t) {
        if(s==null || s.length()==0) return true;
        int m = s.length();
        int n = t.length();
        int index = 0;
        for(int i=0;i<n;i++){
            if(t.charAt(i) == s.charAt(index)){
                index++;
                if(index == m) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isSubsequenceV4(String s, String t) {
        if(s==null || s.length()==0) return true;
        int m = s.length();
        int l = -1;
        for(int i=0;i<m;i++){
            l = t.indexOf(s.charAt(i),l+1);
            if(l==-1) return false;
        }
        return true;
    }
}
