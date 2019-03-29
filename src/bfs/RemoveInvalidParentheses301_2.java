package bfs;

import java.util.ArrayList;
import java.util.List;

public class RemoveInvalidParentheses301_2 {
    private List<String> results = new ArrayList<>();
    /**
     * 改进算法：观察所有返回值的长度是相同的。我们需要移除的字符数是一定的。
     * 怎么确定这个数量呢？为了得到有效的字符串，我们应该移除多少个左括号，多少个右括号呢？
     * 1 我们从左开始每次处理一个括号；
     * 2 如果我们遇到了左 括号，它可能回也有可能不会导致一个错误的表达。因为可能在后面会遇到一个关闭的括号。所以我们简单地记录下左括号的痕迹：left +=1；
     * 3 如果我们遇到了右 括号，这会有两种情况。情况1：没有左括号，left=0，这个时候直接right+=1。情况2：还有一些左括号没有匹配到，这个时候left-=1。
     * 4 继续处理，直到字符串结束。
     * 5 最后left 和 right的值告诉我们没有匹配到的左右括号分别有多少个。
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        results = new ArrayList<>();
        int len = s.length();
        int left = 0,right = 0;
        for(int i=0;i<len;i++){
            if(s.charAt(i)=='('){
                left++;
            }else if(s.charAt(i)==')'){
                if(left>0){
                    left--;
                }else{
                    right++;
                }

            }
        }

        dfs(s,0,left,right,new StringBuilder());
        return results;
    }

    private void dfs(String s, int idx, int left, int right, StringBuilder val) {
        if(left<0 || right<0) return;
        if(idx==s.length()){
            String text = val.toString();
            if(left==0 && right==0 && isValidate(text)){
                if(!results.contains(text)){
                    results.add(text);
                }
            }
        }else{
            int len = val.length();
            if(s.charAt(idx)=='('){
                //去掉
                dfs(s,idx+1,left-1,right,val);
                val.append(s.charAt(idx));
                dfs(s,idx+1,left,right,val);
            }else if(s.charAt(idx)==')'){
                dfs(s,idx+1,left,right-1,val);
                val.append(s.charAt(idx));
                dfs(s,idx+1,left,right,val);
            }else{
                val.append(s.charAt(idx));
                dfs(s,idx+1,left,right,val);
            }
            val.setLength(len);
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
