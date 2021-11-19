package DynamicProgramming;

import java.util.Arrays;

public class BestTimetoBuyandSellStockwithTransactionFee {

	public static void main(String[] args) {
		/**
		 * 一樣是買賣股票，這次再賣出的時候有手續費
		 * 一樣的規則，手上一次只能有一張股票
		 */
		int[] prices = {1,3,2,8,4,9};
		System.out.println(maxProfit(prices, 2)); // 8
		int[] prices2 = {1,3,7,5,10,3};
		System.out.println(maxProfit(prices2, 3)); // 6
	}

    public static int maxProfit(int[] prices, int fee) {
        /**
         * dfs + memo dp
         */
    	int[][] dp = new int[prices.length][2];
    	for (int i = 0; i < dp.length; i++) {
    		Arrays.fill(dp[i], -1);
    	}
    	
    	return dfs(prices, fee, 0, false, dp);
    }

	private static int dfs(int[] prices, int fee, int index, boolean hasStock, int[][] dp) {
		
		if (index >= prices.length) {
			return 0;
		}
		
		if (dp[index][hasStock ? 1 : 0] != -1) {
			return dp[index][hasStock ? 1 : 0];
		}
		
		int stockPriceToday = prices[index];
		int maxProfit = 0;
		
		if (hasStock) {
			
			int sellToday = stockPriceToday-fee + dfs(prices, fee, index+1, false, dp);
			int doNothingToday = dfs(prices, fee, index+1, true, dp);
			maxProfit = Math.max(sellToday, doNothingToday);
		} else {
			
			int buyToday = -stockPriceToday + dfs(prices, fee, index+1, true, dp);
			int doNothingToday = dfs(prices, fee, index+1, false, dp);
			maxProfit = Math.max(buyToday, doNothingToday);
		}
		
		dp[index][hasStock ? 1 : 0] = maxProfit;
		return maxProfit;
	}
}
