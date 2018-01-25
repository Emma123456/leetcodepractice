package array;

public class MinCostClimbingStairs746 {
	private int minCost;
	/**
	 * 数组下标从0开始，那么就从-1级台阶开始
	 * -1：可以花费cost[0]，也可以花费cost[1]，做暴力枚举，选择一个最小的代价
	 * @param cost
	 * @return
	 */
	public int minCostClimbingStairs(int[] cost) {
		minCost = Integer.MAX_VALUE;
		visit(cost,0,0,true);
		return minCost;
        
    }

	private void visit(int[] cost, int curStep,int curCost,boolean skip) {
		if(curStep == cost.length-1){
			if(!skip){
				curCost += cost[curStep];
			}
			curStep++;
		}
		if(curStep>=cost.length){
			minCost = Math.min(curCost, minCost);
			return;
		}
		//花费代价跳过这个台阶，下一个台阶就允许直接跳过
		visit(cost,curStep+1,curCost+cost[curStep],true);
		if(skip){
			//不花费代价跳过这个台阶，下一个台阶就必须付出代价
			visit(cost,curStep+1,curCost,false);
		}
	}
	/**
	 * 动态规划，找到递推式 dp[i] = min(dp[i-2] + cost[i-2], dp[i-1] + cost[i-1])
	 * 
	 * 一定要注意边界：dp[i]是达到第i级台阶需要的cost，最后的返回值是达到第n+1级台阶的cost
	 * @param cost
	 * @return
	 */
	public int minCostClimbingStairsV2(int[] cost) {
		return dpcost(cost.length ,cost);
	}
	
	private int dpcost(int n,int[] cost){
		if(n==0 || n==1){
			return 0;
		}
		return Math.min(dpcost(n-2,cost)+cost[n-2], dpcost(n-1,cost)+cost[n-1]);
	}
	
	public int minCostClimbingStairsV3(int[] cost) {
		int[] dp = new int[cost.length+1];
		dp[0] = 0;
		dp[1] = 0;
		for(int i=2;i<cost.length+1;i++){
			dp[i] = Math.min(dp[i-2]+cost[i-2], dp[i-1]+cost[i-1]);
		}
		return dp[cost.length ];
	}
	
	public static void main(String[] args) {
		int[] cost = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
		int r = new MinCostClimbingStairs746().minCostClimbingStairs(cost);
		System.out.println(r);
	}
}
