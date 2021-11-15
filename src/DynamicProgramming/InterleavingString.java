package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class InterleavingString {

	public static void main(String[] args) {
		/**
		 * 查看s3是否為s1和s2的Interleave
		 * Interleave: 
		 * s3可由s1和s2的子字串交互組成
		 * aadbbcbcac = aa(s1) + dbbc(s2) + bc(s1) + a(s2) + c(s1)
		 */
		String s1 = "aabcc";
		String s2 = "dbbca";
		String s3 = "aadbbcbcac";
		System.out.println(isInterleave(s1, s2, s3));
		s1 = "aabcc";
		s2 = "dbbca";
		s3 = "aadbbbaccc";
		System.out.println(isInterleave(s1, s2, s3));
		s1 = "";
		s2 = "";
		s3 = "";
		System.out.println(isInterleave(s1, s2, s3));
	}
	
	public static boolean isInterleave(String s1, String s2, String s3) {
		/**
		 * 正規dp
		 * dp[i][j]表示s1前i個字元以及s2前j個字元，是否能組成s3前i+j個字元
		 * 轉移方程
		 * base case: dp[0][0] = true
		 * dp[i][j] = (dp[i-1][j] && s1[i-1] == s3[i+j-1]) || (dp[i][j-1] && s2[j-1] == s3[i+j-1])
		 */
		if (s3.length() != s1.length() + s2.length()) {
			return false;
		}
		
		boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
		
		for (int i = 0; i < dp.length; i++) {
			
			for (int j = 0; j < dp[i].length; j++) {
				if (i == 0 && j == 0) {
					dp[0][0] = true;
					continue;
				}
				if (i == 0) {
					dp[i][j] = dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1);
					continue;
				}
				if (j == 0) {
					dp[i][j] = dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1);
					continue;
				}
				dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1)) || (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
			}
		}
		
		return dp[dp.length-1][dp[0].length-1];
	}

    public static boolean isInterleave2(String s1, String s2, String s3) {
    	/**
    	 * 暴力dfs + dp memo
    	 */
    	if (s3.length() != s1.length() + s2.length()) {
			return false;
		}
    	
    	Map<String, Boolean> dp = new HashMap<String, Boolean>();
    	
        return dfs(s1, s2, s3, dp);
    }

	private static boolean dfs(String s1, String s2, String s3, Map<String, Boolean> dp) {
		//System.out.println("s1: "+s1+", s2: "+s2+", s3: "+s3);
		/**
		 * 暴力dfs搭配dp
		 * dp用s1+","+s2+","+s3來紀錄答案
		 * 
		 */
		if (dp.containsKey(s1+","+s2+","+s3)) {
			return dp.get(s1+","+s2+","+s3);
		}
		
		if (s3.equals("") && s1.equals("") && s2.equals("")) {
			return true;
		}
		
		if (s3.equals(s1+s2)) { /* base case */
			return true;
		}
		
		for (int i = 1; i < s3.length(); i++) {
			/**
			 * 每次從s3先取一點來看，看看s1和s2有沒有以此為開頭
			 * 有的話，就dfs剩下來的字串
			 */
			
			String subString = s3.substring(0, i);
			
			if (s1.startsWith(subString)) {
				String remainS1 = s1.substring(subString.length());
				String remainS3 = s3.substring(subString.length());
				if (dfs(remainS1, s2, remainS3, dp)) {
					dp.put(s1+","+s2+","+s3, true);
					return true;
				}
			}
			if (s2.startsWith(subString)) {
				String remainS2 = s2.substring(subString.length());
				String remainS3 = s3.substring(subString.length());
				if (dfs(s1, remainS2, remainS3, dp)) {
					dp.put(s1+","+s2+","+s3, true);
					return true;
				}
			}
		}
		dp.put(s1+","+s2+","+s3, false);
		return false;
	}
}
