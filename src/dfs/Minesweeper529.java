package dfs;

import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper529 {
	private int[] dx = new int[] { -1,-1,-1,0,0,1,1,1 };
	private int[] dy = new int[] { -1,0,1,-1,1,-1,0,1 };

	/**
	 * 题目不难，DFS。问题是写对dx和dy
	 * @param board
	 * @param click
	 * @return
	 */
	public char[][] updateBoard(char[][] board, int[] click) {
		dfs(board, click);
		return board;
	}

	private void dfs(char[][] board, int[] click) {
		if (click[0] < 0 || click[0] >= board.length || click[1] < 0 || click[1] >= board[0].length )
			return;
		int x = click[0];
		int y = click[1];
		if (board[x][y] == 'M') {
			board[x][y] = 'X';
			return;
		}
		if (board[x][y] == 'E') {
			// count M
			int count = 0;
			for (int i = 0; i < dx.length; i++) {
				int newx = x + dx[i];
				int newy = y + dy[i];
				if (newx >= 0 && newx < board.length && newy >= 0 && newy < board[0].length) {
					if (board[newx][newy] == 'M') {
						count++;
					}
				}
			}
			if (count == 0) {
				board[x][y] = 'B';
				for (int i = 0; i < dx.length; i++) {
					int newx = x + dx[i];
					int newy = y + dy[i];
					dfs(board, new int[] { newx, newy });
				}
			} else {
				board[x][y] = (char) (count + 48);
			}
		}
	}
	
	
	public char[][] updateBoardV2(char[][] board, int[] click) {
		bfs(board, click);
		return board;
	}
	private void bfs(char[][] board, int[] click) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(click);
		while(!queue.isEmpty()){
			int[] pos = queue.poll();
			int x = pos[0];
			int y = pos[1];
			if (board[x][y] == 'M') {
				board[x][y] = 'X';
				break;
			}
			if (board[x][y] == 'E') {
				// count M
				int count = 0;
				for (int i = 0; i < dx.length; i++) {
					int newx = x + dx[i];
					int newy = y + dy[i];
					if (newx >= 0 && newx < board.length && newy >= 0 && newy < board[0].length) {
						if (board[newx][newy] == 'M') {
							count++;
						}
					}
				}
				if (count == 0) {
					board[x][y] = 'B';
					for (int i = 0; i < dx.length; i++) {
						int newx = x + dx[i];
						int newy = y + dy[i];
						if (newx >= 0 && newx < board.length && newy >= 0 && newy < board[0].length) {
							queue.add(new int[]{newx,newy});
						}
					}
				} else {
					board[x][y] = (char) (count + 48);
				}
			}
		}
	}

	public static void main(String[] args) {
		char[][] board = new char[][]{
				{'E','E','E','E','E'},{'E','E','M','E','E'},{'E','E','E','E','E'},{'E','E','E','E','E'}	
		};
		new Minesweeper529().updateBoard(board, new int[]{3,0});
	}
}
