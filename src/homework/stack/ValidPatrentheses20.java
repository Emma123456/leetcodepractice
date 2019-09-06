package homework.stack;

import java.util.Stack;

public class ValidPatrentheses20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch=='('|| ch=='[' || ch=='{'){
                stack.push(ch);
            }else if(ch==')' && !stack.isEmpty() && stack.peek()=='('){
                stack.pop();
            }else if(ch==']' && !stack.isEmpty() && stack.peek()=='['){
                stack.pop();
            }else if(ch=='}' && !stack.isEmpty() && stack.peek()=='{'){
                stack.pop();
            }else{
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 这种方法是在往栈push的时候就放入需要匹配的字符
     * @param s
     * @return
     */
    public boolean isValidV2(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch=='('){
                stack.push(')');
            }else if(ch=='{'){
                stack.push('}');
            }else if(ch=='['){
                stack.push(']');
            }else if(stack.isEmpty() || stack.pop()!=ch){
                return false;
            }

        }
        return stack.isEmpty();
    }
}
