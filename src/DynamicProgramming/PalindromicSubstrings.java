package DynamicProgramming;

public class PalindromicSubstrings {

	public static void main(String[] args) {
		/**
		 * 給定一組字串
		 * 找出這處字串有多少子字串是回文的
		 * 相同自但是不同位置也視為不同子字串
		 * 例如aaa總共有六個
		 * a,a,a,aa,aa,aaa
		 */
		
		System.out.println(countSubstrings("aaa"));
		System.out.println(countSubstrings("abc"));
	}

    public static int countSubstrings(String s) {
    	/**
    	 * dp
    	 * dp[i][j] 紀錄的是位置i~j是否回文
    	 * 轉移方程式：
    	 * dp[i][j] = 1 if i == j
    	 * dp[i][j] = 1 if s[i] == s[j] && j-i == 1
    	 * dp[i][j] = 1 if s[i] == s[j] && dp[i+1][j-1] == 1
    	 */
    	
    	int[][] dp = new int[s.length()][s.length()];
    	int count = 0;
    	
    	for (int j = 0; j < s.length(); j++) {
    		for (int i = 0; i <= j; i++) {
    			
    			if (i == j) {
					dp[i][j] = 1;
					count += 1;
					continue;
				}
    			if ((dp[i+1][j-1] == 1 || j-i == 1) && s.charAt(i) == s.charAt(j)) {
    				dp[i][j] = 1;
    				count += 1;
    				continue;
    			}
    		}
    	}
    	
    	for (int i = 0; i < s.length(); i++) {
    		System.out.print("[");
    		for (int j = 0; j < s.length(); j++) {
    			System.out.print(dp[i][j]+",");
    		}
    		System.out.print("],");
    	}
        
    	return count;
    }
}
