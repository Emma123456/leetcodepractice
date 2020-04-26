package backtracing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class RemoveInvalidParenthesis301 {
    private Set<String> result;
    private int minimumRemoved;
    public List<String> removeInvalidParentheses(String s) {
        result = new HashSet<String>();
        minimumRemoved = s.length();
        dfs(s,0,0,0,new StringBuilder(),0);
        return new ArrayList<String>(result);
    }

    private void dfs(String s,int index,int leftCount,int rightCount,StringBuilder expression,int removed){
        if(removed > minimumRemoved) return;
        if(index == s.length()){
            if(leftCount == rightCount){
                if(removed < minimumRemoved){
                    minimumRemoved = removed;
                    result.clear();
                    result.add(expression.toString());
                }else if(removed == minimumRemoved){
                    result.add(expression.toString());
                }
            }
        }else{
            int length = expression.length();
            //不是括号的时候
            if(s.charAt(index)!='(' && s.charAt(index)!=')'){
                expression.append(s.charAt(index));
                dfs(s,index+1,leftCount,rightCount,expression,removed);
                expression.deleteCharAt(length);
            }else if(s.charAt(index)==')' && leftCount<=rightCount){
                dfs(s,index+1,leftCount,rightCount,expression,removed+1);
            }else{
                dfs(s,index+1,leftCount,rightCount,expression,removed+1);
                expression.append(s.charAt(index));
                if(s.charAt(index)=='('){
                    dfs(s,index+1,leftCount+1,rightCount,expression,removed);
                }else{
                    dfs(s,index+1,leftCount,rightCount+1,expression,removed);
                }
                expression.deleteCharAt(length);

            }
        }
    }
}
