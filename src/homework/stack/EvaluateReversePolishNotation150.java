package homework.stack;

import java.util.Stack;

public class EvaluateReversePolishNotation150 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        for(String token: tokens){
            if("+".equals(token)){
                stack.push(stack.pop()+ stack.pop());
            }else if("-".equals(token)){
                Integer val1 = stack.pop();
                Integer val2 = stack.pop();
                stack.push(val2-val1);
            }else if("*".equals(token)){
                stack.push(stack.pop() * stack.pop());
            }else if("/".equals(token)){
                Integer val1 = stack.pop();
                Integer val2 = stack.pop();
                stack.push(val2/val1);
            }else{
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
