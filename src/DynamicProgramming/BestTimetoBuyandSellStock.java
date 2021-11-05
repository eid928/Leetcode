package DynamicProgramming;

public class BestTimetoBuyandSellStock {

	public static void main(String[] args) {
		/**
		 * 買賣股票
		 * 規則：買一次賣一次，求最大獲利
		 */
		int[] prices1 = {7,1,5,3,6,4};
		int[] prices2 = {7,6,4,3,1};

		System.out.println(maxProfit(prices1));
		System.out.println(maxProfit(prices2));
	}
    public static int maxProfit(int[] prices) {
    	
    	int lowestSoFar = Integer.MAX_VALUE;
    	int maxProfit = 0;
    	
    	for (int i = 0; i < prices.length; i++) {
    		int profitBySellAtI = prices[i] - lowestSoFar;
    		maxProfit = Math.max(maxProfit, profitBySellAtI);
    		lowestSoFar = Math.min(lowestSoFar, prices[i]);
    	}
    	
        return maxProfit;
    }
}
