package DynamicProgramming;

public class PerfectSquares {

	public static void main(String[] args) {
		
		/**
		 * 給定一整數n，n可以用平方數相加而成
		 * 平方數：1,4,9,16...
		 * 求出n最少可以用多少個平方數相加而成？
		 */
		System.out.println(numSquares(12)); // 4+4+4 => 3個
		System.out.println(numSquares(13)); // 4+9 => 2個
	}
    public static int numSquares(int n) {
    	/**
    	 * dp: 儲存n=i的時候的答案
    	 * 當走到i的時候，i的答案有可能是
    	 * 1. i-1的答案＋1
    	 * 2. i-4的答案＋1
    	 * 3. i-9的答案＋1 ...直到減去的數最靠近i為止
    	 * 選最小的即是dp[i]
    	 * 要遍歷至n，每個位置遍歷根號n遍
    	 * 故時間複雜度＝O(n * 根號n)
    	 */
    	
    	int[] dp = new int[n+1];
    	dp[0] = 0;
    	
    	for (int i = 1; i <= n; i++) {
    		dp[i] = Integer.MAX_VALUE;
    		for (int j = 1; j*j <= i; j++) {
    			dp[i] = Math.min(dp[i-j*j]+1, dp[i]);
    		}
    	}
    	
        return dp[n];
    }
}
