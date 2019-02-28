package bfs;

/**
 * 这道题目主要考英文   Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
 * 任何不在边界的O，并且不与边界的O相邻的O能够被翻转为X
 */
public class SurroundedRegions130 {
    public void solve(char[][] board) {
        int m = board.length;
        if(m==0) return;
        int n = board[0].length;
        for(int i=0;i<m;i++){
            if(board[i][0]=='O'){
                flip(board,i,0,m,n,'O','M');
            }
            if(board[i][n-1]=='O'){
                flip(board,i,n-1,m,n,'O','M');
            }
        }
        for(int j=0;j<n;j++){
            if(board[0][j]=='O'){
                flip(board,0,j,m,n,'O','M');
            }
            if(board[m-1][j]=='O'){
                flip(board,m-1,j,m,n,'O','M');
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='O'){
                    flip(board,i,j,m,n,'O','X');
                }
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='M'){
                    flip(board,i,j,m,n,'M','O');
                }
            }
        }
    }

    private void flip(char[][] board,int i,int j,int m,int n,char src,char dest){
        if(i>=0 && i<m && j>=0 && j<n && board[i][j]==src){
            board[i][j] = dest;
            flip(board,i+1,j,m,n,src,dest);
            flip(board,i-1,j,m,n,src,dest);
            flip(board,i,j+1,m,n,src,dest);
            flip(board,i,j-1,m,n,src,dest);
        }
    }
}

