package dp;

import java.util.List;

public class WordBreak139V2 {
    private Boolean[] memory;
    public boolean wordBreak(String s, List<String> wordDict) {
        memory = new Boolean[s.length()+1];
        return wordBreak(s,wordDict,0);
    }
    private boolean wordBreak(String s, List<String> wordDict, int start) {
        if(start >= s.length()){
            return true;
        }
        if(memory[start]!=null){
            return memory[start];
        }
        boolean result = false;
        for(int i=start;i<=s.length();i++){
            if(wordDict.contains(s.substring(start,i))){
                if(wordBreak(s,wordDict,i)){
                    result =  true;
                    break;
                }
            }
        }
        memory[start] = result;
        return memory[start];
    }
}
