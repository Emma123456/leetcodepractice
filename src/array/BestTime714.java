package array;

public class BestTime714 {
	/**
	 * 使用动态规划的思想。关键是找到递归方程。
	 * 定义：hold[i]是在第i天持有股票的状态下的最大收益；sold[i]是在第i天卖掉股票的状态下的最大收益； 
	 * 方程式：hold[i] = Math.max(hold[i-1],sold[i-1]-prices[i])//第i-1天就持有股票，或者第i天购买股票
	 * sold[i] = Math.max(hold[i-1]+prices[i]-fee,sold[i-1])
	 * 初始化：hold[0] = -prices[0];sold[0] = 0
	 * 注意：边界条件设定
	 * @param p
	 * @param fee
	 * @return
	 */
	public int maxProfit(int[] prices, int fee) {
		int n = prices.length;
		if(n<2) return 0;
		int[] hold = new int[prices.length];
		int[] sold = new int[prices.length];
		hold[0] = -prices[0];
		sold[0] = 0;
		for(int i=1;i<prices.length;i++){
			hold[i] = Math.max(hold[i-1],sold[i-1]-prices[i]);
			sold[i] = Math.max(hold[i-1]+prices[i]-fee,sold[i-1]);
		}
		return Math.max(hold[n-1], sold[n-1]);
	}
	
	public int maxProfitV2(int[] prices, int fee) {
		int n = prices.length;
		if(n<2) return 0;
		int hold = -prices[0];
		int sold = 0;
		int preHold = 0;
		for(int i=1;i<prices.length;i++){
			preHold = hold;
			hold = Math.max(hold,sold-prices[i]);
			sold = Math.max(preHold+prices[i]-fee,sold);
		}
		return sold;
	}
	
	/**
	 * 贪心：关键是选择一个能不能卖掉股票的点，重新开始寻找买入机会。
	 * 例如序列  1 3 2 8 ，如果发现2<3，就完成交易买1卖3，此时，由于fee=2，那么收益=(3-1-fee)+(8-2-fee) < (8-1-fee)，说明卖早了。
	 * 令maxP是当前最大的price，当(maxP-prices[i]>=fee)，时则可以在maxP处卖出，且不会存在早卖的情况。
	 * 解释二：如果股票降到了maxP-fee这个值以下，那就可以在maxP卖掉。
	 * @param prices
	 * @param fee
	 * @return
	 */
	public int maxProfitV3(int[] prices, int fee) {
		int n = prices.length;
		if (n < 2)
			return 0;
		int maxP = prices[0];
		int minP = prices[0];
		int curProfit = 0;
		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			maxP = Math.max(maxP, prices[i]);
			minP = Math.min(minP, prices[i]);
			curProfit = Math.max(curProfit, prices[i] - minP - fee);
			if(maxP - prices[i]>=fee){
				profit += curProfit;//在maxP处交易
				maxP = prices[i];
				minP = prices[i];
				curProfit = 0 ;
			}
		}
		return profit+curProfit;
	}
	
	/**
	 * 更费解的方法
	 * @param prices
	 * @param fee
	 * @return
	 */
	public int maxProfitV4(int[] prices, int fee) {
		int n = prices.length;
		if (n < 2)
			return 0;
		int minP = prices[0];
		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			if(prices[i]<minP){
				minP = prices[i];
			}else if(prices[i] - fee-minP>0){
				profit += prices[i] - fee-minP;
				minP = prices[i] - fee;
			}
		}
		return profit;
	}

	/**
	 * 暴力枚举
	 * 
	 * @param prices
	 * @param fee
	 * @return
	 */
	public int maxProfitV99(int[] prices, int fee) {
		return dfs(prices, 0, 0, fee, 0);
	}

	/**
	 ** 考虑在第idx天是买？卖？不操作？
	 **/
	private int dfs(int[] prices, int idx, int buyed, int fee, int profit) {
		if (idx >= prices.length) {
			return profit;
		}
		int max = Integer.MIN_VALUE;
		if (buyed == 0) {
			// 买
			max = Math.max(max, dfs(prices, idx + 1, 1, fee, profit - prices[idx]));
		} else {
			// 卖
			max = Math.max(max, dfs(prices, idx + 1, 0, fee, profit + prices[idx] - fee));
		}
		// 不操作
		max = Math.max(max, dfs(prices, idx + 1, buyed, fee, profit));
		return max;
	}

	public static void main(String[] args) {
		int[] prices = new int[]{1, 4, 5, 8};
		int fee = 2;
		int r = new BestTime714().maxProfitV4(prices, fee);
		System.out.println(r);
	}

}
