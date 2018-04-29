package dfs;

import java.util.Stack;

public class FillImage733 {
	private int[] dy = new int[] { 1, -1, 0, 0 };
	private int[] dx = new int[] { 0, 0, 1, -1 };

	/**
	 * 考虑 newColor = image[sr][sc]的情况
	 * @param image
	 * @param sr
	 * @param sc
	 * @param newColor
	 * @return
	 */
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		fill(image, sr, sc, newColor, image[sr][sc]);
		return image;
	}

	private void fill(int[][] image, int sr, int sc, int newColor, int val) {
		if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != val)
			return;
		image[sr][sc] = -newColor;
		for (int i = 0; i < 4; i++) {
			fill(image, sr + dy[i], sc + dx[i], newColor, val);
		}
		image[sr][sc] = newColor;
	}
	
	public int[][] floodFillV2(int[][] image, int sr, int sc, int newColor) {
		int m = image.length;
		int n = image[0].length;
		int val = image[sr][sc];
		boolean[][] visited = new boolean[m][n];
		Stack<int[]> stack = new Stack<int[]>();
		stack.push(new int[]{sr,sc});
		while(!stack.isEmpty()){
			int[] pos = stack.pop();
			image[pos[0]][pos[1]] = newColor;
			visited[pos[0]][pos[1]]= true;
			for (int i = 0; i < 4; i++) {
				int newx = pos[0]+dy[i];
				int newy = pos[1]+dx[i];
				if (newx < 0 || newx >= m || newy < 0 || newy >= n || image[newx][newy] != val || visited[newx][newy]){
					continue;
				}else{
					stack.push(new int[]{newx,newy});
				}
			}
		}
		return image;
	}
	public static void main(String[] args) {
		int[][] image = new int[][]{{0,0,0},{0,1,1}};
		image = new FillImage733().floodFillV2(image, 1, 1, 1);
		for(int[] val : image){
			System.out.println();
			for(int i :val){
				System.out.print(i+"\t");
			}
		}
	}
}
