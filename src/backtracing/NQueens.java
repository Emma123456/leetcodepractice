package backtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
    private boolean[] cols;
    private boolean[] diagnol;
    private boolean[] antiDiagnol;
    private char[][] board;
    private List<List<String>> result;
    private int n;
    public List<List<String>> solveNQueens(int n) {
        cols = new boolean[n];
        diagnol = new boolean[2*n];
        antiDiagnol = new boolean[2*n];
        this.n = n;
        result = new ArrayList<List<String>>();
        board = new char[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(board[i],'.');
        }
        dfs(0);
        return result;
    }

    /**
     * 按行放置
     * @param i
     */
    private void dfs(int i){
        if(i==n){
            addAnsewer();
            return;
        }
        for(int j=0;j<n;j++){
            if(canPutQueen(i,j)){
                putQueen(i,j);
                dfs(i+1);
                clearQueen(i,j);
            }
        }

     }

    private void addAnsewer(){
        List<String> list = new ArrayList<String>();
        for(int i=0;i<n;i++){
            StringBuilder str = new StringBuilder();
            for(int j=0;j<n;j++){
                str.append(board[i][j]);
            }
            list.add(str.toString());
        }
        result.add(list);
    }

    private boolean canPutQueen(int i,int j){
        //System.out.println(i+" "+j);
        return !cols[j] && !diagnol[i-j +n -1] && !antiDiagnol[i+j];
    }

    private void putQueen(int i,int j){
        cols[j] = true;
        diagnol[i-j+n-1] = true;
        antiDiagnol[i+j]=true;
        board[i][j] = 'Q';
    }
    private void clearQueen(int i,int j){
        cols[j] = false;
        diagnol[i-j+n-1] = false;
        antiDiagnol[i+j]=false;
        board[i][j] = '.';
    }

    public static void main(String[] args) {
        List<List<String>> result  = new NQueens().solveNQueens(4);
        System.out.println(result);
    }
}
