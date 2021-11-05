package DynamicProgramming;

public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		
		String s = "babad";
		String s2 = "cbbd";
		String s3 = "a";
		String s4 = "ac";
		
		System.out.println(longestPalindrome(s));
		System.out.println(longestPalindrome(s2));
		System.out.println(longestPalindrome(s3));
		System.out.println(longestPalindrome(s4));
	}

    public static String longestPalindrome(String s) {
    	/**
    	 * dynamic programing
    	 * 時空複雜度皆為平方，好像沒有比較快，顆顆
    	 * dp[i][j]紀錄的是字串位置i到j的子字串是否是回文
    	 * 轉移方程式：
    	 * dp[i][j] = 1 if i == j
    	 * dp[i][j] = s.charAt(i)==s.charAt(j) if i+1 == j
    	 * dp[i][j] = s.charAt(i)==s.charAt(j) && dp[i+1][j-1] if i+1 < j
    	 * 由轉移方程式可以得知，要知道dp[i][j]必須先知道dp[i+1][j-1]
    	 * 所以遍歷的順序，不能為i:0~n; j:i+1~n
    	 * 而是j: 0~n; i: 0~j
    	 */
    	if (s.length() == 0) return "";
    	
    	int[][] dp = new int[s.length()][s.length()];
    	int left = 0;
    	int right = 0;
    	
    	for (int j = 0; j < s.length(); j++) {
    		
    		dp[j][j] = 1;
    		
    		for (int i = 0; i < j; i++) {
    			
    			if (s.charAt(i) == s.charAt(j) && (i+1==j || dp[i+1][j-1] == 1)) {
    				dp[i][j] = 1;
    				if (j-i > right-left) {
    					right = j;
    					left = i;
    				}
				}
    		}
    	}
        return s.substring(left, right+1);
    }
}
