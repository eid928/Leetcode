package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class CoinChange2 {

	public static void main(String[] args) {
		
		int[] coins = {1,2,5};
		System.out.println(change(5, coins)); // 4: 5=5, 5=2+2+1, 5=2+1+1+1, 5=1+1+1+1+1
		int[] coins2 = {2};
		System.out.println(change(3, coins2)); // 0

	}
	public static int change(int amount, int[] coins) {
		/**
		 * dp tabulation 一維
		 * dp[i] = sum(dp[i - coins[j]])
		 * dp[0] = 1
		 */
		
		int[] dp = new int[amount+1];
		dp[0] = 1;
		
		for (int j = 0; j < coins.length; j++) { 
			/**
			 * 重點: 因為2+1和1+2算是同一種
			 * 所以同一種硬幣全部amount都看完才去看下一種硬幣
			 */
			
			for (int i = 1; i <= amount; i++) {
				
				if (i-coins[j]>=0) {
					dp[i] += dp[i-coins[j]];
				}
			}
		}
		
		return dp[amount];
	}
	
	public static int change2(int amount, int[] coins) {
		/**
		 * dp tabulation
		 * 由於加總的方法，是無論順序的，例如2+1和1+2算是同一種
		 * 所以dp必須紀錄用了哪些硬幣
		 * dp[i][j]為用0~j的硬幣，組成總額i的方法數
		 * dp[0][all] = 1
		 * dp[i][0] = 1 where i 能整除 coins[0]
		 * dp[i][j] = dp[i][j-1] (完全不用j) + dp[i-coins[j]][j] (至少用一個j)
		 * dp[5][1] = dp[5][0] + dp[3][1] = (1+1+1+1+1)(完全不用2) + (2+2+1, 2+1+1+1)(至少用一個2)
		 * dp[5][2] = dp[5][1] + dp[0][2] = (1+1+1+1+1, 2+2+1, 2+1+1+1)(完全不用5) + (5+0)(至少用一個5)
		 * 以此類推
		 * time: O(mn)
		 */
		
		int[][] dp = new int[amount+1][coins.length];
		
		for (int j = 0; j < coins.length; j++) {
			System.out.print("[");
			int coinsVal = coins[j];
			for (int i = 0; i <= amount; i++) {
				
				if (i == 0) {
					dp[i][j] = 1;
					continue;
				}
				
				if (j == 0) {
					dp[i][j] = i % coins[0] == 0 ? 1 : 0;
				} else {
					dp[i][j] = dp[i][j-1] + (i-coinsVal >= 0 ? dp[i-coinsVal][j] : 0);
				}
				
				
				System.out.print(dp[i][j]+",");
			}
			System.out.print("],");
		}
		
		return dp[amount][coins.length-1];
	}

    public static int change3(int amount, int[] coins) {
        /**
         * dfs + memo
         * 由於加總的方法，是無論順序的，例如2+1和1+2算是同一種
         * 所以dfs必須紀錄index，一種coin用完再用下一種
         * 一種coin可以最多使用i次，i * value <= amount
         * 小於i的都是可能的路徑
         * memo以String紀錄，同時紀錄amount和index
         */
    	Map<String, Integer> memo = new HashMap<String, Integer>();
    	
    	int result = dfs(amount, coins, 0, memo);
    	System.out.println(memo);
		return result;
    }

	private static int dfs(int amount, int[] coins, int index, Map<String, Integer> memo) {
		
		if (amount == 0) return 1;
        else if (index >= coins.length) return 0;
        else if (index == coins.length - 1) return amount % coins[index] == 0 ? 1 : 0;
		
		if (memo.containsKey(amount+","+index)) {
			return memo.get(amount+","+index);
		}
		
		int totalMethod = 0;
		int value = coins[index];
		
		for (int i = 0; i * value <= amount; i++) {
			
			int remain = amount - i * value;
			int resultForRemain = dfs(remain, coins, index+1, memo);
			totalMethod += resultForRemain;
		}
		
		memo.put(amount+","+index, totalMethod);
		return totalMethod;
	}
}
