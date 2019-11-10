package homework.backtracking;

public class Match {
    private String s;
    private String p;
    private int n;
    private int m;

    public boolean isMatchV2(String s, String p) {
        System.out.println(s+" "+p);
        if(p.isEmpty()) return s.isEmpty();
        boolean fisrtMatch = (!s.isEmpty() && (s.charAt(0)==p.charAt(0) || p.charAt(0)=='.'));
        if(p.length()>=2 && p.charAt(1)=='*'){
            return isMatchV2(s,p.substring(2)) || (fisrtMatch && isMatchV2(s.substring(1),p));
        }else{
            return fisrtMatch && isMatchV2(s.substring(1),p.substring(1));
        }
    }

    public boolean isMatchDP(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[s.length()][p.length()]=true;
        for(int i=s.length();i>=0;i--){
            for(int j=p.length()-1;j>=0;j--){
                boolean firstMath = ((i<s.length()) && (s.charAt(i)==p.charAt(j) || p.charAt(j)=='.'));
                if(j+1< p.length() && p.charAt(j+1)=='*'){
                    dp[i][j] = dp[i][j+2] || (firstMath && dp[i+1][j]);
                }else{
                    dp[i][j] = firstMath && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

    public boolean isMatch(String s, String p) {
        this.s = s;
        this.p=p;
        this.n = s.length();
        this.m = p.length();
        int[][] memo = new int[n+1][m+1];
        return match(0,0,memo);
    }

    /**
     i: 在s中的下标
     j：在p中的下标
     */
    private boolean match(int i,int j,int[][] memo){

        if(i==n && j==m) {
            memo[i][j]=1;
            return true;
        }
        if(i>=n || j>=m) return false;
        if(j==m){
            memo[i][j] = (i==n?1:2);
        }
        if(memo[i][j]!=0) return memo[i][j]==1;

        boolean match = (s.charAt(i)== p.charAt(j) || p.charAt(j)=='.');
        boolean result = false;
        if(j+1< m && p.charAt(j+1)=='*'){
            result = match(i,j+2,memo) || match(i+1,j,memo);
        }else{
            result = match && match(i+1,j+1,memo);
        }
        memo[i][j] = (result?1:2);
        return result;
    }

    public static void main(String[] args){
        String s = "mississippi";
        String p = "mis*is*p*.";

        s="aab";
        p = "c*a*b";
        boolean result = new Match().isMatchDP(s,p);
        System.out.println(result);
    }
}
