package bfs;

import java.util.*;

public class CutsOff675 {
    private int[][] pos = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    private int m;
    private int n;
    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<int[]>();
        m = forest.size();
        n = forest.get(0).size();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(forest.get(i).get(j)>1){
                    trees.add(new int[]{i,j,forest.get(i).get(j)});
                }
            }
        }

        Collections.sort(trees, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        int ans = 0, sr = 0,sc = 0;
        for(int[] tree : trees){
            int d = bfs(forest,sr,sc,tree[0],tree[1]);

            if(d<0) return -1;
            ans += d;
            forest.get(tree[0]).set(tree[1],1);
            sr = tree[0];
            sc = tree[1];
        }
        return ans;
    }

    private int bfs(List<List<Integer>> forest,int sr,int sc,int tr,int tc){
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{sr,sc});
        boolean[][] seen = new boolean[m][n];
        seen[sr][sc] = true;
        int step = 0;
        while(!queue.isEmpty()){

            int size = queue.size();
            for(int k=0;k<size;k++){
                int[] array = queue.poll();
                sr = array[0];
                sc = array[1];
                if(sr == tr && sc == tc) return step;
                for(int i=0;i<4;i++){
                    int nr = sr + pos[i][0];
                    int nc = sc + pos[i][1];
                    if(nr>=0 && nr<m && nc>=0 && nc<n && seen[nr][nc]==false && forest.get(nr).get(nc)>0){
                        queue.offer(new int[]{nr,nc});
                        seen[nr][nc]=true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
