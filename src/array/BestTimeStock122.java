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
	public static void main(String[] args) {
		
	}

}
