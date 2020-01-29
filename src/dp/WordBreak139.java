package dp;

import java.util.Arrays;
import java.util.List;

public class WordBreak139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreak(s,wordDict,0);
    }
    private boolean wordBreak(String s, List<String> wordDict,int start) {
        System.out.println(start);
        if(start >= s.length()){
            return true;
        }
        for(int i=start;i<=s.length();i++){
            if(wordDict.contains(s.substring(start,i))){
                if(wordBreak(s,wordDict,i)){
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args){
        String s = "aaaaaaab";
        List<String> wordDict = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa");
        new WordBreak139().wordBreak(s, wordDict);
    }
}
