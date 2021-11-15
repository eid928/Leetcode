package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreakII {

	public static void main(String[] args) {
		/**
		 * 和wordbreak類似
		 * 但這個要回傳所有能組成的單字組合併合成一個句子
		 */
		String s1 = "og";
		System.out.println(s1.substring(0, 2));
		System.out.println(s1.substring(2));
		
		String s = "catsandog";
		List<String> wordDict = new ArrayList<>();
		wordDict.add("cats");
		wordDict.add("dog");
		wordDict.add("sand");
		wordDict.add("and");
		wordDict.add("cat");
		System.out.println(wordBreak(s, wordDict));
		
		s = "catsanddog";
		System.out.println(wordBreak(s, wordDict));
		
		s = "pineapplepenapple";
		wordDict = new ArrayList<>();
		wordDict.add("apple");
		wordDict.add("pen");
		wordDict.add("applepen");
		wordDict.add("pine");
		wordDict.add("pineapple");
		System.out.println(wordBreak(s, wordDict));
	}

    public static List<String> wordBreak(String s, List<String> wordDict) {
    	/**
    	 * dfs + dp memo
    	 * dp memo實際上可以改進一些case，但因為這題還是找所有組合
    	 * 其worst case是超多可能的組合，此時dp就無法給予太多幫助
    	 * n = wordDict.size
    	 * m = s.length
    	 * 畫出樹狀圖的話，最高高度為m，每個節點最多分支n
    	 * 所有的可能節點最多為n^m個
    	 * 在worst case下，所有節點都需訪問
    	 * time: O(n^m)
    	 */
    	Map<String, List<String>> dp = new HashMap<String, List<String>>();
    	Set<String> wordSet = new HashSet<String>(wordDict);
    	List<String> result = dfs(s, wordSet, dp);
    	
        return result;
    }

	private static List<String> dfs(String s, Set<String> wordSet, Map<String, List<String>> dp) {
		System.out.println("s:"+s);
		
		if (dp.containsKey(s)) { /* 用dp memo紀錄 */
			return dp.get(s);
		}
		
		if (s.isEmpty()) {
			/**
			 * base case: 如果能成功拆到空字串
			 * 那這條路會是一個成功的組合，要回傳result並包含一個""的元素，讓size不為0表示這是成功的路
			 */
			List<String> result = new ArrayList<>();
			result.add("");
			return result;
		}
		
		List<String> result = new ArrayList<>();
		/* 若是s之後所有路都失敗，那就是回傳這個new ArrayList<>()，size為0，表示都是死路 */
		
		for (int i = 1; i <= s.length(); i++) {
			
			String breakPart = s.substring(0, i);
			String remainPart = s.substring(i);
			
			if (wordSet.contains(breakPart)) {
				
				List<String> remainPartResult = dfs(remainPart, wordSet, dp);
				if (remainPartResult.size() != 0) { /* 有活路 */
					
					for (String string : remainPartResult) { /* 把remainpart的答案拿來重新組合成句子 */
						if (string.equals("")) result.add(breakPart);
						else result.add(breakPart+" "+string);
					}
				}
			}
		}
		
		dp.put(s, result); /* 用dp memo紀錄 */
		return result;
	}
}
