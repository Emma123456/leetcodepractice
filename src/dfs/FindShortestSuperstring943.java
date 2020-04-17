package dfs;

import java.util.ArrayList;
import java.util.List;

public class FindShortestSuperstring943 {
    private String[] A;
    private int[][] cost;
    private int minCost = Integer.MAX_VALUE;
    private ArrayList<Integer> bestPath;

    /**
     * 1125ms
     * @param A
     * @return
     */
    public String shortestSuperstring(String[] A) {
        this.A = A;
        int n = A.length;
        bestPath = null;
        minCost = Integer.MAX_VALUE;
        cost = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j = 0; j<n;j++){
                cost[i][j] = minLength(A[i], A[j]);
            }
        }
        boolean[] visited = new boolean[A.length];
        dfs(A.length,0,visited,new ArrayList<Integer>());
        return contanctString(bestPath);
    }

    private int minLength(String str1, String str2){
        int m = Math.min(str1.length(),str2.length());
        for(int i = m; i>0;i--){
            if(str1.endsWith(str2.substring(0,i))){
                return  str2.length()-i;
            }
        }
        return str2.length();
    }


    private void dfs(int m,int curCost,boolean[] visited, ArrayList<Integer> path) {
        if(curCost > minCost) return;
        if(m == 0){
            //注意结果需要完全拷贝
            minCost = curCost;
            bestPath = new ArrayList<Integer>(path);
            return;
        }
        for(int i = 0;i<A.length;i++){
            if(visited[i]==false){
                visited[i] = true;
                path.add(i);
                if( path.size() == 1){
                    dfs(m-1,curCost+A[i].length(), visited,path);
                }else{
                    int preIndex = path.get(path.size()-2);
                    dfs(m-1,curCost+cost[preIndex][i], visited,path);
                }

                path.remove(path.size()-1);
                visited[i] = false;
            }
        }
    }

    /**
     *把路径中的字符串拼接起来
     */
    private String contanctString(List<Integer> path){
        String str = A[path.get(0)];
        for(int i = 1; i< path.size();i++){
            String tmp = A[path.get(i)];

            str += tmp.substring(tmp.length()-cost[path.get(i-1)][path.get(i)]);
        }
        return str;
    }

    public static void main(String[] args) {
        String[] A = new String[]{"catg","ctaagt","gcta","ttca","atgcatc"};
        String str = new FindShortestSuperstring943().shortestSuperstring(A);
        System.out.println(str);
    }
}
