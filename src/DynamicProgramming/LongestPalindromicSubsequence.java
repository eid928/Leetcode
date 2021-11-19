package DynamicProgramming;

public class LongestPalindromicSubsequence {

	public static void main(String[] args) {
		
		String s = "bbbab";
		System.out.println(longestPalindromeSubseq(s)); // 4: bbbb
		s = "cbbd";
		System.out.println(longestPalindromeSubseq(s)); // 2: bb
	}

    public static int longestPalindromeSubseq(String s) {
    	/**
    	 * dp tabulation
    	 * dp[i][j]為s位置i~j中最長的回文子序列長度
    	 * 
    	 * dp[i][j] = 1 if i == j
    	 * dp[i][j] = dp[i+1][j-1]+2 if s[i] == s[j] (就算i+1=j，結果也會是2)
    	 * dp[i][j] = max( dp[i+1][j], dp[i][j-1] ) if s[i] != s[j]
    	 * 
    	 * time: O(N^2)
    	 */
    	
    	int[][] dp = new int[s.length()][s.length()];
    	
    	for (int j = 0; j < s.length(); j++) {
    		
    		for (int i = j; i >= 0; i--) {
    			
    			if (i == j) {
					dp[i][j] = 1;
				} else if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = dp[i+1][j-1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
				}
    		}
    	}
    	
        return dp[0][s.length()-1];
    }
}
