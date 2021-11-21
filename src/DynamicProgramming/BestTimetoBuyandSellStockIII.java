package DynamicProgramming;

import java.util.Arrays;

public class BestTimetoBuyandSellStockIII {

	public static void main(String[] args) {
		/**
		 * 買賣股票
		 * 這次的規則是
		 * 最多只能transaction兩次
		 * 也就是買賣兩次
		 */
		int[] prices = {3,3,5,0,0,3,1,4};
		System.out.println(maxProfit(prices)); // 6
		int[] prices2 = {1,2,3,4,5};
		System.out.println(maxProfit(prices2)); // 4
		int[] prices3 = {7,6,4,3,1};
		System.out.println(maxProfit(prices3)); // 0
	}

    public static int maxProfit(int[] prices) {
        /**
         * dp: dfs + memo
         * 每個樹狀圖的節點需保存三個status
         * index, 有沒有股票, 以及還剩下幾次transaction
         * 所以用一個三維dp來做memorize
         */
    	int[][][] dp = new int[prices.length][2][3];
    	for (int i = 0; i < dp.length; i++) {
    		for (int j = 0; j < dp[i].length; j++) {
    			Arrays.fill(dp[i][j], -1);
    		}
    	}
    	
    	int maxProfit = dfs(prices, 0, false, 2, dp);
    	
    	return maxProfit;
    }

	private static int dfs(int[] prices, int index, boolean hasStock, int transaction, int[][][] dp) {
		
		if (transaction <= 0) {
			return 0;
		}
		
		if (index >= prices.length) {
			return 0;
		}
		
		if (dp[index][hasStock? 1 : 0][transaction] != -1) {
			return dp[index][hasStock? 1 : 0][transaction];
		}
		
		int pricesToday = prices[index];
		int maxProfit = 0;
		
		if (hasStock) {
			
			int decideToSell = pricesToday + dfs(prices, index+1, false, transaction-1, dp);
			int decideToDoNothing = dfs(prices, index+1, true, transaction, dp);
			maxProfit = Math.max(decideToSell, decideToDoNothing);
		} else {
			
			int decideToBuy = -pricesToday + dfs(prices, index+1, true, transaction, dp);
			int decideToDoNothing = dfs(prices, index+1, false, transaction, dp);
			maxProfit = Math.max(decideToBuy, decideToDoNothing);
		}
		
		dp[index][hasStock? 1 : 0][transaction] = maxProfit;
		return maxProfit;
	}
}
