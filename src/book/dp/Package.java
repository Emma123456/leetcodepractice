package book.dp;

import java.util.Arrays;

/**
 * 动态规划第一部分
 */
public class Package {
    private int[] weight =  new int[]{2,2,4,6,3};
    private int n = 5;//物品个数
    private int w = 9;//背包承受的最大重量
    private int maxW = Integer.MIN_VALUE;//结果

    /**
     * 处理第i个物品的情况，当前重量是cw
     * 这是回溯法，复杂度是指数级的。有些状态会计算多次。
     * @param i
     * @param cw
     */
    public void f(int i,int cw){
        if(cw==w || i==n){
            maxW =Math.max(cw,maxW);
            return;
        }
        f(i+1,cw);//第i个物品，不装入背包
        if(cw+weight[i]<=w){
            f(i+1,cw+weight[i]);//第i个物品，装入背包
        }
    }


    private boolean[][] mem = new boolean[n][w+1];

    /**
     * 记录状态，已经计算过的状态就不再计算了
     * @param i
     * @param cw
     */
    public void fV2(int i,int cw){
        if(cw==w || i==n){
            maxW =Math.max(cw,maxW);
            return;
        }
        if(mem[i][cw]) return;
        mem[i][cw] = true;
        f(i+1,cw);//第i个物品，不装入背包
        if(cw+weight[i]<=w){
            f(i+1,cw+weight[i]);//第i个物品，装入背包
        }

    }

    public int knapsnack(int[] weight,int n,int w){
        boolean[][] states = new boolean[n][w+1];
        states[0][0] = true;
        if(weight[0]<w){
            states[0][weight[0]] = true;
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<w;j++){
                if(states[i-1][j]==true){
                    states[i][j] = true;
                }
            }
            for(int j=0;j<=w-weight[i];j++){
                if(states[i-1][j]==true){
                    states[i][j+weight[i]] = true;
                }
            }
        }
        for(int j=w;j>=0;j--){
            if(states[n-1][j]) return j;
        }
        return 0;
    }


    public int knapsnackV2(int[] weight,int n,int w){
        boolean[] states = new boolean[w+1];
        states[0] = true;
        if(weight[0]<w){
            states[weight[0]] = true;
        }
        for(int i=1;i<n;i++){
            //使用一维数组需要从后向前计算，否则会有多余的计算
            for(int j=w-weight[i];j>=0;j--){
                if(states[j]==true){
                    states[j+weight[i]] = true;
                    System.out.print(j+weight[i]+" \t");
                }
            }
        }
        for(int j=w;j>=0;j--){
            if(states[j]) return j;
        }
        return 0;
    }


    /**
     * 0-1背包问题升级 在满足背包最大重量的前提下，要价值最高
     * @param weight
     * @param n
     * @param w
     * @return
     */
    public int knapsnackV3(int[] weight,int[] value, int n,int w){

        int[][] state = new int[n][w+1];
        int maxValue = -1;
        for(int i=0;i<n;i++){
            Arrays.fill(state[i],-1);
        }

        state[0][0] = 0;
        if(weight[0]<w){
            state[0][weight[0]] = value[0];
        }

        for(int i=1;i<n;i++){
            //不放入第i物品
            for(int j=0;j<=w;j++){
                if(state[i-1][j]>-1){
                    state[i][j] = state[i-1][j];
                }
            }

            //放入第i物品
            for(int j=0;j<=w-weight[i];j++){
                if(state[i-1][j]>-1){
                    int v = state[i-1][j]+value[i];
                    if(v>state[i][j+weight[i]]){
                        state[i][j+weight[i]] = v;
                    }
                }
            }
        }

        for(int j=w;j>=0;j--){
            if(state[n-1][j]>-1){
                maxValue = Math.max(maxValue,state[n-1][j]);
            }
        }
        return maxValue;
    }


    /**
     * 对V3 做空间优化
     * @param weight
     * @param value
     * @param n
     * @param w
     * @return
     */
    public int knapsnackV4(int[] weight,int[] value, int n,int w){

        int[] state = new int[w+1];
        int maxValue = -1;
        Arrays.fill(state,-1);

        state[0] = 0;
        if(weight[0]<w){
            state[weight[0]] = value[0];
        }

        for(int i=1;i<n;i++){
            //放入第i物品
            for(int j=w-weight[i];j>=0;j--){
                if(state[j]>-1){
                    int v = state[j]+value[i];
                    if(v>state[j+weight[i]]){
                        state[j+weight[i]] = v;
                    }
                }
            }
        }

        for(int j=w;j>=0;j--){
            if(state[j]>-1){
                maxValue = Math.max(maxValue,state[j]);
            }
        }
        return maxValue;
    }


    /**
     * 双十一，选商品
     *
     * @param items
     *          商品价格
     * @param n
     *          商品个数
     * @param w
     *          满减的价格
     */
    public void double11advance(int[] items,int  n,int w){
        int maxw = 3*w+1;
        boolean[][] states = new boolean[n][maxw+1];//凑单的商品价值不超过3*w
        states[0][0] = true;
        if(weight[0]<w){
            states[0][weight[0]] = true;
        }
        for(int i=1;i<n;i++){
            //不选择第i个商品
            for(int j=0;j<maxw+1;j++){
                if(states[i-1][j]==true){
                    states[i][j] = true;
                }
            }
            //选择第i个商品
            for(int j=0;j<=maxw-items[i];j++){
                if(states[i-1][j]==true){
                    states[i][j+items[i]] = true;
                }
            }
        }
        int j=w;
        for(;j<=maxw;j++){
            if(states[n-1][j]) break;
        }
        if(j==maxw+1){
            System.out.println("没有找到解决方案");
        }else{
            //从要想到达state[i][j]只有 state[i-1][j] 和 state[i][j-items[i]] 两个选择。
            System.out.println(j);
            for(int i=n-1;i>0;i--){
                if(j-items[i]>=0 && states[i-1][j-items[i]]==true){
                    System.out.println(i+"\t"+items[i]);//购买这个
                    j -= items[i];
                }
            }
            if(j!=0){
                System.out.println(0+"\t"+items[0]);
            }
        }

    }


    public int trianglePath(int[][] nums,int m,int n){
        int[][] pathsum = new int[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(pathsum[i],Integer.MAX_VALUE);
        }
        pathsum[0][0] = nums[0][0];
        for(int i=0;i<m-1;i++){
            for(int j=0;j<i+1;j++){
                pathsum[i+1][j] = Math.min(pathsum[i+1][j],pathsum[i][j]+nums[i+1][j]);
                pathsum[i+1][j+1] = Math.min(pathsum[i+1][j+1],pathsum[i][j]+nums[i+1][j+1]);
            }
        }
        int minPath = Integer.MAX_VALUE;
        for(int val : pathsum[m-1]){
            minPath = Math.min(minPath,val);
        }
        return minPath;
    }
    public static void main(String[] args){
        int[][] nums = new int[5][5];
        nums[0] = new int[]{5};
        nums[1] = new int[]{7,8};
        nums[2] = new int[]{2,3,4};
        nums[3] = new int[]{4,9,6,1};
        nums[4] = new int[]{9,7,9,4,5};
        int r = new Package().trianglePath(nums,5,5);
        System.out.println(r);


        Package p = new Package();
        p.f(0,0);
        System.out.println(p.maxW);
    }
}
