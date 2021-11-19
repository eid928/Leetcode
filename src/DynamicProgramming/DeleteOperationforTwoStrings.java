package DynamicProgramming;

public class DeleteOperationforTwoStrings {

	public static void main(String[] args) {
		
		String word1 = "sea";
		String word2 = "eat";
		System.out.println(minDistance(word1, word2)); //2
		word1 = "leetcode";
		word2 = "etco";
		System.out.println(minDistance(word1, word2)); //4
	}
	
    public static int minDistance(String word1, String word2) {
    	/**
    	 * dp tabulation
    	 * 這題換句話說就是求longest common subsequence
    	 * 所以做法一樣
    	 * dp[i][j]為word1前i個字元和word2前j個字元的最長子序列長度
    	 * dp[0][0]代表word1="", word2=""
		 * dp[word1.length][word2.length]即為答案
		 * 
		 * base case: dp[i][j] = 0 if i == 0 || j == 0
		 * dp[i][j] = dp[i-1][j-1] + 1 where word1[i] == word2[j]
		 *          = max( dp[i-1][j], dp[i][j-1] ) where word1[i] != word2[j]
    	 */
    	
    	int lengthOfLCS = 0;
    	int[][] dp = new int[word1.length()+1][word2.length()+1];
    	
    	for (int i = 1; i <= word1.length(); i++) {
    		for (int j = 1; j <= word2.length(); j++) {
    			if (word1.charAt(i-1) == word2.charAt(j-1)) {
    				dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
    		}
    	}
    	lengthOfLCS = dp[word1.length()][word2.length()];
    	
        return word1.length() + word2.length() - lengthOfLCS*2;
    }
}
