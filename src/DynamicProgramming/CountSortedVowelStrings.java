package DynamicProgramming;

public class CountSortedVowelStrings {

	public static void main(String[] args) {
		/**
		 * 用5種母音a,e,i,o,u，可排出lexicographically sorted的字串
		 * 例如ae, ai, eiou
		 * 今給定一個正整數n
		 * 算出長度為n的這樣的字串組合共有幾組?
		 */
		System.out.println(countVowelStrings(2));
		System.out.println(countVowelStrings(33));
	}

    public static int countVowelStrings(int n) {
        /**
         * dp: tabulation
         * dp[i][0]代表是n=i的時候，用a~u的答案
         * dp[i][1]代表是n=i的時候，用e~u的答案，以此類推
         * 
         * 當i增加的時候
         * a後面可以接a~u開頭的所有答案
         * e後面只能接e~u開頭的所有答案
         * dp[i][0] = dp[i-1][0](a開頭) + dp[i-1][1](e開頭) + dp[i-1][2] + ...
         * dp[i][1] = dp[i-1][1] + dp[i-1][2] + ...
         */
    	int[][] dp = new int[n+1][5];
    	
    	dp[1][0] = 5;
    	dp[1][1] = 4;
    	dp[1][2] = 3;
    	dp[1][3] = 2;
    	dp[1][4] = 1;
    	
    	for (int i = 2; i <= n; i++) {
    		
    		int acc = 0;
    		for (int j = 4; j >= 0; j--) {
    			acc += dp[i-1][j];
    			dp[i][j] += acc;
    		}
    	}
    	
    	return dp[n][0];
    }
}
