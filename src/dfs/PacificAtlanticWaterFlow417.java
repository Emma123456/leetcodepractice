package dfs;

import java.util.ArrayList;
import java.util.List;

public class PacificAtlanticWaterFlow417 {
	private int[] dx = new int[]{-1,0,0,1};
    private int[] dy = new int[]{0,-1,1,0};
    private  List<int[]> result = new ArrayList<int[]>();
    public List<int[]> pacificAtlantic(int[][] matrix) {
        if(matrix==null || matrix.length==0) return result;
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int[][] visited = new int[m][n];
        for(int j=0;j<n;j++){
            dfs(matrix,visited,m,n,i,j,1);
        }
        int j = 0;
        for(i=1;i<m;i++){
            dfs(matrix,visited,m,n,i,j,1);
        }
       
        j=n-1;
        for(i=0;i<m;i++){
            dfs(matrix,visited,m,n,i,j,2);
        }
        i = m-1;
        for(j=0;j<n;j++){
            dfs(matrix,visited,m,n,i,j,2);
        }
        return result;
    }
    
    private void dfs(int[][] matrix,int[][] visited,int m,int n,int i,int j,int val){
        if(i<0 || i>=m || j<0 || j>=n) return;
        if(val==2 && visited[i][j]==1){
             result.add(new int[]{i,j});
        }
        if(visited[i][j]==val) return;
        visited[i][j] = val;
        for(int k=0;k<dx.length;k++){
            int newi = i+dx[k];
            int newj = j+dy[k];
            if(newi>=0 && newi<m && newj>=0 && newj<n && visited[newi][newj] !=val && matrix[i][j]<=matrix[newi][newj]){
                dfs(matrix,visited,m,n,newi,newj,val);
            }
        }
    }
}
