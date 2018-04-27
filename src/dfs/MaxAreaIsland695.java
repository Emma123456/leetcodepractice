package dfs;

import java.util.Stack;

public class MaxAreaIsland695 {
	public int maxAreaOfIsland(int[][] grid) {
		int m = grid.length;
		if (m == 0) {
			return 0;
		}
		int n = grid[0].length;
		int maxA = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(grid[i][j] == 1){
					int area = dfs(grid,i,j,m,n);
					maxA = Math.max(maxA, area);
				}
			}
		}
		return maxA;
	}

	private int dfs(int[][] grid, int i, int j,int m,int n) {
		if(i>=m || i<0 || j>=n || j<0 || grid[i][j] ==0 ){
			return 0;
		}
		//为了防止重复计算
		grid[i][j] = 0;
		int area = 1;
		area += dfs(grid,i-1,j,m,n);
		area += dfs(grid,i,j-1,m,n);
		area += dfs(grid,i,j+1,m,n);
		area += dfs(grid,i+1,j,m,n);
		return area;
	}
	public int maxAreaOfIslandV2(int[][] grid) {
        if(grid==null || grid.length==0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int max = 0;
        int[] dr = new int[]{1,-1,0,0};
        int[] dc = new int[]{0,0,1,-1};
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 1 && !visited[i][j]){
                    int area = 0;
                    Stack<int[]> stack = new Stack<int[]>();
                    stack.push(new int[]{i,j});
                    visited[i][j] = true;
                    while(!stack.isEmpty()){
                        int[] node = stack.pop();
                        int r = node[0],c=node[1];
                        area++;
                        for(int k=0;k<4;k++){
                            int newi = r + dr[k];
                            int newj = c + dc[k];
                            if(newi>=0 && newj>=0 && newi<m && newj<n && !visited[newi][newj] && grid[newi][newj]==1){
                                stack.push(new int[]{newi,newj});
                                visited[newi][newj] = true;
                            }
                        }
                    }
                    
                    max = Math.max(max,area);
                }
            }
        }
        return max;
    }
}
