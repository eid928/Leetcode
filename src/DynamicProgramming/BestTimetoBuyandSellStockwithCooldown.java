package DynamicProgramming;

import java.util.Arrays;

public class BestTimetoBuyandSellStockwithCooldown {

	public static void main(String[] args) {
		int[] prices = {1,2,3,0,2};
		System.out.println(maxProfit(prices));
		int[] prices2 = {1};
		System.out.println(maxProfit(prices2));
	}
	
    public static int maxProfit(int[] prices) {
    	/**
    	 * dfs + dp memo
    	 * dp[i][0]儲存第i天直到最後，不持股的狀態下所能獲取的最大利潤
    	 * dp[i][1]儲存第i天直到最後，持股的狀態下所能獲取的最大利潤
    	 */
    	int[][] dp = new int[prices.length][2];
    	for (int i = 0; i < dp.length; i++) {
    		Arrays.fill(dp[i], -1);
    	}
    	
        return dfs(prices, 0, false, dp);
    }

	private static int dfs(int[] prices, int index, boolean hasStock, int[][] dp) {
		/**
		 * 不需要儲存買價，買的瞬間就已經扣掉了
		 */
		if (index >= prices.length) {
			return 0;
		}
		
		if (dp[index][hasStock ? 0 : 1] != -1) {
			return dp[index][hasStock ? 0 : 1];
		}
		
		int stockPriceToday = prices[index];
		
		int maxProfit = 0;
		
		if (hasStock) { /* 手上有股票 */
			
			int sellToday = stockPriceToday + dfs(prices, index+2, false, dp); // 今天決定賣掉，隔天需要冷卻，直接跳到index+2
			int doNothingToday = dfs(prices, index+1, true, dp);
			maxProfit = Math.max(sellToday, doNothingToday);
		} else { /* 手上沒有股票 */
			
			int buyToday = -stockPriceToday + dfs(prices, index+1, true, dp);
			int doNothingToday = dfs(prices, index+1, false, dp);
			maxProfit = Math.max(buyToday, doNothingToday);
		}
		
		dp[index][hasStock ? 0 : 1] = maxProfit;
		return maxProfit;
	}

	public static int maxProfit2(int[] prices) {
    	
    	if (prices.length <= 1) {
			return 0;
		}
        /* https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/fei-zhuang-tai-ji-de-dpjiang-jie-chao-ji-tong-su-y/ */
    	int[][] dp = new int[prices.length][3];
    	/**
    	 * dp[][]
    	 * 0: 不持股
    	 * 1: 持股
    	 * 2: 不持股冷卻
    	 */
    	dp[0][0] = 0;
    	dp[0][1] = -prices[0];
    	dp[0][2] = 0;
    	
    	for (int i = 1; i < prices.length; i++) {
    		dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2]);
    		dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
    		dp[i][2] = dp[i-1][1] + prices[i];
    	}
    	
    	return Math.max(dp[dp.length-1][0], dp[dp.length-1][2]);
    }
}
