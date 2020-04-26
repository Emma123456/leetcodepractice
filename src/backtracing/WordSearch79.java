package backtracing;

public class WordSearch79 {
    private char[][] board;
    private String word;
    private int m;
    private int n;
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        m = board.length;
        if(m == 0) return false;
        n = board[0].length;
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
