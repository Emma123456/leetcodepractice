package dfs;

public class NumberIslands200 {
	private int[] dx = new int[]{-1,0,0,1};
    private int[] dy = new int[]{0,-1,1,0};
    public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int count=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1'){
                    walk(grid,i,j,m,n);
                    count++;
                }
            }
        }
        return count;
    }
    
    private void walk (char[][] grid,int i,int j,int m,int n){
        if(i<0 || i>=m || j<0|| j>=n || grid[i][j]!='1') return;
        grid[i][j] = '2';
        for(int x = 0;x<dx.length;x++){
            walk(grid,i+dx[x],j+dy[x],m,n);
        }
    }
    public static void main(String[] args) {
    	char[][] grid = new char[][]{
    			{'1','1','0','0','0'}
    			,{'1','1','0','0','0'}
    			,{'0','0','1','0','0'}
    			,{'0','0','0','1','1'}
    	};
    	
    	new NumberIslands200().numIslands(grid);
	}
}
