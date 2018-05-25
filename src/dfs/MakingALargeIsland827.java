package dfs;

import java.util.ArrayList;
import java.util.List;

public class MakingALargeIsland827 {
	private int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	/**
	 * 对于遇到的每个0，尝试修改为1，用dfs数数面积；取最大的面积值.当grid中没有0的时候，面积为n^2
	 * 时间复杂度O(n^4)
	 * @param grid
	 * @return
	 */
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int max = 1;
        boolean hasZero = false;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==0){
                	boolean[][] visited = new boolean[n][n];
                	grid[i][j] = 1;
                	hasZero = true;
                    int area = dfs(grid,visited,i,j);
                    max = Math.max(max,area);
                    grid[i][j] = 0;
                }
            }
        }
        return hasZero?max:n*n;
    }
    
    private int dfs(int[][] grid,boolean[][] visited, int i,int j){
        if(i<0 || i>=grid.length || j<0 || j>=grid.length || visited[i][j]) return 0;
        if(grid[i][j]==0){
            return 0;
        }
        visited[i][j]=true;
        int r = 1;
        for(int[] dir : dirs){
        	int newi = i+dir[0];
        	int newj = j+dir[1];
        	r += dfs(grid,visited,newi,newj);
        }
        return r;
    }
    /**
     * 将0周围的1分组，可以连续在一起的1分为一组
     * 时间复杂度O(n^2)
     * @param grid
     * @return
     */
    public int largestIslandV2(int[][] grid) {
		int n = grid.length;
		int idx = 2;
		int[] area = new int[n * n + 2];
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					area[idx] = getArea(grid, i, j, idx++);
					ans = Math.max(ans, area[idx-1]);
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0) {
					int r = 1;
					List<Integer> list = new ArrayList<Integer>();
					for (int[] dir : dirs) {
						int newi = i + dir[0];
						int newj = j + dir[1];
						if (newi < 0 || newi >= n || newj < 0 || newj >= n || grid[newi][newj] < 2 ){
							continue;
						}
						if(!list.contains(grid[newi][newj])){
							list.add(grid[newi][newj]);
							r += area[grid[newi][newj]];
						}
					}
					ans = Math.max(ans, r);
				}
			}
		}
		return ans;
    }

	private int getArea(int[][] grid, int i, int j, int idx) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid.length || grid[i][j] != 1)
			return 0;
		grid[i][j] = idx;
		int r = 1;
		for (int[] dir : dirs) {
			int newi = i + dir[0];
			int newj = j + dir[1];
			r += getArea(grid, newi, newj,idx);
		}
		return r;
	}

	public static void main(String[] args) {
    	//int[][] grid = new int[][]{new int[]{0,0},new int[]{0,1}};
    	int[][] grid = new int[][]{new int[]{1,0},new int[]{0,1}};
    	int r = new MakingALargeIsland827().largestIslandV2(grid);
    	System.out.println(r);
	}
}
