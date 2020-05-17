package dfs;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsofaPhoneNumber17 {
    private String[] maps = new String[]{
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };
    private List<String> results;
    public List<String> letterCombinations(String digits) {
        results = new ArrayList<String>();
        if(digits ==null || digits.length()==0) return results;
        dfs(digits,0,"");
        return results;
    }

    private void dfs(String digits, int index, String current){
        if(index == digits.length()){
            results.add(current);
            return;
        }
        String match = maps[digits.charAt(index)-'0'];
        for(int i=0;i<match.length();i++){
            dfs(digits,index+1,current+match.charAt(i));
        }
    }
}
