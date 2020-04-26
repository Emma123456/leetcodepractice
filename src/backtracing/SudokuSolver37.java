package backtracing;

public class SudokuSolver37 {
    private int[][] rows;
    private int[][] cols;
    private int[][] boxs;
    private char[][] board;
    private int n = 9;
    public void solveSudoku(char[][] board) {
        n = 9;
        rows = new int[n][n+1];
        cols = new int[n][n+1];
        boxs = new int[n][n+1];
        this.board = board;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]!='.'){
                    placeNumber(i,j,board[i][j]-'0');
                }
            }
        }
        dfs(0,0);
    }

    private boolean dfs(int i,int j){
        if(j==n){
            return true;
        }
        //先按行填充
        int nextI = (i+1)%n;
        int nextJ = (nextI ==0 ? j+1:j);
        if(board[i][j]=='.'){
            for(int d = 1;d<=9;d++){
                if(isValid(i,j,d)){
                    board[i][j] = (char)(d+'0');
                    placeNumber(i,j,d);
                    if(dfs(nextI,nextJ)){
                        return true;
                    }else{
                        board[i][j] = '.';
                        unPlaceNumber(i,j,d);
                    }
                }
            }
        }else{
            return dfs(nextI,nextJ);
        }
        return false;
    }

    private boolean isValid(int row,int col,int d){
        return rows[row][d]==0 && cols[col][d]==0 && boxs[row/3*3+col/3][d] ==0;
    }
    private void placeNumber(int row,int col,int d){
        rows[row][d] = 1;
        cols[col][d] = 1;
        boxs[row/3*3+col/3][d] = 1;
    }
    private void unPlaceNumber(int row,int col,int d){
        rows[row][d] = 0;
        cols[col][d] = 0;
        boxs[row/3*3+col/3][d] = 0;
    }
}
