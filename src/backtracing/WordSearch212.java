package backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间复杂度O(m*n*4^l)  l是字符串平均长度
 */
public class WordSearch212 {
    private char[][] board;
    private String word;
    private int m;
    private int n;
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        m = board.length;
        if(m == 0) return new ArrayList<String>();
        n = board[0].length;
        List<String> result = new ArrayList<String>();
        for(String word : words){
            if(findWord(word)){
                result.add(word);
            }
        }
        return result;
    }

    private boolean findWord(String word) {
        this.word = word;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(dfs(i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i,int j,int index){
        if(index == word.length()) return true;
        if(i<0 || i>=m || j<0 || j>=n || board[i][j]=='#'){
            return false;
        }
        boolean r = false;
        if(board[i][j] == word.charAt(index)){
            char ch = board[i][j];
            board[i][j] = '#';
            r = dfs(i-1,j,index+1)
                    || dfs(i+1,j,index+1)
                    || dfs(i,j-1,index+1)
                    || dfs(i,j+1,index+1);
            board[i][j] = ch;
        }
        return r;
    }
}
