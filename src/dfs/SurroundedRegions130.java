package dfs;

import java.util.LinkedList;
import java.util.Queue;

import uf.UnionFind2;

public class SurroundedRegions130 {
	/**
	 * 难点是题目理解。
	 * 我理解的题意是：如果O在矩形最外圈，那么它不会被翻转为X；如果有和最外圈的O在垂直方向上或者水平方向上相邻的O，那它也不会被翻转为X。其余的O被翻转为X。
	 * 实际上这个题目的含义是：所有被X包围的O都翻转为X。
	 * Flood fill
	 * @param board
	 */
	public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0)
            return;
        int m = board.length;
        int n = board[0].length;
        for(int j=0;j<n;j++){
            if(board[0][j]=='O'){
                fill(board,0,j,m,n);
            }
            if(board[m-1][j]=='O'){
                fill(board,m-1,j,m,n);
            }
        }
        for(int i=0;i<m;i++){
            if(board[i][0]=='O'){
                fill(board,i,0,m,n);
            }
            if(board[i][n-1]=='O'){
                fill(board,i,n-1,m,n);
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='O'){
                    board[i][j] = 'X';
                }
                if(board[i][j]=='#'){
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private void fill(char[][] board,int i,int j,int m,int n){
        board[i][j] = '#';
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{i,j});
        while(!queue.isEmpty()){
            int[] idxs = queue.poll();
            int row = idxs[0];
            int col = idxs[1];
            if(row-1>=0 && board[row-1][col]=='O'){
                board[row-1][col] = '#';
                queue.add(new int[]{row-1,col});
            }
            if(row+1<m && board[row+1][col]=='O'){
                board[row+1][col] = '#';
                queue.add(new int[]{row+1,col});
            }
            if(col-1>=0 && board[row][col-1]=='O'){
                board[row][col-1] = '#';
                queue.add(new int[]{row,col-1});
            }
            if(col+1<n && board[row][col+1]=='O'){
                board[row][col+1] = '#';
                queue.add(new int[]{row,col+1});
            }
        }
    }
    
    /**
     * uion-find 算法将O分为两组；一组是与dummyNode一组，不改变值的，其他组是需要改变值的
     * @param board
     */
    public void solveV2(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0)
            return;
        int m = board.length;
        int n = board[0].length;
        int dummyNode = m * n;
        UnionFind2 uf = new UnionFind2(dummyNode+1);
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='O'){
                    if(i==0 || i==m-1 || j==0 || j==n-1){
                    	uf.union(i * n + j, dummyNode);
                    }else{
                    	 if(j - 1 >= 0 && board[i][j - 1] == 'O')  uf.union(getNode(i, j,n), getNode(i, j - 1,n));
                         if(i - 1 >= 0 && board[i - 1][j] == 'O')  uf.union(getNode(i, j,n), getNode(i - 1, j,n));
                         if(i + 1 < m && board[i + 1][j] == 'O')   uf.union(getNode(i, j,n), getNode(i + 1, j,n));
                         if(j + 1 < n && board[i][j + 1] == 'O')   uf.union(getNode(i, j,n), getNode(i, j + 1,n));
                    }
                }
            }
        }
        
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                if(board[i][j] == 'O'){
                    if(uf.find(getNode(i, j,n)) == uf.find(dummyNode)) board[i][j] = 'O';
                    else    board[i][j] = 'X';
                }
            }
        }
    }
    
    public int getNode(int i, int j,int n){
        return i * n + j;
    }
}
