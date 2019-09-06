package homework.stack;

import java.util.*;

public class LongestValidParentheses32 {
    /**
     * ")()())"
     * "(()"
     * 重要的是分析出条件：从上一步怎么转换到下一步   当遇到(怎么处理，遇到)怎么处理 这个想不明白，无论是回溯还是动归都写不出来。
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int max = 0;
        for(int i=1;i<s.length();i++){
            max = Math.max(max,f(s,i));
        }
        return max;
    }

    private int f(String s,int idx){
        if(idx<0) return 0;

        char ch = s.charAt(idx);
        if(ch=='(') return 0;
        int max = 0;
        if(idx-1>=0 && s.charAt(idx-1)=='('){
            max = f(s,idx-2)+2;
        }else if(idx-1>=0 && s.charAt(idx-1)==')'){
            int checkIdx = idx-f(s,idx-1)-1;
            if(checkIdx>=0 && s.charAt(checkIdx)=='('){
                max = Math.max(max,f(s,checkIdx-1)+(idx-checkIdx+1));
            }

        }
        return max;
    }


    public int longestValidParenthesesV2(String s) {
        if(s==null || s.length()==0) return 0;
        int[] dp =new int[s.length()];
        Arrays.fill(dp,-1);
        dp[0] = 0;
        for(int i=1;i<s.length();i++){
            fV2(s,i,dp);
        }
        int max = 0;
        for(int i=0;i<s.length();i++){
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    private int fV2(String s,int idx,int[] dp){
        if(idx<0) return 0;

        if(dp[idx]>-1) return dp[idx];

        char ch = s.charAt(idx);
        if(ch=='(') return 0;
        int max = 0;
        if(idx-1>=0 && s.charAt(idx-1)=='('){
            max = fV2(s,idx-2,dp)+2;
        }else if(idx-1>=0 && s.charAt(idx-1)==')'){
            int checkIdx = idx-dp[idx-1]-1;
            if(checkIdx>=0 && s.charAt(checkIdx)=='('){
                max = Math.max(max,fV2(s,checkIdx-1,dp)+(idx-checkIdx+1));
            }

        }
        dp[idx] = max;
        return max;
    }


    public int longestValidParenthesesV3(String s) {
        if(s==null || s.length()==0) return 0;
        int[] dp =new int[s.length()];
        dp[0] = 0;
        int max = 0;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)=='('){
                dp[i] = 0;
            }else {
                if(i-1>=0 && s.charAt(i-1)=='('){
                    dp[i] = (i-2>=0?dp[i-2]:0)+2;
                }else if(i-1>=0 && s.charAt(i-1)==')'){
                    int checkIdx = i-dp[i-1]-1;
                    if(checkIdx>=0 && s.charAt(checkIdx)=='('){
                        dp[i] = (checkIdx-1>=0?dp[checkIdx-1]:0)+(i-checkIdx+1);
                    }
                }
            }
            max = Math.max(max,dp[i]);
        }

        return max;
    }


    /**
     * 使用栈，记录左括号的位置，当遇到右括号的时候，右括号的位置-左括号位置+1就是长度。
     * @param s
     * @return
     */
    public int longestValidParenthesesV4(String s) {
        int  max = 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(-1);//这一步
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                stack.push(i);
            }else{
                stack.pop();//这一步，都和我的想法不同，为什么会这么想呢？
                if(!stack.isEmpty()){
                    max = Math.max(max,i-stack.peek());
                }else{
                    stack.push(i);
                }
            }
        }
        return max;
    }

    public static void main(String[] args){
        String s = ")()())";
        int r = new LongestValidParentheses32().longestValidParenthesesV2(s);
        System.out.println(r);
    }
}
