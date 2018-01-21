package array;

public class BestTimeStock121 {
	/**
	 * 这是最直观的深度优先算法，每天决定买还是不买；卖还是不卖，会有栈溢出的问题
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {
		return dfs(prices, 0, -1);
	}

	private int dfs(int[] prices, int idx, int buyprice) {
		if (idx >= prices.length) {
			return 0;
		}
		if (buyprice == -1) {
			int p1 = dfs(prices, idx + 1, prices[idx]);// 买了
			int p2 = dfs(prices, idx + 1, -1);// 不买
			return Math.max(p1, p2);
		} else {
			int p1 = prices[idx] - buyprice;// 卖了
			int p2 = dfs(prices, idx + 1, buyprice);// 下一天再卖
			return Math.max(p1, p2);
		}

	}

	/**
	 * 与Maximum Subarray习题类似
	 * @param prices
	 * @return
	 */
	public int maxProfitV2(int[] prices) {
		int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
	}
	
	public int maxProfitV3(int[] prices) {
		int maxProfit = 0;
		for(int i=0;i<prices.length;i++){
			//第i天买入，第j天卖掉
			for(int j=i+1;j<prices.length;j++){
				maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
			}
		}
		return maxProfit;
	}
	/**
	 * 对v3改进，得到v2
	 * @param prices
	 * @return
	 */
	public int maxProfitV3Pro(int[] prices) {
		int minPrices = prices[0];
		int maxProfit = 0;
		for(int j=0;j<prices.length;j++){//还是在第j天卖掉，但是买入的价钱一定是从0到第j天最便宜的那天
			minPrices = Math.min(minPrices, prices[j]);
			maxProfit = Math.max(maxProfit, prices[j] - minPrices);
		}
		return maxProfit;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
