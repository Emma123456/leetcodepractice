package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper529 {
    /**
     * BFS思路的话，点节点【x，y】；【x，y】的邻接点是一层
     * 这个题目的难点1是英语问题，没有理解 If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
     * 难点2 是计算change数组
     * 相邻节点是 8 个方向上的节点
     * @param board
     * @param click
     * @return
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board==null || board.length==0) return board;
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(click);
        int[][] change = new int[][]{{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{-1,1},{0,1},{1,1}};
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int[] position = queue.poll();
                int x = position[0];
                int y=position[1];
                if(board[x][y] =='M') {
                    board[x][y] = 'X';
                    return board;
                }
                if(board[x][y]=='E'){
                    visited[x][y] = true;
                    int number = 0;
                    for(int j=0;j<change.length;j++){
                        if(x+change[j][0]>=0 && x+change[j][0]<m && y+change[j][1]>=0 && y+change[j][1]<n){
                            if(board[x+change[j][0]][y+change[j][1]]=='M'){
                                number++;
                            }
                        }
                    }
                    if(number==0){
                        board[x][y] = 'B';
                        for(int j=0;j<change.length;j++){
                            if(x+change[j][0]>=0 && x+change[j][0]<m && y+change[j][1]>=0 && y+change[j][1]<n){
                                if(visited[x+change[j][0]][y+change[j][1]]==false){
                                    queue.offer(new int[]{x+change[j][0],y+change[j][1]});
                                }
                            }
                        }
                    }else{
                        board[x][y] = (char)(number+48);
                    }
                }
            }
        }
        return board;
    }
}
