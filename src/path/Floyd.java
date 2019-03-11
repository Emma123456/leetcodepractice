package path;

public class Floyd {
    /**
     *求图中任意两点之间的最短距离
     * https://www.cnblogs.com/wangyuliang/p/9216365.html
     * @param state
     *           初始状态下，各个节点之间的路径
     * @param n
     *          图中节点的数量
     */
    public void multiSourceShortestPath(int[][] state,int n){
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(state[i][j] > state[i][k]+state[k][j]){
                        state[i][j] = state[i][k]+state[k][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        int maxValue = Integer.MAX_VALUE -10000;
        int[][] state = new int[][]{{0,2,6,4},{maxValue,0,3,maxValue},{7,maxValue,0,1},{5,maxValue,12,0}};
        int n = 4;
        new Floyd().multiSourceShortestPath(state,n);
        for(int i=0;i<n;i++){
            System.out.println("========================");
            for(int j=0;j<n;j++){
                System.out.print(state[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
