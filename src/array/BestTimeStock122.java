package array;

public class BestTimeStock122 {
	/**
	 * 我的想法：一次交易一定要收益>0.怎么确定是正确的？
	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {
		int maxP = 0;
		if(prices.length==0) return maxP;
		int price = prices[0];
		for(int i=1;i<prices.length;i++){
			if(prices[i] - price>0){
				maxP += (prices[i] - price);
			}
			price = prices[i];
		}
		return maxP;
    }

	/**
	 * 这个版本比较好理解：找局部最小值和局部最大值
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/discuss/208241/Explanation-for-the-dummy-like-me
	 * @param prices
	 * @return
	 */
	public int maxProfitV2(int[] prices) {
		int maxProfit = 0;
		int i=0;
		int n = prices.length;
		while(i<n-1){
			while(i<n-1 && prices[i+1]<=prices[i]) i++;
			int buy = prices[i];
			while(i<n-1 && prices[i+1]>prices[i]) i++;
			int sell = prices[i];
			maxProfit += sell - buy;
		}
		return maxProfit;
	}
	public static void main(String[] args) {
		
	}

}
