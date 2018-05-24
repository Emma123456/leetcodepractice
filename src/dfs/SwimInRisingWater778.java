package dfs;

import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * 测试用例
[[0,2],[1,3]]
[[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
[[3,2],[0,1]]

 * @author Administrator
 *
 */
public class SwimInRisingWater778 {
	class Pair {
		int x;
		int y;
		int v;

		Pair(int x, int y, int v) {
			this.x = x;
			this.y = y;
			this.v = v;
		}
	}

	private int minCost = Integer.MAX_VALUE;

	/**
	 * 超时
	 * @param grid
	 * @return
	 */
	public int swimInWater(int[][] grid) {
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		swim(grid, visited, 0, 0, grid[0][0], grid[0][0]);
		return minCost;
	}

	private void swim(int[][] grid, boolean[][] visited, int i, int j, int t, int cost) {
		if (i == grid.length - 1 && j == grid[0].length) {
			minCost = Math.min(minCost, cost);
			return;
		}
		if (cost > minCost) return;
		visited[i][j] = true;
		PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>(new Comparator<Pair>() {
			public int compare(Pair p1, Pair p2) {
				if (p1.v == p2.v) {
					return 0;
				}
				return p1.v > p2.v ? 1 : -1;
			}
		});

		if (i - 1 >= 0 && !visited[i - 1][j]) {
			minHeap.offer(new Pair(i - 1, j, grid[i - 1][j]));
		}
		if (i + 1 < grid.length && !visited[i + 1][j]) {
			minHeap.offer(new Pair(i + 1, j, grid[i + 1][j]));
		}
		if (j - 1 >= 0 && !visited[i][j - 1]) {
			minHeap.offer(new Pair(i, j - 1, grid[i][j - 1]));
		}
		if (j + 1 < grid[0].length && !visited[i][j + 1]) {
			minHeap.offer(new Pair(i, j + 1, grid[i][j + 1]));
		}
		while (!minHeap.isEmpty()) {
			Pair pair = minHeap.poll();
			//t 是不可以倒退的
			//只有时刻t增加才需要增加cost
			swim(grid,visited,pair.x,pair.y,pair.v>t?pair.v:t,cost+(pair.v>t?pair.v-t:0));
		}
		visited[i][j]=false;
	}
	
	public int swimInWaterV2(int[][] g) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[0]-b[0]);
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        boolean[][] visited = new boolean[g.length][g[0].length];
        pq.offer(new int[]{g[0][0], 0, 0});
        visited[0][0] = true;
        int res = 0;
        while(!pq.isEmpty()) {
            int[] tmp = pq.poll();
            if(tmp[1] == g.length-1 && tmp[2] == g[0].length-1){
            	res = tmp[0];
            	break;
            }
            for(int[] dir : dirs){
            	int x = tmp[1]+dir[0];
            	int y = tmp[2]+dir[1];
            	if(x>=0 && x<g.length && y>=0 && y<g[0].length && !visited[x][y]){
            		pq.add(new int[]{g[x][y]<tmp[0]?tmp[0]:g[x][y],x,y});
            		visited[x][y] = true;
            	}
            }
        }
        return res;
    }
	public static void main(String[] args) {
		int[][] grid = new int[][]{new int[]{10,12,4,6},new int[]{9,11,3,5},new int[]{1,7,13,8},new int[]{2,0,15,14}};
		new SwimInRisingWater778().swimInWaterV2(grid);
	}
}
