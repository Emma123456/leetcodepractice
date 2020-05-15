package dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 时间复杂度(O(3^4))
 */
public class RestoreIPAddress93 {
    private List<String> result;
    private String s;
    public List<String> restoreIpAddresses(String s) {
        result =  new ArrayList<String>();
        this.s = s;
        dfs(0,0,new LinkedList<String>());
        return result;
    }

    private void dfs(int start, int segmentIndex,LinkedList<String> path){
        if(segmentIndex==4){
            if(start == s.length()){
                String r = "";
                for(String str : path){
                    r+=str+".";
                }
                result.add(r.substring(0,r.length()-1));
            }
            return;
        }
        if(start == s.length()) return;

        int maxIndex = (s.charAt(start) =='0' ? start +1 :start+3);
        maxIndex = Math.min(maxIndex , s.length());
        for(int i = start+1;i<=maxIndex;i++){
            int value = Integer.parseInt(s.substring(start,i));
            if(value>=0 &&value<=255){
                path.add(s.substring(start,i));
                dfs(i,segmentIndex+1,path);
                path.removeLast();
            }

        }

    }
}
