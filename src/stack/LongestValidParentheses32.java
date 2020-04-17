package stack;

import java.util.Stack;

public class LongestValidParentheses32 {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int maxlength = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(validate(s.substring(i,j+1))){
                    maxlength = Math.max(maxlength,j+1-i);
                }
            }
        }
        return maxlength;
    }

    private boolean validate(String s ){
        Stack<Character> stack = new Stack<Character>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(!stack.isEmpty()){
                if(ch==')' && stack.peek()=='('){
                    stack.pop();
                }else{
                    stack.push(s.charAt(i));
                }
            }else{
                stack.push(s.charAt(i));
            }

        }
        return stack.isEmpty();
    }


    public int longestValidParenthesesV2(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int max = 0;
        for(int i = 1;i<n;i++){
            if(s.charAt(i)==')'){
                if(s.charAt(i-1) == '('){
                    dp[i] = (i>=2?dp[i-2]:0)+2;
                }else if(i-dp[i-1] > 0 && s.charAt(i-dp[i-1]-1)=='('){
                    dp[i] = dp[i-1] + (i-dp[i-1]-2>=0? dp[i-dp[i-1]-2]:0) + 2;
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }


    public int longestValidParenthesesV3(String s) {
        int n = s.length();
        int max = 0;
        int left=0,right = 0;
        for(int i=0;i<n;i++){
            if(s.charAt(i) =='('){
                left++;
            }else{
                right++;
            }
            if(left == right){
                max = Math.max(max, 2*left);
            }else if(right>left){
                left = right = 0;
            }
        }

        left = right = 0;

        for(int i=n-1;i>=0;i--){
            if(s.charAt(i) =='('){
                left++;
            }else{
                right++;
            }
            if(left == right){
                max = Math.max(max, 2*left);
            }else if(left>right){
                left = right = 0;
            }
        }
        return max;
    }
}
