package backtracing;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses22 {
    private List<String> result;
    public List<String> generateParenthesis(int n) {
        result = new ArrayList<String>();
        dfs(n,n,"");
        return result;
    }

    private void dfs(int left,int right,String current){
        if(left==0 && right==0){
            result.add(current);
            return;
        }
        if(left==right){
            dfs(left-1,right,current+"(");
        }else if(left==0){
            dfs(left,right-1,current+")");
        }else{
            dfs(left-1,right,current+"(");
            dfs(left,right-1,current+")");
        }

    }
}
