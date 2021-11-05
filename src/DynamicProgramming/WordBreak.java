package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreak {

	public static void main(String[] args) {
		
		String s = "leetcode";
		List<String> wordDict = new ArrayList<>();
		wordDict.add("leet");
		wordDict.add("code");

		wordBreak(s, wordDict);
		
		s = "catsandog";
		wordDict = new ArrayList<>();
		wordDict.add("cats");
		wordDict.add("dog");
		wordDict.add("sand");
		wordDict.add("and");
		wordDict.add("cat");
		
		wordBreak(s, wordDict);
	}
	
	public static boolean wordBreak(String s, List<String> wordDict) {
		
		/**
		 * dp[i]紀錄字串0~i是否可以拆分
		 * 轉移方程式：
		 * dp[i] = (dp[j] && j~i) || ...
		 * j是i之前所有為True的位置
		 * 例如catsandog
		 * [c,a,t,s,a,n,d,o,g]
		 * [F,F,T,T,F,i......]
		 * 當我指向i這個位置時
		 * 就讓j指針從一開始往i走
		 * 每當j指向True，就檢查剩下的字串是否有在字典內
		 */
		Set<String> dict = new HashSet<>(wordDict);
		boolean[] dp = new boolean[s.length()+1];
		dp[0] = true;
		
		for (int i = 1; i < dp.length; i++) {
			
			for (int j = 0; j < i; j++) {
				if (!dp[j]) continue;
				String substring = s.substring(j, i);
				
				if (dict.contains(substring)) {
					dp[i] = true;
					break;
				};
			}
		}
		
		return dp[dp.length-1];
	}

    public static boolean wordBreak2(String s, List<String> wordDict) {
        
    	Set<String> dict = new HashSet<>(wordDict);
    	Map<String, Boolean> dp = new HashMap<>();
    	
    	return couldBreak(s, dict, dp);
    }

	private static boolean couldBreak(String s, Set<String> dict, Map<String, Boolean> dp) {
		
		if (dict.contains(s)) {
			dp.put(s, true);
			return true;
		}
		
		if (dp.containsKey(s)) {
			return dp.get(s);
		}
		
		for (int i = 1; i < s.length(); i++) {
			String firstSection = s.substring(0, i);
			String secondSection = s.substring(i);
			
			if (dict.contains(firstSection) && couldBreak(secondSection, dict, dp)) {
				dp.put(s, true);
				return true;
			}
		}
		
		dp.put(s, false);
		return false;
	}
}
