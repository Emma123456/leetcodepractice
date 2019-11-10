package homework.dp;

public class Package {
    private int[] weight;
    private int maxWeight;
    private int max = -1;
    private boolean[][] state;
    public Package(int[] weight, int maxWeight){
        this.weight = weight;
        this.maxWeight = maxWeight;
    }
    public int takeMax() {
        max = -1;
        state = new boolean[weight.length][maxWeight+1];
        f(0,0);
        return max;
    }

    /**
     * 决策第i个物品
     * @param i
     * @param cw  当前重量是cw
     */
    private void f(int i, int cw) {
        if(i==weight.length  || cw==maxWeight){
            max = Math.max(max,cw);
            return;
        }
        if(state[i][cw]) return;

        state[i][cw] = true;
        //不放
        f(i+1,cw);
        if(weight[i]+cw<=maxWeight){
            f(i+1,cw+weight[i]);
        }
    }


    /**
     * 状态转移
     * */
    public int takeMaxV2() {
        //state[i][j] = true 表示决策完第i个物品后，可以到重量j。
        state = new boolean[weight.length][maxWeight+1];
        state[0][0] = true;
        if(weight[0]<=maxWeight){
            state[0][weight[0]] = true;
        }
        for(int i=1;i<weight.length;i++){
            for(int j=0;j<maxWeight;j++){
                if(state[i-1][j]){
                    state[i][j] = true;
                }
            }
            for(int j=0;j<=maxWeight-weight[i];j++){
                if(state[i-1][j]){
                    state[i][j+weight[i]] = true;
                }
            }
        }

        int max = 0;
        for(int i=maxWeight;i>=0;i--){
            if(state[weight.length-1][i]) return i;
        }
        return 0;
    }


    /**
     * 减少空间
     * @return
     */
    public int takeMaxV3() {
        //state[i][j] = true 表示决策完第i个物品后，可以到重量j。
        boolean[] state = new boolean[maxWeight+1];
        state[0] = true;
        if(weight[0]<=maxWeight){
            state[weight[0]] = true;
        }
        for(int i=1;i<weight.length;i++){
            for(int j=maxWeight-weight[i];j>=0;j--){
                if(state[j]){
                    state[j+weight[i]] = true;
                }
            }
        }

        int max = 0;
        for(int i=maxWeight;i>=0;i--){
            if(state[i]) return i;
        }
        return 0;
    }
}
