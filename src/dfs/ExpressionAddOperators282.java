package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * https://blog.csdn.net/flying_all/article/details/106133954
 */
public class ExpressionAddOperators282 {
    private String num;
    private int target;
    private List<String> result;
    public List<String> addOperators(String num, int target) {
        this.num = num;
        this.target  = target;
        result = new ArrayList<String>();
        dfs(0,new StringBuilder(),0,0);
        return result;
    }

    private void dfs(int start,StringBuilder expr,long value,long preOperand){
        if(start == num.length()){
            //System.out.println(expr+" "+value);
            if(value == target){
                result.add(expr.toString());
            }
            return;
        }
        for(int  i = start ;i<num.length();i++){
            String t = num.substring(start,i+1);
            if(t.charAt(0)=='0' && i>start) break;
            long n = Long.parseLong(t);
            if(n > Integer.MAX_VALUE) break;
            int len = expr.length();
            if(start == 0){
                expr.append(t);
                dfs(i+1,expr,n,n);
                expr.setLength(len);
            }else{
                expr.append("+").append(t);
                dfs(i+1,expr,value+n,n);
                expr.setLength(len);

                expr.append("-").append(t);
                dfs(i+1,expr,value-n,-n);
                expr.setLength(len);

                expr.append("*").append(t);
                dfs(i+1,expr,value-preOperand+preOperand*n,preOperand*n);
                expr.setLength(len);
            }

        }
    }
}
