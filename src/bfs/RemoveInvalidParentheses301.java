package bfs;

import java.util.*;

public class RemoveInvalidParentheses301 {
    //key:去掉了几个字符; value 最后形成的结果
    private Map<Integer,List<String>> map = new HashMap<>();
    private int minDis;
    /**
     * 用回溯法/暴力搜索，挨个去掉或者留下每个字符计算文字的有效性
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        minDis = s.length();
        dfs(s,0,new StringBuilder());
        return map.get(minDis);
    }

    private void dfs(String s, int idx,StringBuilder val) {
        if(idx<s.length()){
            if(s.charAt(idx)=='('||s.charAt(idx)==')'){
                //留下第idx个元素
                dfs(s,idx+1,val.append(s.charAt(idx)));
                val.setLength(val.length()-1);
                //去掉第idx个元素
                dfs(s,idx+1,val);
            }else{
                dfs(s,idx+1,val.append(s.charAt(idx)));
                val.setLength(val.length()-1);
            }

        }else{
            //判断val是否有效
            int dis = s.length() - val.length();
            if(dis>minDis){
                return;
            }
            String text = val.toString();
            if(isValidate(text)){
                List<String> results = map.getOrDefault(dis,new ArrayList<>());
                if(!results.contains(text)){
                    results.add(text);
                    map.put(dis,results);
                    minDis = Math.min(minDis,dis);
                }
            }
        }
    }

    private boolean isValidate(String text) {
        int leftSize = 0;
        for(int i=0;i<text.length();i++){
            if(text.charAt(i)=='('){
                leftSize++;
            }else if(text.charAt(i)==')'){
                if(leftSize==0) return false;
                leftSize--;
            }
        }
        return leftSize==0;
    }
}
