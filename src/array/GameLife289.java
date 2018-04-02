package array;

public class GameLife289 {
	/**
	 * 这个题目的难点是既要根据数组情况修改board[i][j]的新状态，还要能作为邻居找到board[i][j]原来的状态
	 * 可以使用如下策略：
	 * live->die	2	
live->live	1
die->die	0
die->live	3
		最后再遍历一次，把所有值%2。
	 * @param board
	 */
	public void gameOfLife(int[][] board) {
        int m  = board.length;
        if(m==0) return;
        int n = board[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int liveNeighbor = getLiveNeihborCount(board,i,j);
                if(board[i][j]==1){
                    if(liveNeighbor<2 || liveNeighbor>3){
                        board[i][j] = 2;
                    }
                }else{
                    if(liveNeighbor==3){
                        board[i][j] = 3;
                    }
                }
            }
        }
        for(int i=0;i<m;i++){
             for(int j=0;j<n;j++){
                board[i][j] = board[i][j]%2;
             }
        }
    }
    
    public int getLiveNeihborCount(int[][]board,int i,int j){
        int count = 0;
        for(int row = i-1;row<=i+1;row++){
            for(int col = j-1;col<=j+1;col++){
                if(row>=0 && col>=0 && row<board.length && col < board[0].length){
                	if(row==i && col==j){
                		continue;
                	}
                    if(board[row][col]==1 || board[row][col]==2){
                        count++;
                    }
                }
            }
        }
        return count;
    }
	public static void main(String[] args) {
		int[][]board = new int[][]{{1,1},{1,0}};
		new GameLife289().gameOfLife(board);
	}

}
