package DynamicProgramming;

public class CoinChange {

	public static void main(String[] args) {
		/**
		 * array表示硬幣面額的種類，amount為要付的錢
		 * 請求出最少要用幾個硬幣才能付得出來
		 */
		int[] coins = {5,2,1};
		int amount = 11;
		System.out.println(coinChange(coins, amount));
	}

	public static int coinChange(int[] coins, int amount) {
		/**
		 * dp
		 * 狀態轉移：
		 * dp[11] = dp[10]+1, dp[9]+1, dp[6]+1 中挑一個最小的
		 */
		int[] dp = new int[amount+1];
		dp[0] = 0;
		
		for (int i = 1; i < dp.length; i++) {
			
			int method = -1;
			for (int coin : coins) {
				
				if (i == coin) {
					method = 1;
					continue;
				}
				if (i - coin < 0 || dp[i - coin] == -1) {
					continue;
				}
				if (method == -1) {
					method = dp[i - coin] +1 ;
				} else {
					method = Math.min(dp[i - coin] +1, method);
				}
			}
			
			dp[i] = method;
		}
		
		return dp[amount];
    }
}
