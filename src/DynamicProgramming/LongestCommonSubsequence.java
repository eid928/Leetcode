package DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubsequence {

	public static void main(String[] args) {
		/**
		 * 給兩組字串
		 * 求出最長的相同子序列長度為多少
		 * 子序列非子字串，可以不連續
		 */
		String text1 = "abcde";
		String text2 = "ace";
		System.out.println(longestCommonSubsequence3(text1, text2)); // 3, ace
		text1 = "abc";
		text2 = "abc";
		System.out.println(longestCommonSubsequence3(text1, text2)); // 3, abc
		text2 = "def";
		System.out.println(longestCommonSubsequence3(text1, text2)); // 0
	}
	
	public static int longestCommonSubsequence(String text1, String text2) {
		/**
		 * dp tabulation
		 * 用二維陣列dp[i][j]儲存答案
		 * dp[i][j]代表text1前i個字母的子字串和text2前j個字母的子字串的LCS
		 * dp[0][0]代表text1="", text2=""
		 * dp[text1.length][text2.length]即為答案
		 * 
		 * base case: dp[i][j] = 0 if i == 0 || j == 0
		 * dp[i][j] = dp[i-1][j-1] + 1 where text1[i] == text2[j]
		 *          = max( dp[i-1][j], dp[i][j-1] ) where text1[i] != text2[j]
		 *          
		 * table: 
		 *   a b c d e
		 * a 1 1 1 1 1
		 * c 1 1 2 2 2
		 * e 1 1 2 2 3
		 */
		int[][] dp = new int[text1.length()+1][text2.length()+1];
		
		for (int i = 1; i <= text1.length(); i++) {
			for (int j = 1; j <= text2.length(); j++) {
				
				if (text1.charAt(i-1) == text2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		return dp[text1.length()][text2.length()];
	}

    public static int longestCommonSubsequence2(String text1, String text2) {
    	/**
    	 * dfs + memo
    	 * i, j代表text1, text2從i, j開始的substring
    	 * 樹狀圖:
    	 * 每個節點分別為一組i,j，可以對應到兩個substring
    	 * 如果首字母相同，即text1.charAt(i) == text2.charAt(j)
    	 * 則該節點底下只有一個分支，也就是將相同的首字母去掉 => i++, j++
    	 * 並將結果+1
    	 * 若首字母不同，則底下節點有兩個分支，即:
    	 * 1. 把text1首字母去掉，i++
    	 * 2. 把text2首字母去掉，j++
    	 * 分別查找，看哪個分支的結果大
    	 * abcde,ace -> bcde,ce -> cde,ce -> de,e -> e,e
    	 * 						                  -> de,''
    	 * 						-> bcde,e -> ...
    	 */
    	Map<Integer, Map<Integer, Integer>> memo = new HashMap<>();
    	
        int dfs = dfs(text1, text2, 0, 0, memo);
        System.out.println(memo);
		return dfs;
    }
    
    public static int longestCommonSubsequence3(String text1, String text2) {
    	/**
    	 * dfs + memo
    	 * 用matrix版本，比map快非常多，速度接近tabulation
    	 * 由於dfs填入matrix答案的順序不一定，所以matrix要事先都填入-1當作尚未訪問
    	 */
    	int[][] dp = new int[text1.length()][text2.length()];
    	for (int i = 0; i < dp.length; i++) {
    		Arrays.fill(dp[i], -1);
    	}
    	
        int dfs = dfs2(text1, text2, 0, 0, dp);
		return dfs;
    }

	private static int dfs(String text1, String text2, int i, int j, Map<Integer, Map<Integer, Integer>> memo) {
		
		if (memo.containsKey(i) && memo.get(i).containsKey(j)) {
			return memo.get(i).get(j);
		}
		
		if (i >= text1.length() || j >= text2.length()) {
			return 0;
		}
		
		int result = 0;
		
		if (text1.charAt(i) == text2.charAt(j)) {
			result = 1 + dfs(text1, text2, i+1, j+1, memo);
		} else {
			int cutText1 = dfs(text1, text2, i+1, j, memo);
			int cutText2 = dfs(text1, text2, i, j+1, memo);
			result = Math.max(cutText1, cutText2);
		}
		
		Map<Integer, Integer> map = memo.getOrDefault(i, new HashMap<>());
		map.put(j, result);
		memo.put(i, map);
		return result;
	}
	
	private static int dfs2(String text1, String text2, int i, int j, int[][] dp) {
		
		if (i < dp.length && j < dp[i].length && dp[i][j] != -1) {
			return dp[i][j];
		}
		
		if (i >= text1.length() || j >= text2.length()) {
			return 0;
		}
		
		int result = 0;
		
		if (text1.charAt(i) == text2.charAt(j)) {
			result = 1 + dfs2(text1, text2, i+1, j+1, dp);
		} else {
			int cutText1 = dfs2(text1, text2, i+1, j, dp);
			int cutText2 = dfs2(text1, text2, i, j+1, dp);
			result = Math.max(cutText1, cutText2);
		}
		
		dp[i][j] = result;
		return result;
	}
}
